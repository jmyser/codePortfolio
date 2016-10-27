<?php require_once('htmlTable.php');
/* manageOrder.php
 * from DWP chapter 9, revised by Bob Kasper, August 2016
 */

// $items=associative array of product id and qty
// $gateway=URL of payment gateway
function checkoutForm(&$items,$gateway, $hidden)
{ global $db_obj;
  $query="";
  if (empty($items)) return "<p>Nothing in your order.</p>";
  foreach($items as $prod=>$qty)
  { $query .= "UNION SELECT id as Product_ID, description AS Description, 
            price AS Price, $qty AS Quantity,
            $qty*price AS Amt
            FROM product  WHERE id='$prod'";
  }
  $query=substr($query,6);
  if ( ($result=$db_obj->query($query)) )
  {  $ans=htmlCheckout($result,"Description", "Amt",
                    "Quantity", $gateway, $hidden, "db");
     $result->close(); return $ans; 
  }
  else return FALSE;
}

// $items=associative array of product id and qty
// $action=URL of form action
function cartTable(&$items,$action)
{ global $db_obj;
  $query="";
  if (empty($items)) return "<p>Shopping cart is empty.</p>";
  foreach($items as $prod=>$qty)
  { $query .= "UNION SELECT id as Product_ID, description AS Description, 
            price AS Price, $qty AS Quantity,
            $qty*price AS Amt
            FROM product  WHERE id='$prod'";
  }
  $query=substr($query,6);
  if ( ($result=$db_obj->query($query)) )
  {  $ans=htmlCart($result,"Product_ID", "Amt",
                    "Quantity", $action, "db");
     $result->close(); return $ans; 
  }
  else return FALSE;
} 

function orderTable($order)
{ global $db_obj; // BK: customer -> member table, id -> uid
                  // unquote $order in both queries
  $query = "SELECT concat(first, ' ', last) from member
      WHERE uid=(SELECT customer from orders WHERE id=$order)";
  if ( !($customer=value_from_db($query)) ) 
     return "<p>No such order: $order</p>";
  $ans="<p>Customer: $customer,  Order: $order</p>\n";
  $query = "SELECT product.id as Product_ID, product.description 
     AS Description, product.price AS Price, qty AS Quantity,
     qty*product.price AS Amt FROM order_item, product 
     WHERE order_id=$order && prod_id=product.id";
  if ( ($result=$db_obj->query($query)) )
  {  $ans.=htmlOrder($result,"Amt","db"); $result->close(); return $ans;  }
  else return FALSE;
}

function changeQty($order, $product, $qty)
{ global $db_obj; ;
  if ( ($qty=intval($qty)) == 0 ) 
  {  removeItem($order, $product);
     return;
  }
  elseif ( $qty > 0 )
  {  $query="INSERT INTO order_item VALUES
          ('$order', '$product', $qty)
          ON DUPLICATE KEY UPDATE qty=$qty";
     return ( $db_obj->query($query) );
  }
}

function removeItem($order, $product)
{ global $db_obj;
  $query="DELETE FROM order_item WHERE
          order_id='$order' && prod_id='$product'";
  if ( $db_obj->query($query) && $db_obj->affected_rows==1 )
  {   $query="SELECT * FROM order_item WHERE order_id='$order'";
      $result=$db_obj->query($query);
      if ($result && $result->num_rows == 0 )
      {  $result->close();
         return (removeOrder($order));
      }
      if ($result) $result->close(); return true;
  }
  return false;
}
$item_stmt=FALSE;

