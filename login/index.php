<?php
/**
 *  File name & path
 *  Author
 *  Date
 *  Description
 */
require('checkLogin.php');
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-database.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-analytics.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-firestore.js"></script>

</head>
<body>

<div class="container">
    <?
    include ("navbar.html");
    ?>
</div>
<div class="container">
<table class="table" id="ordersTable" style="background-color: gray; color: whitesmoke">
    <thead>
    <tr>
    <th>Order #</th>
    <th>Company</th>
    <th>Item</th>
    <th>Quantity</th>
    </tr>
    </thead>
</table>
</div>

<script src="scripts/firebase.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>