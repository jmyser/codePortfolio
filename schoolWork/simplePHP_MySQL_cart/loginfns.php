<?php
$PASSWORD_FILE="passwd_file.php";

function login_fn() {  touch("login.mark");  }
function logout_fn() 
{  if ( file_exists("login.mark") )
      unlink("login.mark"); 
}

function https()
{   if (!isset($_SERVER['HTTPS']) || $_SERVER['HTTPS']!="on")
    {   header("Location: https://" . $_SERVER['SERVER_NAME']
         .  $_SERVER['REQUEST_URI']);
        exit;  // back to browser
    }
}

function http()
{   if (isset($_SERVER['HTTPS']) && $_SERVER['HTTPS']=="on")
    {   header("Location: http://" . $_SERVER['SERVER_NAME']
         .  $_SERVER['REQUEST_URI']);
        exit;  // back to browser
    }
}

function change_passwd(&$id,&$oldpass,&$pass,&$file,&$err)
{ if (! check_pass($id,$oldpass,$file)) // double check login
  { $err .= "Old password wrong."; return false;  }
  $done=false;
  $lines=file($file);
  if ( $f=@fopen($file, "w") )
  { foreach($lines as $line)
    { if ( !$done && strstr($line, "$id:") )
      { $pass_hash=crypt($pass, '$2a$09$dynamicwebdesign$');
        preg_match("/(.*):/", $line, $matches);
        $prefix = $matches[1];
        $line="$prefix:" . $pass_hash . "\n";
        $done=true;
      }
      fwrite($f, $line);
    }
    fclose($f);
  }
  else {  $err .= "Cannot open password file.";  }
  return $done;
}  // change_email is similar

function check_pass(&$id,&$pass,&$file)
{ if (!($authUserLine = check_user($id,$file)))
     return false;// no entry for user
  // echo $authUserLine . "\n";
  preg_match("/$id:(.*)$/", $authUserLine, $matches);
  $ps = $matches[1];
  return (crypt($pass, $ps) == $ps);
}

function check_user(&$id,&$file)
{ $ar=preg_grep("/$id:.*$/", file($file)); 
  return array_shift($ar);
}

function get_email(&$id,&$file)
{  if ( $authUserLine=check_user($id,$file) )
   { preg_match("/(.*):$id:/", $authUserLine, $matches);
     $email = $matches[1];
     return $email;
   }
}

function add_passwd(&$id,&$pass,&$file,$email="")
{ if ( check_user($id,$file) ) return false;  // user exists
  $pass_hash=crypt($pass, '$2a$09$dynamicwebdesign$');
  $f=@fopen($file, "a");
  if ($f)
  { fwrite($f, "// $email:" . "$id:" . $pass_hash . "\n");
    fclose($f);
    return true;
  }
  return false;
}
//// PHP random password generator
//// based on code from webtoolkit.info

function gen_pass($length=8, $strength=2) 
{  $vowels = 'aeuyj9753';
   $consonants = 'bdghmnpqrstvz-';
   if ($strength & 1) 
   { $consonants .= 'BDGHLMNPQRSTVWXZ_'; }
   if ($strength & 2) 
   { $vowels .= "YUEAJ2468"; }
   if ($strength & 4) 
   { $consonants .= ',.#'; $vowels .= '@$%'; }
   $password = '';
   $c_max=strlen($consonants)-1;
   $v_max=strlen($vowels)-1;
   $alt = time() % 2;
   for ($i = 0; $i < $length; $i++) 
   { $password .= 
       $alt ? $consonants[rand(0,$c_max)] 
            : $vowels[rand(0,$v_max)];
     $alt = !$alt;
   }
   return $password;
}
///  echo gen_pass() . "\n";
/// add_passwd("pswang","opensesame", $PASSWORD_FILE, "pswang@kent.edu");
/// 
///  if ( check_pass("pwang","opensesame",$PASSWORD_FILE) )
///     echo "password check success!\n";
///  else
///     echo "password check failed!\n";  
?>
