<?php 
$formErr=array();  // key=>error array
//// $check_list  // global array of fields to check 
//  $check_list['key']="require"  for a required non-empty field
//  $check_list['key']="check_fn"  for any field to pass checking by check_fn
//    each check_fn returns "OK" or and error msg string.

function formdata_check(&$fd)  
{  global $formErr, $check_list;
   $pass=1; reset($check_list);
   foreach($check_list as $key=>$val)
   {  if ( $val == "require" )
      {  if ( empty($fd[$key]) )  
         { $pass=0;
           $formErr[$key]="please fill in";  
         }
      }
      else
      {  if ( ($s=$val($fd[$key])) !="OK" )  
         { $formErr[$key]=$s; $pass=0; }
      }
   }
   return $pass;
}

// code producing functions
function text($key) 
{  if ( !empty($_REQUEST[$key]) )
   {  echo 'name="' . $key . '" id="' . $key . 
           '" value="' .$_REQUEST[$key] . '" ';  
   }
   else
   {  echo 'name="' . $key . '" id="' . $key .'" ';  }
}

function radio($key, $val) 
{  echo 'name="' . $key . '" value="' . $val . '" '; 
   if ( !empty($_REQUEST[$key]) )
   {  if (  $val == $_REQUEST[$key] )
      {  echo ' checked=""';  }
   }
}

function check($key, $val) 
{  echo 'name="' . $key . '[]" value="' . $val . '" ';
   if ( !empty($_REQUEST[$key]) && is_array($arr=$_REQUEST[$key]))
   {  if ( in_array($val, $arr) )  
      {  echo ' checked="" ';  }
   }
}

function genLabel($key, $label)
{ global $formErr;
  echo "<label for=\"$key\"";
  if ( !empty($formErr[$key]) )
  {  echo ' class="fixthis" ';  }
  echo ">" . $label . "</label>";
}

function genErr($key)
{ global $formErr;
  if ( !empty($formErr[$key]) )
  {  echo '<br /><span class="fixthis">' 
          . $formErr[$key] . "</span>";  }
}
?>
