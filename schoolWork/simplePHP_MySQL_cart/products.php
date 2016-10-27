<?php session_start();
if ( empty($_SESSION['user']) )
{  header("Location: login.php?target=" . $_SERVER['PHP_SELF']);
   exit;
}

//$tmpcartarray = array();
if(!empty($_POST['products'])) {
    foreach($_POST['products'] as $check) {
    	$_SESSION['cart'][$check]=1;
    }
}

var_dump($_SESSION['cart']);


require_once("connectDB.php");
global $db_obj;
$query = "SELECT * FROM Product";
$result_obj = $db_obj->query($query);


$title="Products"; 
$css=array("basic.css", "form.css", "page.css");
require("rfront.php");
?>
<h2>Products</h2>
<form method="POST" name="products[]" action="products.php"> 
<table class="db">
	<tr>
    <th>id</th>
    <th>description</th> 
    <th>price</th>
    <th>select</th>
  </tr>
<?php
if ($result_obj)
{
	while($row = mysqli_fetch_assoc($result_obj)) {
		$prodID = $row["id"]; 
        $prodDESC = $row["description"]; 
        $prodPRICE = $row["price"];
        echo "<tr>";
        echo "<td>$prodID</td>";
        echo "<td>$prodDESC</td>";
        echo "<td>$prodPRICE</td>";
        echo "<td><input type='checkbox' name='products[]' value='$prodID'></td>";
        echo "</tr>";
	}
}

?>
</table>
<input type="submit" value="Add to cart" />
</form>



<?php require("rback.php"); ?>
