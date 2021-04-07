//Winter 2021 Capstone 1
//Ethen Foods mobile/web app
//Author: Aaron Tadina, Chunhai Yang, Qinghang Zhang
//firebase token
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
var processedtable = document.getElementById('processedTable');
const ordertable = document.getElementById("ordersTable")
let tbody = document.createElement("tbody");
let protbody = document.createElement("tbody");
// Attach an asynchronous callback to read the data at our posts reference
//creates elements that will be filled by the data from the database and be printed out
//as a datatable on the admin page.
ref.orderByChild('status').equalTo('Pending').once("value", function(snapshot) {
    let ss = snapshot.val();
    Object.keys(ss).forEach(function (order){
        let tr = document.createElement("tr");
        let ordernum = document.createElement("th");
        let company = document.createElement("td");
        let item = document.createElement("td");
        let quantity = document.createElement("td");
        let address = document.createElement("td");
        let status = document.createElement("td");
        let editbtn = document.createElement('button');
        let delbtn = document.createElement('button');
        let processbtn = document.createElement('button');
        editbtn.innerHTML="Edit";
        delbtn.innerHTML = "Delete";
        processbtn.innerHTML = "Process";
        delbtn.className = 'btn btn-primary';
        editbtn.className='btn btn-primary';
        processbtn.className = 'btn btn-primary';
        var key = ss[order].order_num;
        editbtn.value=key;
        delbtn.value = key;
        processbtn.value = key;
        editbtn.addEventListener("click", function (){editOrder(editbtn)});
        delbtn.addEventListener("click",function(){delOrder(delbtn)});
        processbtn.addEventListener("click",function (){processOrder(processbtn)});
        ordernum.innerHTML = ss[order].order_num;
        company.innerHTML = ss[order].company;
        item.innerHTML = ss[order].item;
        quantity.innerHTML = ss[order].quantity;
        address.innerHTML=ss[order].address;
        status.innerHTML = ss[order].status;
        tr.append(ordernum);
        tr.append(company);
        tr.append(item);
        tr.append(quantity);
        tr.append(address);
        tr.append(status);
        tr.append(editbtn);
        tr.append(delbtn);
        tr.append(processbtn);
        tbody.append(tr);
    num++;
    });
    ordertable.append(tbody);
}, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
});
//creates elements that will be filled by the data from the database and be printed out
//as a datatable on the processed table on the admin page.
ref.orderByChild('status').equalTo('Processed').once("value", function(snapshot) {
    let processedss = snapshot.val();
    Object.keys(processedss).forEach(function (order){
        let protr = document.createElement("tr");
        let proOrdernum = document.createElement("th");
        let proCompany = document.createElement("td");
        let proItem = document.createElement("td");
        let proQuantity = document.createElement("td");
        let proAddress = document.createElement("td");
        let printbtn = document.createElement("button");
        printbtn.className = "btn btn-primary";
        printbtn.innerHTML = "Print";
        printbtn.addEventListener("click",function(){printOrder(processedss[order].order_num)});
        proOrdernum.innerHTML = processedss[order].order_num;
        proCompany.innerHTML = processedss[order].company;
        proItem.innerHTML = processedss[order].item;
        proQuantity.innerHTML = processedss[order].quantity;
        proAddress.innerHTML = processedss[order].address;
        protr.append(proOrdernum);
        protr.append(proCompany);
        protr.append(proItem);
        protr.append(proQuantity);
        protr.append(proAddress);
        protr.append(printbtn);
        protbody.append(protr);
    });
    processedtable.append(protbody);
}, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
});
submitbtn.addEventListener("click",addOrder)
function addOrder(){
    //adds an order to the realtime database and puts a number index for ease of use and access for the
    //realtime database
    var newOrder = ref.child(num);
    newOrder.set({
        "order_num": num+1,
        "company": document.getElementById("company").value,
        "item": document.getElementById("item").value,
        "quantity": document.getElementById("quantity").value,
        "address": document.getElementById("address").value,
        'status':'Pending'
    })
    console.log(newOrder.key);
    console.log(document.getElementById("item").value);
    location.reload(true);
}
function editOrder(order){
    //finds the order and fills up the fields on the add order section with the data.
    //and updates the database with the data that was on the field when the admin clicks the update button.
    var ordernum = order.value;
    ref.child(ordernum-1).once('value').then(function(snapshot){
        console.log(snapshot.val());
        editsnap = snapshot.val();

        Objcompany.value = editsnap.company;
        Objitem.value = editsnap.item;
        Objquantity.value = editsnap.quantity;
        Objaddress.value = editsnap.address;
        updatebtn.addEventListener("click",function(){
           ref.child(ordernum-1).update({'order_num':ordernum, 'company': Objcompany.value,
           'item':Objitem.value, 'quantity':Objquantity.value,'address':Objaddress.value, 'status':'Pending'});
            location.reload();
        });

    })


}
//delete button function
function delOrder(order){
    //finds the order from the database and deletes it
    var confirmation = confirm("Are you sure you want to delete this order?");
    if(confirmation){
    var ordernum = order.value;
    ref.child(ordernum-1).remove();
    }
    location.reload(true);
}
//process button function
function processOrder(order){
    var ordernum = order.value;
    //finds the data from the firebase realtimedatabase and stores it in the sessionstorage
    //to be used on the confirmation page that the admin will be sent into after clicking the button
    ref.child(ordernum-1).once('value').then(function(snapshot) {
        console.log(snapshot.val());
        snap = snapshot.val();
        sessionStorage.setItem("ordernum",snap.order_num);
        sessionStorage.setItem("company",snap.company);
        sessionStorage.setItem("item",snap.item);
        sessionStorage.setItem("quantity",snap.quantity);
        sessionStorage.setItem("address",snap.address);
        ref.child(ordernum - 1).update({
            'status': "Processed"
        });

    });
    window.location.replace("ordercheckout.php");

}
function printOrder(num){
    //same function as the processOrder just binded in a different button
    ref.child(num-1).once('value').then(function(snapshot) {
        snap = snapshot.val();
        sessionStorage.setItem("ordernum",snap.order_num);
        sessionStorage.setItem("company",snap.company);
        sessionStorage.setItem("item",snap.item);
        sessionStorage.setItem("quantity",snap.quantity);
        sessionStorage.setItem("address",snap.address);

    });
    window.location.replace("ordercheckout.php");
}