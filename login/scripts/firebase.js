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
var db = firebase.database();
var ref = db.ref("orders");
const ordertable = document.getElementById("ordersTable")
let tbody = document.createElement("tbody");
// Attach an asynchronous callback to read the data at our posts reference
ref.on("value", function(snapshot) {
    console.log(snapshot.val());
    let ss = snapshot.val();
    for (let i = 0; i < snapshot.val().length; i++) {
        let tr = document.createElement("tr");
        let ordernum = document.createElement("th");
        let company = document.createElement("td");
        let item = document.createElement("td");
        let quantity = document.createElement("td");
        ordernum.innerHTML = ss[i].order_num;
        company.innerHTML = ss[i].company;
        item.innerHTML = ss[i].item;
        quantity.innerHTML = ss[i].quantity;
        tr.append(ordernum);
        tr.append(company);
        tr.append(item);
        tr.append(quantity);
        tbody.append(tr);

    }
    ordertable.append(tbody);
}, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
});