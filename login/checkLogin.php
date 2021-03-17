<?php
//echo "<pre>";
//var_dump($_SERVER);
//echo "</pre>";
//Turn on error reporting -- this is critical!
ini_set('display_errors', 1);
error_reporting(E_ALL);
//see if the user logged in
session_start();
if(!isset($_SESSION['un'])){
    $_SESSION['page'] = $_SERVER["SCRIPT_URI"];
    header("location: login.php");
}
//else{
//    header('location: ordercheckout.php');
//}
//header redirects to the login page.
//header must come first before any html.
//header("location: login.php");

