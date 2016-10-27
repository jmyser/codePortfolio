function checkpass(p1, p2)
{   p1=document.getElementById(p1);
    p2=document.getElementById(p2);
    if ( p1.value != p2.value )
    {  alert("passwords do not match");
       p2.value="";
    }
}
