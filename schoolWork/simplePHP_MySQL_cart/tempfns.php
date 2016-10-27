<?php   /////    tempfns.php    /////
function addCss(&$arr)
{ if ( isset($arr) )
  { foreach($arr as $file)
    { echo '<link rel="stylesheet" type="text/css" href="'
           . $file .  "\" />\n";
    }
    reset($arr);
  }
}
function addJs(&$arr)
{ if ( isset($arr) )
  { foreach($arr as $file)
    {  echo '<script type="text/javascript" src="' 
            . $file .  "\"></script>\n";
    }
    reset($arr);
  }
}
function addAny(&$arr)
{ if ( isset($arr) )
  { foreach($arr as $str) 
    {  echo "$str\n"; }
    reset($arr);
  }
}
function addFromFile(&$arr)
{ if ( isset($arr) )
  { foreach($arr as $file)
    {  require($file);  }
  }
}
?>
