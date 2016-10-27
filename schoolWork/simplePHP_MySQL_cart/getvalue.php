<?php
function value_from_db($q)
{   global $db_obj;
    $r = $db_obj->query($q);
    if (  $r && $r->num_rows == 1 )
    {   $row = $r->fetch_row();
        return($row[0]);
    }
    else return FALSE;
}

// require_once("mysqli.php");
require_once("connectDB.php");

$q="SELECT zip5 from customer where lastname='Wang'";
echo value_from_db($q);
?>
