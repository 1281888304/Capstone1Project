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
// var admin = require("firebase-admin");

// Get a database reference to our posts
var Objcompany = document.getElementById("company");
var Objitem =document.getElementById("item");
var Objquantity = document.getElementById("quantity");
var Objaddress = document.getElementById("address");
var num=0;
var submitbtn = document.getElementById("addOrder")
var updatebtn = document.getElementById("update");
var db = firebase.database();
var ref = db.ref("orders");
const ordertable = document.getElementById("ordersTable")
let tbody = document.createElement("tbody");
// Attach an asynchronous callback to read the data at our posts reference
ref.once("value", function(snapshot) {
    console.log(snapshot.val());
    let ss = snapshot.val();
    // for (let i = 0; i < snapshot.val().length; i++) {
    Object.keys(ss).forEach(function (order){
        let tr = document.createElement("tr");
        let ordernum = document.createElement("th");
        let company = document.createElement("td");
        let item = document.createElement("td");
        let quantity = document.createElement("td");
        let address = document.createElement("td");
        let editbtn = document.createElement('button');
        let delbtn = document.createElement('button');
        editbtn.innerHTML="Edit";
        delbtn.innerHTML = "Delete";
        delbtn.className = 'btn btn-primary';
        editbtn.className='btn btn-primary';
        var key = ss[order].order_num;
        editbtn.value=key;
        delbtn.value = key;
        editbtn.addEventListener("click", function (){editOrder(editbtn)});
        delbtn.addEventListener("click",function(){delOrder(delbtn)});
        ordernum.innerHTML = ss[order].order_num;
        company.innerHTML = ss[order].company;
        item.innerHTML = ss[order].item;
        quantity.innerHTML = ss[order].quantity;
        address.innerHTML=ss[order].address;
        tr.append(ordernum);
        tr.append(company);
        tr.append(item);
        tr.append(quantity);
        tr.append(address);
        tr.append(editbtn);
        tr.append(delbtn);
        tbody.append(tr);
    num++;
    });
    ordertable.append(tbody);
}, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
});
submitbtn.addEventListener("click",addOrder)
function addOrder(){
    console.log(num);
    var newOrder = ref.child(num);
    newOrder.set({
        "order_num": num+1,
        "company": document.getElementById("company").value,
        "item": document.getElementById("item").value,
        "quantity": document.getElementById("quantity").value,
        "address": document.getElementById("address").value,
    })
    console.log(newOrder.key);
    console.log(document.getElementById("item").value);
    location.reload(true);
}
function editOrder(order){
    var ordernum = order.value;
    console.log(ordernum);
    ref.child(ordernum-1).once('value').then(function(snapshot){
        console.log(snapshot.val());
        editsnap = snapshot.val();

        Objcompany.value = editsnap.company;
        Objitem.value = editsnap.item;
        Objquantity.value = editsnap.quantity;
        Objaddress.value = editsnap.address;
        updatebtn.addEventListener("click",function(){
           ref.child(ordernum-1).update({'order_num':ordernum, 'company': Objcompany.value,
           'item':Objitem.value, 'quantity':Objquantity.value,'address':Objaddress.value});
            location.reload();
        });

    })


}
function delOrder(order){
    var confirmation = confirm("Are you sure you want to delete this order?");
    if(confirmation){
    var ordernum = order.value;
    ref.child(ordernum-1).remove();
    }
    location.reload(true);
}