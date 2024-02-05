<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color:#21211f;;
            color: #ffffff;
            padding: 20px;
        }
        #container {
            max-width: 800px;
            margin: 0 auto;
            items-align: center;
            text-align: center;
        }

        form {
            margin-bottom: 20px;
        }

        select, input {
            margin-bottom: 10px;
        }
        #addBtn {
            background-color: #edb007;
            display: block;
            margin-left: auto;
            margin-right: auto;
            margin-top: 10px;
        }
        #backBtn {
            border: solid 1px #000000;
            width: 100px;
            display: inline-block;
            text-align: center;
            padding: 5px;
            margin-top: 10px;
        }
        #amount {
            max-width: 80px;
        }
        #purchasePrice {
            max-width: 80px;
        }

        a {
            color: white;
            text-decoration: none;
        }

        a:hover {
            color: white;
            text-decoration: underline;
        }

        #orderTable {
            color: white;
            margin-top: 20px;
        }

        #buttonAdd {
            display: block;
            margin-top: 20px;
        }
    </style>
</head>

<body>
    <div id="container">
        <#if users?has_content && products?has_content>
            <form id="orderForm" action="/shop" method="post">
                <label for="selectedUser">Select User:</label>
                <select id="selectedUser" name="selectedUser">
                    <option> - </option>
                    <#list users as user>
                        <option value=${user.userId}>${user.name}</option>
                    </#list>
                </select>

                <label for="selectedItem">Select Item:</label>
                <select id="selectedItem" name="selectedItem">
                    <option> - </option>
                    <#list products as product>
                        <option value=${product.itemId}>${product.name}</option>
                    </#list>
                </select>

                <label for="amount">Quantity:</label>
                <input id="amount" type="number" name="amount" value="1" min="1" step="1">
                <label for="purchasePrice">Purchase Price:</label>
                <input id="purchasePrice" type="number" name="purchasePrice" step="0.01">

                <button id="addBtn" type="submit" class="btn btn-dark">Confirm</button>
            </form>

            <#if owner?has_content && itemname?has_content && pprice?has_content && sprice?has_content>
                ${owner}, ${itemname}, ${pprice}, ${sprice}
            </#if>
        <#else>
            <h1>Before starting you need to add users and/or products!</h1>
        </#if>

        <#if orders?has_content>
            <table id="orderTable" class="table table-bordered">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Username</th>
                        <th>Product</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <#list orders as order>
                        <tr>
                            <td><a href="/orderDetails/${order.orderId}">${order.orderId}</a></td>
                            <td>${order.user.name}</td>
                            <td>${order.item.name}</td>
                            <td>${order.amount} PCS</td>
                            <td>${order.purchasePrice} PLN</td>
                            <td>${order.totalPurchaseValue} PLN</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
        <a href="/"><button id="backBtn" class="btn btn-dark"> Back </button></a>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
