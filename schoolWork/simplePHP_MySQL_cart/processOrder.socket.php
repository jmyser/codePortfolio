<?php $title="Payment Confirmation";
      $bgcolor="#fff"; include('../head.inc');
// read the post from PayPal system and add 'cmd'
$body = 'cmd=_notify-synch';

$tx_token = $_GET['tx'];
$auth_token = "CVsvIY7IZ_hjf2zQoRsYhkifaUOdwPCNzYJg9rf__zs7XkaAI6cMr7DJAO8";
$body .= "&tx=$tx_token&at=$auth_token";
// post back to PayPal system to validate
$header = "POST /cgi-bin/webscr HTTP/1.0\r\n";
$header .= "Content-Type: application/x-www-form-urlencoded\r\n";
$header .= "Content-Length: " . strlen($body) . "\r\n\r\n";
// $fp = fsockopen ('www.paypal.com', 80, $errno, $errstr, 30);
// If possible, securely post back to paypal using HTTPS
// Your PHP server will need to be SSL enabled
$fp = fsockopen ('ssl://www.paypal.com', 443, $errno, $errstr, 30);

// make HTTP request
if (!$fp) 
{  echo "HTTP request failed!\n";  exit(1);  // HTTP ERROR
} else 
{ fputs ($fp, $header . $body);

  // read the body data
  $res = '';
  $headerdone = false;
  while (!feof($fp)) 
  { $line = fgets ($fp, 1024);
    if (strcmp($line, "\r\n") == 0) {
    // read the header
    $headerdone = true; }
    else if ($headerdone)
    { // header has been read. now read the contents
      $res .= $line;
    }
  }

  // parse the data
  $lines = explode("\n", $res);
  $keyarray = array();
  if (strcmp ($lines[0], "SUCCESS") == 0) 
  { for ($i=1; $i<count($lines);$i++){
    list($key,$val) = explode("=", $lines[$i]);
    $keyarray[urldecode($key)] = urldecode($val); }
    // check the payment_status is Completed
    // check that txn_id has not been previously processed
    // check that receiver_email is your Primary PayPal email
    // check that payment_amount/payment_currency are correct
    //  payment_gross,       [mc_currency] => USD
    // process payment
    $firstname = $keyarray['first_name'];
    $lastname = $keyarray['last_name'];
    $itemname = $keyarray['item_name'];
    $amount = $keyarray['mc_gross'];
  
    echo "<pre>\n";
    print_r($keyarray);
    echo "</pre>\n";
    echo ("<p><h3>Thank you for your purchase!</h3></p>");

    echo ("<b>Payment Details</b><br>\n");
    echo ("<li>Name: $firstname $lastname</li>\n");
    echo ("<li>Item: $itemname</li>\n");
    echo ("<li>Amount: $amount</li>\n");
    echo ("");
  }
  else if (strcmp ($lines[0], "FAIL") == 0) 
  { // log for manual investigation
  }
}  // end of handling
fclose ($fp);
?>

Your transaction has been completed, and a receipt for your purchase has been emailed to you.<br>You may log into your account at <a href='https://www.paypal.com'>www.paypal.com</a> to view details of this transaction.<br>
</body></html>
