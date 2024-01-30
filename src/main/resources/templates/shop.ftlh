<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <style>
        body{
            background-color:#174001;
            color:white;
        }
        #itemForm {
            display:none;
        }
        #buttonAdd {
            display:block;
        }
        select {
            min-width: 100px;
        }
        option {
            min-width: 100px;
        }
        #backBtn {
            border: solid 1px #000000;
            width: 100px;
        }
        a {
            color:white;
            text-decoration:none;
        }
        a:hover {
            color:white;
            text-decoration:none;
        }
        a:visited {
            color:white;
            text-decoration:none;
        }
        #orderTable {
            color:white;
        }
    </style>
</head>

<body>
   <div id="container">

    <#if users?has_content && products?has_content>
        <form id="orderForm" action="/shop" method="post" class="mt-3">
            <select name="selectedUser">
                <option> - </option>
                <#list users as user>
                    <option value=${user.userId}>${user.name}</option>
                </#list>
            </select>
            <select name="selectedItem">
                <option> - </option>
                <#list products as product>
                    <option value=${product.itemId}>${product.name}</option>
                </#list>
            </select>
            <input type="number" name="amount" value="1" min="1" class="form-control mb-3">
            <input type="number" name="purchasePrice" value="0.01" class="form-control mb-3">
            <button type="submit" class="btn btn-dark"> Confirm </button>
        </form>
        <#if owner?has_content && itemname?has_content && pprice?has_content && sprice?has_content>
            ${owner}, ${itemname}, ${pprice}, ${sprice}
        </#if>
        <button id="buttonAdd" class="btn btn-dark mt-5" onclick="toggleItemForm()">Add order</button>
    <#else>
        <h1> Before starting you need to add users and/or products!</h1>
        <a href="/"><div id="backBtn">Back</div></a>
    </#if>
        <#if orders?has_content>
                    <table id="orderTable" class="table table-bordered mt-3">
                        <thead>
                            <tr>
                                <th>Orders:</th>
                            </tr>
                            <tr>
                                <th> Order ID: </th>
                                <th> Username: </th>
                                <th> Product: </th>
                                <th> Amount: </th>
                                <th> Price: </th>
                                <th> Value: </th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list orders as order>
                                <tr>
                                    <td><a href="/orderDetails/${order.orderId}">${order.orderId}</a></td>
                                    <td> ${order.user.name} </td>
                                    <td> ${order.item.name} </td>
                                    <td> ${order.amount} </td>
                                    <td> ${order.purchasePrice} </td>
                                    <td> ${order.totalPurchaseValue} </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </#if>
   </div>

        <script>
            function toggleOrderForm() {
                var orderForm = document.getElementById("orderForm");
                var addButton = document.getElementById("buttonAdd");
                if (orderForm.style.display === "none") {
                    orderForm.style.display = "block";
                    buttonAdd.style.display = "none";
                } else {
                    orderForm.style.display = "none";
                    buttonAdd.style.display = "block";
                }
            }
        </script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
