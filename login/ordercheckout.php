<?php
require('checkLogin.php');
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image" href="images/ethenlogo.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <title>Checkout</title>
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #8dc5a4;">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.php"><img src="images/ethenlogo.png" width="60" height="30" class="d-inline-block align-top"></a>

    </div>
</nav>
<p id="test"></p>
<h1>Order Details</h1>
<br>
<div class="container">
<table class="table" id="checkoutTable" style="background-color: gray; color: whitesmoke">
    <thead>
    <th>Order #</th>
    <th>Company</th>
    <th>Item</th>
    <th>Quantity</th>
    <th>Address</th>
    </thead>
</table>
    <h3 id="date"></h3>
    <button id="print" class="btn btn-primary" onclick="window.print();return false;">Print</button>
</div>

<script src="scripts/ordercheckout.js"></script>
</body>
</html>