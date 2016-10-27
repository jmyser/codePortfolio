<?php session_start();
if ( empty($_SESSION['user']) )
{  header("Location: login.php?target=" . $_SERVER['PHP_SELF']);
   exit;
}

$title="Member Area"; 
$css=array("basic.css", "form.css");
require("rfront.php");
?>
<h2>Welcome back <?php echo $_SESSION['user']; ?></h2>
<p>Here is your member-only area.</p>
<?php require("rback.php"); ?>
