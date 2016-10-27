<?php  session_start();
$realm="Super Club";

require_once("loginfns.php");
require_once("connectDB.php");
require_once("authenticate.php");
if ( !empty($_POST['submit']) && $_POST['submit']=="Login"
     && !empty($_SESSION['target']) )
{// process login
   if ( authenticate($_POST['uid'],$_POST['pass']) ) //check_pass($_POST['uid'],$_POST['pass'],$PASSWORD_FILE)
   {// login success
      $_SESSION['user']=$_POST['uid'];
      login_fn();
      header("Location: " . $_SESSION['target']);
      exit;
   }
   else  
   {// login failed
      $title="Login Failed"; 
      $css=array("basic.css", "form.css");
      require("rfront.php");
      echo "<h2 class='error'>Incorrect Userid/Password</h2>";
      echo "<p class='error'>Please try again.</p>";
      require_once("loginform.php");
   } 
}
else // new login
{  require_once("sessionfns.php");
   /* BK: https() removed,
    *  as it may cause connection privacy errors in some browsers */
   new_session(); logout_fn(); 
   if ( !empty($_REQUEST['target']) )
   {  $_SESSION['target']=$_REQUEST['target']; }
   else
   {  $_SESSION['target']='memberarea.php'; } // BK
   $title="Login"; 
   $css=array("basic.css", "form.css");
   require_once("rfront.php");
   require_once("loginform.php");
}
require_once("rback.php"); 
?>
