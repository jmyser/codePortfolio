<?php 
   session_start();
   $title="Checkout Display";
   $bgcolor="#fff";
   include('head.inc');
?>
<h3>Ready to Checkout?</h3>
<?php
    // require_once("mysqli.php");      // creates $db_obj
    require_once("connectDB.php");
    require_once("manageOrder.php"); // order management functions
    $cart=$_SESSION['cart'];         // cart from session state

 // produce checkout display
    $code = checkoutForm($cart, "orderDisplay.php", $fixed);
    if ( $code ) echo $code;
    else {  echo "Checkout display failed."; }
    // echo '<p><a href="cartDisplay.php">Edit Cart</a></p>';
    $db_obj->close(); // closes database connection
?> 
</body></html>

