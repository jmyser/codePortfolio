<?php
function add_member()
{ global $db_obj;
  global $errmsg;
  $uid=$db_obj->escape_string($_REQUEST['uid']);      // (A)
  $lastname=$db_obj->escape_string($_REQUEST['lastname']);
  $firstname=$db_obj->escape_string($_REQUEST['firstname']);
  $email=$db_obj->escape_string($_REQUEST['email']);
  $password=$db_obj->escape_string($_REQUEST['pass']);  // (B)
  $address=$db_obj->escape_string($_REQUEST['address']);
  $state=$db_obj->escape_string($_REQUEST['state']);
  $zip=$db_obj->escape_string($_REQUEST['zip']);
  $query = "INSERT INTO member "
          . "(uid, lastname, firstname, email, password, address, state, zip) "
          . "VALUES ('$uid', '$lastname', '$firstname', '$email', PASSWORD('$password'), '$address', '$state', '$zip')";
  $result_obj = $db_obj->query($query);
  if( !$result_obj )
  { $errmsg = "Insert failed: " . $query;  return false; }
  return true;
}
?>