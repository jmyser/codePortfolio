<?php session_start();
      $title="Payment Confirmation";
      $bgcolor="#fff"; include('../head.inc');

// pdtArray puts PDT response string into an array
function pdtArray(&$res)
{ $lines = explode("\n", $res);
  $keyarray = array();
  if (strcmp ($lines[0], "SUCCESS") == 0)   // must be SUCCESS
  { for ($i=0; $i<count($lines);$i++){
    list($key,$val) = explode("=", $lines[$i]);
    $keyarray[urldecode($key)] = urldecode($val); }
  }
  return $keyarray;
}

//require_once('HTTP/Request.php');
require_once('HTTP/Request2.php');
$requrl = 'https://www.paypal.com/cgi-bin/webscr';
$auth_token = "CVsvIY7IZ_hjf2zQoRsYhkifaUOdwPCNzYJg9rf__zs7XkaAI6cMr7DJAO8";
$req = &new HTTP_Request2($requrl);
$req->setConfig('ssl_verify_peer', FALSE);
$req->setMethod(HTTP_Request2::METHOD_POST);
$req->addPostParameter("at", $auth_token);
$req->addPostParameter('cmd', '_notify-synch');
$req->addPostParameter('tx', $_GET['tx']);

$response = $req->send();
if (200 != $response->getStatus()) // HTTP ERROR
{  echo "Sorry: Order Processing failed!\n";  
   exit(1); 
} 
else
{   $msg = $response->getBody();
    echo "<pre>\n";
    print_r(pdtArray($msg));
    echo "</pre>\n";
}

//  print_r($_SESSION['cart']);
?>
<h2>Thank You for your Order</h2>
Your transaction has been completed, and a receipt for your purchase has been emailed to you.<br>You may log into your account at <a href='https://www.paypal.com'>www.paypal.com</a> to view details of this transaction.<br>
</body></html>
