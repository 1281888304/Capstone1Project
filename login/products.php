<?php
require('checkLogin.php');
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image" href="images/ethenlogo.png">
    <link rel="stylesheet" href="css/products.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://www.gstatic.com/firebasejs/8.4.1/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.4.1/firebase-analytics.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-database.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-analytics.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-firestore.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.1.2/firebase-storage.js"></script>
    <title>Products</title>
</head>
<body>
<div class="container-fluid">
    <?
    include ("navbar.html");
    ?>
</div>
<div class="container-fluid">
    <h1>Products</h1>
<div class="container">
    <div class="container">
    <br><h3>Add a product</h3>
    <label for="files">Image:</label><br>
    <input type="file" id="files" name="files[]" multiple />
        <div class="mb-2 row mt-2">
            <label for="product_name" class="col-sm-2 col-form-label">Product name</label>
            <div class="col-sm-5">
                <input type="text" class="form" id="product_name">
            </div>
        </div>
            <div class="mb-2 row">
            <label for="price" class="col-sm-2 col-form-label">Price</label>
            <div class="col-sm-5">
                <input type="text" class="form" id="price">
            </div>
            </div>
        <div class="mb-2 row">
            <label for="price" class="col-sm-2 col-form-label">Unit of Measurement</label>
            <div class="col-sm-5">
                <input type="text" class="form" id="measurement">
            </div>
        </div>
        <div class="m-1 row">
        <label for="item">Category</label>
            <div class="col-sm-5">
        <select class="form-select" id="item">
            <option>----</option>
            <option value="Powder">Powder</option>
            <option value="Topping">Topping</option>
            <option value="Syrup">Syrup</option>
        </select>
            </div>
        </div>
        <button type="submit" id="addProduct" class="btn-primary" onclick="setTimeout(addProduct,3000)">Add</button>
        <button type="submit" class="btn-primary" id="updatebtn">Update</button>
        </div>

</div>
    <div class="container-md">
<h4>Powders</h4>
<table class="table table-md" id="ordersTable" style="background-color: gray; color: whitesmoke">
    <thead>
    <tr>
        <th>Product Name</th>
        <th>Product Image</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody id="powders">

    </tbody>
</table>
    </div>
    <div class="container-md">
<h4>Toppings</h4>
<table class="table table-md" id="ordersTable" style="background-color: gray; color: whitesmoke">
    <thead>
    <tr>
        <th>Product Name</th>
        <th>Product Image</th>
        <th>Price</th>
        <th></th>
    </tr>
    </thead>
    <tbody id="toppings">

    </tbody>
</table>
    </div>
    <div class="container-md">
    <h4>Syrup</h4>
    <table class="table table-md" id="ordersTable" style="background-color: gray; color: whitesmoke">
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Product Image</th>
            <th>Price</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="syrups">

        </tbody>
    </table>
    </div>
</div>
</body>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="scripts/products.js"></script>
</html>
