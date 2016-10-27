<?php 
/* orderDisplay.php
 * from DWP chapter 9, revised by Bob Kasper, August 2016
 */
    // added session check, August 2016
    session_start();
    if ( empty($_SESSION['user']) )
    {  header("Location: login.php?target=" . $_SERVER['PHP_SELF']);
       exit;
    }

   $title="Display Orders from Database";
   $bgcolor="#fff";
   include('head.inc');
?>
<h2>Your order information.</h2><p>
<?php
    // require_once("mysqli.php");  // creates $db_obj
    require_once("connectDB.php");
    require_once("manageOrder.php");  // order management functions
 
    // added processing for request to create new order, August 2016
    if (!empty($_POST['cmd'])) { // BK: placeholder to detect form submission
        $customer = $_SESSION['user'];
        $order_id = enterOrder($customer, $_SESSION['cart']);
        if (! $order_id)
           echo "Failed: could not create a new order.";
        else {unset($_SESSION['cart']);}
    }
 
 // display order
    if ($order_id) // use new order id, August 2016
        $ot = orderTable($order_id);
    if ( $ot ) echo $ot;
    else {  echo "Order display failed."; }
    $db_obj->close(); // closes database connection
?> 
</p></body></html>
