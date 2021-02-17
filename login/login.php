<?php
/**
 *  File name & path
 *  Author
 *  Date
 *  Description
 */
//Turn on error reporting -- this is critical!
ini_set('display_errors', 1);
error_reporting(E_ALL);
//start session
session_start();
$err = false;
$username = "";
//if the form has been submitted
if(!empty($_POST)){
    $username = $_POST['username'];
    $password = $_POST['password'];
//    echo $username. " ". $password;
    //Dummy variables
    $user = 'admin';
    $pass = 'password01';
    if($username == $user && $password == $pass){
        //store username in the session array.
        $_SESSION['un'] = $username;
        $page = isset($_SESSION['page']) ? $_SESSION['page'] : "index.php";
        header("location: ".$page);
    }
    else{
        $err = true;
    }
}
//var_dump($_POST);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <style>
        .err {
            color: darkred;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="container-fluid">
        <img src="images/ethenlogo.png"class="mx-auto d-block">
        <h1>Login</h1>
    </div>
    <form action="#" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="<?php echo $username;?>">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" >
        </div>
        <?php
        if($err){
        echo '<span class="err">Incorrect login</span><br>';
        }
        ?>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>

</div>

<script src="//code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>