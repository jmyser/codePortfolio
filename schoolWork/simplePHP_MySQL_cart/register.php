<?php require("loginfns.php"); /* BK: https() removed,
 *  as it may cause connection privacy errors in some browsers */
      require_once("regfns.php");
      require_once("connectDB.php");
      require_once("adduser.php");

$title="New Member Account"; 
$css=array("basic.css", "form.css");
$js=array("register.js");
require("rfront.php");
if ( empty($_POST['submit']) )  require("regform.php");
elseif( check_reg_data() && add_member() )
{ ?>
<h2>Login Registration Confirmation</h2>
<p>Thanks. we your login account has been created.</p>
<?php } else { ?>
<h2>Login Registration Failed</h2>
<p class="error">Sorry we failed to register your login account.</p>
<p class="error"><?php echo $errmsg; ?></p>
<p><a href="">try again</a></p>
<?php } ?>
<?php require("rback.php"); ?>
