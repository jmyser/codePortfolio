<?php

/* PHP file to include for programs that connect
 * to a database on localhost
 */

$host = "localhost";
$user = "lab5"; // change to real user information
$pass = "dbValid3456!"; 
$dbname = "jermyser";

$db_obj = new mysqli($host, $user, $pass, $dbname);
if (mysqli_connect_errno()) {
    printf("Can't connect to $host $dbname. Errorcode: %s\n",
            mysqli_connect_error());
    exit;
}