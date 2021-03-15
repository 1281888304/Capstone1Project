var chckout = document.getElementById("checkoutTable");
var ordernum = document.createElement("td");
var company = document.createElement("td");
var item = document.createElement("td");
var quantity = document.createElement("td");
var address = document.createElement("td");
var tbody = document.createElement("tbody");
var tr = document.createElement("tr");
var date = document.getElementById("date");
ordernum.innerHTML = sessionStorage.getItem("ordernum");
company.innerHTML = sessionStorage.getItem("company")
item.innerHTML = sessionStorage.getItem("item");
quantity.innerHTML = sessionStorage.getItem("quantity");
address.innerHTML = sessionStorage.getItem("address");
tr.append(ordernum)
tr.append(company);
tr.append(item);
tr.append(quantity);
tr.append(address);
tbody.append(tr);
chckout.append(tbody);
date.innerHTML = new Date();