var firebaseConfig = {
    apiKey: "AIzaSyDORaW-nvBGpy-PSVQfEpBGBpUagdD2tFs",
    authDomain: "ethen-35316.firebaseapp.com",
    databaseURL: "https://ethen-35316-default-rtdb.firebaseio.com",
    projectId: "ethen-35316",
    storageBucket: "ethen-35316.appspot.com",
    messagingSenderId: "718698175023",
    appId: "1:718698175023:web:e34f88c9e0673fee9804da",
    measurementId: "G-75K8VPXH0N"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();
// Get a reference to the storage service, which is used to create references in your storage bucket
var storage = firebase.storage().ref();
var dbref = firebase.database().ref("Product");
var productName = document.getElementById("product_name");
var price = document.getElementById("price");
var measurement = document.getElementById("measurement");
var category = document.getElementById("item");

var measurement = document.getElementById("measurement");

document.getElementById("addProduct").addEventListener("click",uploadImage);
// document.getElementById("addProduct").addEventListener("click",window.setTimeout(addProduct,5000));
function uploadImage() {
    var productName = document.getElementById("product_name").value;
    var price = document.getElementById("price").value;
    var measurement = document.getElementById("measurement").value;
    var category = document.getElementById("item").value;
    var file = document.getElementById("files").files[0];
    var thisRef = storage.child(productName);
    thisRef.put(file).then(function (snapshot) {
        // alert("Image uploaded")
        console.log("Uploaded a file");
    })
}
    function addProduct(){
    var childNumber = 0;
    var productName = document.getElementById("product_name").value;
    var price = document.getElementById("price").value;
    var measurement = document.getElementById("measurement").value;
    var category = document.getElementById("item").value;
    var productdb = dbref.child(category);
    var imgUrl;
    storage.child(productName).getDownloadURL()
        .then((url) => {
            console.log(url);
            imgUrl = url;

    productdb.once("value").then(function (snapshot){
        childNumber = snapshot.numChildren()+1;
        console.log(childNumber);
    var newproduct = productdb.child(category.charAt(0).toLowerCase()+category.slice(1)+childNumber);
    var imgkey = category.charAt(0).toLowerCase()+category.slice(1)+"Image";
    var namekey = category.charAt(0).toLowerCase()+category.slice(1)+"Name";
    var numkey = category.charAt(0).toLowerCase()+category.slice(1)+"Num";
    var prckey = category.charAt(0).toLowerCase()+category.slice(1)+"Price";
    var ttlekey = category.charAt(0).toLowerCase()+category.slice(1)+"Title";
    newproduct.set({
       [imgkey]: url,
       [namekey] : productName,
       [numkey]: "1",
       [prckey] : price+"/"+measurement,
       [ttlekey] : category.charAt(0).toLowerCase()+category.slice(1)+childNumber
    });
    })
        })

        .catch((error) => {
            console.log("ERROR")
        });
        alert("Product added")
        location.reload();
    }

dbref.child("Powder").once("value",function (snapshot){
    let counter = 1;
    let ss = snapshot.val();
    Object.keys(ss).forEach(function (product){
        console.log(ss);
        let table = document.getElementById("powders");
        let tr = document.createElement("tr");
        let productname = document.createElement("td");
        let productimage = document.createElement("img");
        let image = document.createElement("td");
        let price = document.createElement("td");
        let editbtn = document.createElement('button');
        let delbtn = document.createElement('button');
        delbtn.addEventListener("click",function(){delProduct(delbtn)});
        editbtn.addEventListener("click", function (){editProduct(editbtn)});
        editbtn.innerHTML="Edit";
        delbtn.innerHTML = "Delete";
        delbtn.className = 'btn btn-primary';
        editbtn.className='btn btn-primary';
        productname.innerHTML = ss[product].powderName;
        price.innerHTML = ss[product].powderPrice;
        productimage.src = ss[product].powderImage;
        productimage.className = "img-thumbnail";
        var key = "powder"+counter;
        console.log(key);
        editbtn.value=key;
        delbtn.value = key;
        image.append(productimage);
        tr.append(productname)
        tr.append(image)
        tr.append(price);
        tr.append(editbtn);
        tr.append(delbtn);
        table.append(tr);
        counter++;
    })
})
dbref.child("Syrup").once("value",function (snapshot){
    let counter = 1;
    let ss = snapshot.val();
    Object.keys(ss).forEach(function (product){
        console.log(ss);
        let table = document.getElementById("syrups");
        let tr = document.createElement("tr");
        let productname = document.createElement("td");
        let productimage = document.createElement("img");
        let image = document.createElement("td");
        let price = document.createElement("td");
        let editbtn = document.createElement('button');
        let delbtn = document.createElement('button');
        delbtn.addEventListener("click",function(){delProduct(delbtn)});
        editbtn.addEventListener("click", function (){editProduct(editbtn)});
        editbtn.innerHTML="Edit";
        delbtn.innerHTML = "Delete";
        delbtn.className = 'btn btn-primary';
        editbtn.className='btn btn-primary';
        productname.innerHTML = ss[product].syrupName;
        price.innerHTML = ss[product].syrupPrice;
        productimage.src = ss[product].syrupImage;
        productimage.className = "img-thumbnail";
        var key = "syrup"+counter;
        console.log(key);
        editbtn.value=key;
        delbtn.value = key;
        image.append(productimage);
        tr.append(productname)
        tr.append(image)
        tr.append(price);
        tr.append(editbtn);
        tr.append(delbtn);
        table.append(tr);
        counter++;
    })
})
dbref.child("Topping").once("value",function (snapshot){
    let counter = 1;
    let ss = snapshot.val();
    Object.keys(ss).forEach(function (product){
        console.log(ss);
        let table = document.getElementById("toppings");
        let tr = document.createElement("tr");
        let productname = document.createElement("td");
        let productimage = document.createElement("img");
        let image = document.createElement("td");
        let price = document.createElement("td");
        let editbtn = document.createElement('button');
        let delbtn = document.createElement('button');
        delbtn.addEventListener("click",function(){delProduct(delbtn)});
        editbtn.addEventListener("click", function (){editProduct(editbtn)});
        editbtn.innerHTML="Edit";
        delbtn.innerHTML = "Delete";
        delbtn.className = 'btn btn-primary';
        editbtn.className='btn btn-primary';
        productname.innerHTML = ss[product].toppingName;
        price.innerHTML = ss[product].toppingPrice;
        productimage.src = ss[product].toppingImage;
        productimage.className = "img-thumbnail";
        var key = "topping"+counter;
        console.log(key);
        editbtn.value=key;
        delbtn.value = key;
        image.append(productimage);
        tr.append(productname)
        tr.append(image)
        tr.append(price);
        tr.append(editbtn);
        tr.append(delbtn);
        table.append(tr);
        counter++;
    })
})
function delProduct(product){
    console.log(product.value.charAt(0).toUpperCase()+product.value.slice(1,product.value.length-1));
    dbref = dbref.child(product.value.charAt(0).toUpperCase()+product.value.slice(1,product.value.length-1));
    console.log(dbref);
    //finds the order from the database and deletes it
    var confirmation = confirm("Are you sure you want to delete this order?");
    if(confirmation){
        var productnum = product.value;
        console.log(dbref.child(productnum));
        dbref.child(productnum).remove();
    }
    location.reload(true);
}
function editProduct(product){
    //finds the order and fills up the fields on the add order section with the data.
    //and updates the database with the data that was on the field when the admin clicks the update button.
    var productnum = product.value;
    var updatebtn = document.getElementById("updatebtn");
    dbref = dbref.child(product.value.charAt(0).toUpperCase()+product.value.slice(1,product.value.length-1));
    dbref.child(productnum).once('value').then(function(snapshot){
        console.log(snapshot.val());
        editsnap = snapshot.val();
        let producti = product.value.slice(0,product.value.length-1)+"Image";
        let productn = product.value.slice(0,product.value.length-1)+"Name";
        let productp = product.value.slice(0,product.value.length-1)+"Price";
        let productnu = product.value.slice(0,product.value.length-1)+"Num";
        let productt = product.value.slice(0,product.value.length-1)+"Title";
        console.log(productn);
        productName.value = editsnap[productn];
        price.value = editsnap[productp];
        updatebtn.addEventListener("click",function(){
            dbref.child(productnum).update({[producti] : editsnap[producti],[productn] : productName.value, [productnu] : editsnap[productnu],
            [productp] : price.value+'/'+measurement.value, [productt] : editsnap[productt]});
            location.reload();
        });

    })


}
