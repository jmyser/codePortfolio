<?php /////   sessionfns.php    /////
// functions you call after session_start();

function session_end()    // ends current session
{  foreach ($_SESSION as $key=>$val)
   {  unset($_SESSION[$key]);  } // empties session state
   session_destroy();     // release all session resources
}

// ends current session and starts a new one
function new_session()
{  session_end(); session_start();   }

// little need for this
function cancel_session_cookie()
{  if (isset($_COOKIE["PHPSESSID"]))  // cancels sessionid cookie
   { setcookie("PHPSESSID", '', time()-3600, '/'); }
}
?>
