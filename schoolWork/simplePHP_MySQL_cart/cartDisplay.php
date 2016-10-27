<?php 
   session_start();
   if ( empty($_SESSION['cart']) )
   // pretend cart from session state
       $_SESSION['cart']=array("prod_99007"=>2, "prod_99008"=>3);   
   $cart=&$_SESSION['cart'];

   $title="Shopping Cart from Session State and Database";
   $bgcolor="#fff";
   include('head.inc');
?>
<h3>Your Cart</h3>
<?php
    // BK: replaced require_once("mysqli.php");  // creates $db_obj
    require_once("connectDB.php");
    require_once("manageOrder.php");  // order management functions
    if (!empty($_POST['update']) && !empty($_POST['cart'])) 
    {  $newcart=&$_POST['cart'];         
       foreach($cart as $prod=>$qty)
       {  $nqty=0;  
          if (!empty($newcart[$prod])) $nqty=intval($newcart[$prod]);
          if ( $nqty<=0 ) { unset($cart[$prod]); }
	  else if ( $nqty!=$qty ) 
	  { $cart[$prod]=$nqty; }
       }
    }
 // display cart
    $ct = cartTable($cart, "");
    if ( $ct ) echo $ct;
    else {  echo "Cart display failed."; }
    $db_obj->close(); // closes database connection
?> 
</body></html>
