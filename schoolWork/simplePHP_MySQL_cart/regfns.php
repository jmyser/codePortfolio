<?php  /////    regfns.php    /////
require_once("loginfns.php");
define("ID_LEN",5);
define("PASS_LEN",8);
$errmsg="";

function nomatch(&$old,&$new,&$err)
{  if ( $old==$new )
   {  $err .= "New and old passwords are the same!";
      return false;
   }
   return true;
}

function id_check()
{ global $errmsg,$PASSWORD_FILE;
  if( empty($_POST['uid']) )
  { $errmsg .= "The userid is empty.";  return false; }
  if( strlen($_POST['uid']) < ID_LEN )
  { $errmsg .= "The userid ". $_POST['uid'] . " is too short.";
    return false;
  }
  if( check_user_db() )  //$_POST['uid'],$PASSWORD_FILE
  { $errmsg = "The userid " . $_POST['uid'] . 
           " exists. Please use a different userid.";
    return false;
  }
  return true;
}

function check_user_db() 
{
  global $db_obj;
  $userid=$db_obj->escape_string($_REQUEST['uid']); 
  $query = "SELECT * FROM member WHERE uid='$userid'";
  $result_obj = $db_obj->query($query);
  if ($result_obj->num_rows == 0)
  {
    return false;
  }
  return true;
}

function pass_check(&$pass)
{ global $errmsg;
  if( empty($pass) )
  { $errmsg .= "The password is empty.";  return false; }
  if( strlen($pass) < PASS_LEN )
  { $errmsg .= "The password is too short.";
    return false;
  }
  return true;
}

function email_check(&$email)
{ global $errmsg;
  if( empty($email) )
  { $errmsg .= "The email is empty.";  return false; }
  return true;
}

function pass_match(&$pass,&$pass2)
{ global $errmsg;
  if( $pass != $pass2 )
  { $errmsg .= "The passwords don't match.";  return false; }
  return true;
}

function record_pass()
{ global $errmsg, $PASSWORD_FILE;
  if(! add_passwd($_POST['uid'], $_POST['pass'], 
            $PASSWORD_FILE, $_POST['email']))
  { $errmsg .= "Failed to store password.";  return false; }
  return true;
}

function check_reg_data()
{ global $errmsg;
  id_check($_POST['uid']);
  pass_check($_POST['pass']);
  pass_match($_POST['pass'],$_POST['pass2']);
  email_check($_POST['email']);
  return (empty($errmsg)) ;
}
