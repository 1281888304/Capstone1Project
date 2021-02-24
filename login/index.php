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
    <link rel="icon" type="image" href="images/ethenlogo.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-database.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-analytics.js"></script>
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
    <th>Address</th>
    </tr>
    </thead>
</table><br>
    <h2>Add Order</h2>
</div>
<div class="container">
    <div class="mb-5 row">
        <label for="company" class="col-sm-2 col-form-label">Company</label>
        <div class="col-sm-5">
            <input type="text" class="form-control-sm" id="company">
        </div>
    </div>
    <div class="mb-5 row">
        <label for="item" class="col-sm-2 col-form-label">Item</label>
        <div class="col-sm-5">
            <select class="form-select" id="item">
                <option>----</option>
                <option value="Tea extract">Tea extract</option>
                <option value="Orange Syrup">Orange Syrup</option>
                <option value="Passion Fruit Syrup">Passion Fruit</option>
                <option value="Honeydew Syrup">Honeydew Syrup</option>
                <option value="White Peach Popping">White Peach Popping</option>
            </select>
        </div>
    </div>
    <div class="mb-5 row">
        <label for="quantity" class="col-sm-2 col-form-label">Quantity</label>
        <div class="col-sm-5">
            <input type="text" class="form-control-sm" id="quantity">
        </div>
    </div>
    <div class="mb-5 row">
        <label for="address" class="col-sm-2 col-form-label">Address</label>
        <div class="col-sm-5">
            <input type="text" class="form-control-sm" id="address">
        </div>
    </div>
    <button type="submit" class="btn-primary" id="addOrder">Add</button>
    <button type="submit" class="btn-primary" id="update">Update</button>
<!--    <form class="form-group">-->
<!--        <label for="company">Company</label>-->
<!--        <input type="text" class="form-check-input" id="company"><br>-->
<!--        <label for="item" >Item</label>-->
<!--        <select class="form-select" id="item">-->
<!--            <option>----</option>-->
<!--            <option value="Tea extract">Tea extract</option>-->
<!--            <option value="Orange Syrup">Orange Syrup</option>-->
<!--            <option value="Passion Fruit Syrup">Passion Fruit</option>-->
<!--            <option value="Honey Dew Syrup">Honey Dew Syrup</option>-->
<!--            <option value="White Peach Popping">White Peach Popping</option>-->
<!--        </select>-->
<!--    </form>-->
</div>
<script src="scripts/firebase.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>