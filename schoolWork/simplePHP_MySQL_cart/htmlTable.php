<?php
// htmlOrder takes a query restult set and 
// returns html code for order table from a result set 
// empty string if result set has zero rows

function htmlOrder(&$result_obj, $amt_name, $class)
{ if ( $result_obj->num_rows == 0 ) return ""; 
  $tb = "<table border='1' class='" . $class . "'>\n";
  $result_obj->data_seek(0);     // make sure we start from 1st row
  $header_done = false; 
  $total=0.0;  $cols=0;
  while( $data = $result_obj->fetch_assoc() ) // each row as asso array
  {// table header
     if (!$header_done)                       // table headers
     {  $tb .= '<tr>';
        $cols=count($data);
        foreach($data as $attr => $value)
        {  $tb .= "<th>$attr</th>";  } 
        $tb .= "</tr>\n";
        $header_done = true; 
     } 
     $total += $data[$amt_name];
   // table rows
     $tb .= "<tr><td>";                       // table cells
     $tb .= implode("</td><td>", $data); 
     $tb .= "</td></tr>\n";
  }
  $total=number_format($total, 2, '.', '');
  $tb .="<tr class='total'><td colspan='" . ($cols - 1) . 
        "'>Total: </td><td>$total</td></tr>";
  return ($tb . "</table>\n");
}

function htmlTable(&$result_obj, $class)
{ if ( $result_obj->num_rows == 0 ) return ""; 
  $tb = "<table border='1' class='" . $class . "'>\n";
  $result_obj->data_seek(0);     // make sure we start from 1st row
  $header_done = false; 
  while( $data = $result_obj->fetch_assoc() ) // each row as asso array
  {// table header
     if (!$header_done)                       // table headers
     {  $tb .= '<tr>';
        foreach($data as $attr => $value)
        {   $tb .= "<th>$attr</th>"; } 
        $tb .= "</tr>\n";
        $header_done = true; 
     } 
   // table rows
     $tb .= "<tr><td>";                       // table cells
     $tb .= implode("</td><td>", $data); 
     $tb .= "</td></tr>\n";
  }
  return ($tb . "</table>\n");
}

// htmlCart takes a query restult set and 
//  returns html code for a shopping cart 
//   allowing quantity updates
function htmlCart(&$result_obj, $id_name, $amt_name,
                  $qty_name, $action, $class)
{ if ( $result_obj->num_rows == 0 ) return ""; 
  $cart = "<form method='post' action='$action'>
           <table border='1' class='" . $class . "'>\n";
  $result_obj->data_seek(0);     // make sure we start from 1st row
  $header_done = false; 
  $total=0.0;  $cols=0;
  while( $data = $result_obj->fetch_assoc() ) // each row as asso array
  {// table header
     if (!$header_done)                       // table headers
     {  $cart .= '<tr>';
        $cols=count($data);
        foreach($data as $attr => $value)
        {  $cart .= "<th>$attr</th>";  } 
        $cart .= "</tr>\n";
        $header_done = true; 
        reset($data);
     } 
     $total += $data[$amt_name];
     $q=$data[$qty_name];
     $id=$data[$id_name];
     $qty_code="<td><input size='3' name='cart[$id]' value='$q' 
        type='number' step='1' min='0' required='' /></td>";
   // table rows
    $cart .= '<tr>';
    foreach($data as $attr => $value)
    {  if ( $attr == $qty_name ) $cart .= $qty_code;
       else $cart .= "<td>$value</td>"; 
    } 
    $cart .= "</tr>\n";
  }
  $total=number_format($total, 2, '.', '');
  $cart .="<tr class='total'><td colspan='" . ($cols - 1) . 
        "'>Total: </td><td>$total</td></tr>";
  return ($cart . "</table><input type='submit' name='update' value='Update' />
  &nbsp;&nbsp;<input type='button' onclick=\"window.location='checkoutDisplay.php'\"
         value='Checkout' /></form>\n ");
}

// see also:  $row=result_obj->fetch_row         // indexed from 0
//            $row=$result_obj->fetch_object()   // as object properties

// You can obtain meta info for each column 
//  $info_obj = $result->fetch_field_direct(1);
//  $info_obj->name;
//  $info_obj->table;
//  $info_obj->max_length;
//  $info_obj->flags;
//  $info_obj->type;
?>