function addItem_p($order, $product, $qty)  // addItem with prepare
{ global $db_obj, $item_stmt, $p_o, $p_p, $p_q;
  $qty=intval($qty);
  if ( !$item_stmt )
  { $item_stmt = $db_obj->prepare(
       "INSERT INTO order_item VALUES (?, ?, ?)
       ON DUPLICATE KEY UPDATE qty=qty+?");
    if ( $item_stmt )
    {  if (! $item_stmt->bind_param('ssii', 
                    $p_o, $p_p, $p_q, $p_q)
          ) $item_stmt=FALSE;
    }
  }
  if ( $item_stmt && $qty > 0 )
  { $p_o=$order; $p_p=$product; $p_q=$qty;
    return $item_stmt->execute(); 
  }
  return FALSE;
}

function addItem($order, $product, $qty)
{ global $db_obj;
  $qty=intval($qty); if ($qty <= 0) return FALSE;
  $query="INSERT INTO order_item VALUES
          ('$order', '$product', $qty)
          ON DUPLICATE KEY UPDATE qty=qty+$qty";
  return ( $db_obj->query($query) );   // true or false
}

/* ORIGINAL VERSION of newOrder and enterOrder:
function newOrder($order, $customer)
{ global $db_obj;
  $query="INSERT INTO orders VALUES
          ('$order', '$customer', CURRENT_DATE)";
  return ( $db_obj->query($query) );
}

function enterOrder($order, $customer, &$cart)
{ global $db_obj;
  if ( empty($cart) ) return false;  // empty order
  $err=FALSE;
  $db_obj->autocommit(FALSE);
  if ( newOrder($order, $customer) )
  {  foreach($cart as $product=>$qty)
     { if (!addItem_p($order,$product,$qty))
       { $err=TRUE;  break;  }
     }
     if ( $err ) $db_obj->rollback();
     else  $db_obj->commit();
  }
  else { $db_obj->rollback(); return FALSE;  } 
  $db_obj->autocommit(TRUE);
  return !$err;
}
 */

/* August 2016: create new order using auto_increment
 * to generate unique order id */

// return id of new order
function newOrder($customer)
{ global $db_obj;
  $query="INSERT INTO orders(customer, order_date) VALUES
          ('$customer', CURRENT_DATE)";
  $inserted = $db_obj->query($query);
  if ($inserted) {
      $order_id = $db_obj->insert_id;
      return $order_id;
  }
  else return false;
}

// return id of new order
function enterOrder($customer, &$cart)
{ global $db_obj;
  if ( empty($cart) ) return false;  // empty order
  $err=FALSE;
  $db_obj->autocommit(FALSE);
  if ( $order = newOrder($customer) )
  {  foreach($cart as $product=>$qty)
     { if (!addItem_p($order,$product,$qty))
       { $err=TRUE;  break;  }
     }
     if ( $err ) $db_obj->rollback();
     else  $db_obj->commit();
  }
  else { $db_obj->rollback(); return FALSE;  } 
  $db_obj->autocommit(TRUE);
  return ($err ? false : $order);
}


//  Deleting an order removes all order related entries
//      on the order_item table due to the on delete cascade clause
function removeOrder($order)
{ global $db_obj;
  $query="DELETE FROM orders WHERE id='$order'";
  return ( $db_obj->query($query) );
}

function value_from_db($q)
{   global $db_obj;
    $r = $db_obj->query($q);
    if (  $r && $r->num_rows == 1 )
    {   $row = $r->fetch_row();
        return($row[0]);
    }
    else return FALSE;
}

$fixed='<input type="hidden" name="cmd" value="_cart" />
<input type="hidden" name="upload" value="1">
<input type="hidden" name="business" value="pwang@sofpower.com" />
<input type="hidden" name="return" 
       value="https://dwp.sofpower.com/exc09/orders/processOrder.php" />
<input type="hidden" name="cancel_return" 
       value="https://dwp.sofpower.com/exc09/orders/failedOrder.php" />
<input type="hidden" name="image_url" 
       value="https://host10.webserveralpha.com/sofpower/images/sofppal.gif" />';

// htmlCheckout takes a query restult set and 
//  returns html code for a checkout display
//   showing the shopping cart with hidden
//   values for the payment gateway (PayPal)

function htmlCheckout(&$result_obj, $item_name, $amt_name,
                  $qty_name, $gateway, $gwValues, $class)
{ if ( $result_obj->num_rows == 0 ) return ""; 
  $payform = "<form method='post' action='$gateway'>
           <table border='1' class='" . $class . "'>\n";
  $result_obj->data_seek(0);     // make sure we start from 1st row
  $header_done = false; 
  $total=0.0;  $cols=0;  $item_count=0; $gwValues .= "\n"; 
  while( $data = $result_obj->fetch_assoc() ) // each row as asso array
  {// table header
     $item_count++;
     if (!$header_done)                       // table headers
     {  $payform .= '<tr>';
        $cols=count($data);
        foreach($data as $attr => $value)
        {  $payform .= "<th>$attr</th>";  } 
        $payform .= "</tr>\n";
        $header_done = true; 
        reset($data);
     } 
     $n=$data[$item_name];$q=$data[$qty_name]; $amt=$data[$amt_name];
     $total += $amt;
     $gwValues .= 
      "<input type='hidden' name=\"item_name_$item_count\" value=\"$n\">
       <input type='hidden' name=\"amount_$item_count\" value=\"$amt\">
       <input type='hidden' name=\"quantity_$item_count\" value=\"$q\">\n";
   // table rows
    $payform .= '<tr>';
    foreach($data as $attr => $value) $payform .= "<td>$value</td>"; 
    $payform .= "</tr>\n";
  }
  $total=number_format($total, 2, '.', '');
  $payform .="<tr class='total'><td colspan='" . ($cols - 1) . 
        "'>Total: </td><td>$total</td></tr>";
  return ($payform . "</table>\n$gwValues <input type='submit' value='Make Payment' />
  &nbsp;&nbsp;<input type='button' onclick=\"window.location='cartDisplay.php'\"
           value='Edit Cart' /></form>\n");
}

///// echo orderTable("ord_03039"); 
///// error msg of failed query availabe in $db_obj->error
//// uncomment for testing
// require_once("connectDB.php");
// $cart=array("prod_99008"=>6, "prod_99007"=>7, "prod_99004"=>8);
// $cart=array("prod_99007"=>4, "prod_99008"=>4, "prod_99004"=>4);
// $cart=array("prod_99008"=>6);
//echo cartTable($cart); 
//if (enterOrder("ord_30994", "cus_12002", $cart))
// else echo "failed";
//echo orderTable("ord_01009"); 
// changeQty("ord_01009", "prod_99008", 2);
// removeItem("ord_01009", "prod_99008");
// removeItem("ord_04049", "prod_99007");
?>
