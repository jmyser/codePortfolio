<?php
function authenticate($uid, $pass)
{  global $db_obj;
   $userid=$db_obj->escape_string($uid);
   $pass=$db_obj->escape_string($pass);
   $query="SELECT * FROM member WHERE uid ='$uid'"
     . " AND password = PASSWORD('$pass')";            // (F)

   if ( ($result = $db_obj->query($query))           // (G)
            && $result->num_rows == 1 )
   {  return $uid;  }
   else
   {  return "";  }
}
?>
