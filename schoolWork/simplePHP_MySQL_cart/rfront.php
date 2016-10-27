<?php  /////   rfront.php    /////
require_once("tempfns.php"); ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" 
                   lang="en" xml:lang="en">
<head><meta charset="utf-8"/>
<title><?php echo $title; ?></title>
<?php addCss($css); addJs($js); addAny($any);
      addFromFile($srcfile);
?>
</head><body>
<div id="centerpage">
<section id="main">
<?php $page=basename($_SERVER['PHP_SELF']);
      require_once("navbar.php"); ?>
<article id="content">
