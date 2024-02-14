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
        .custom-max-width-800 {
            max-width: 800px;
        }
        .custom-width-100 {
            width: 100px;
        }
        a {
            color: white;
            text-decoration: none;
        }

        a:hover {
            color: white;
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div class="container align-items-center text-center custom-max-width-800">
        <#if users?has_content && products?has_content>

            <#if owner?has_content && itemname?has_content && pprice?has_content && sprice?has_content>
                ${owner}, ${itemname}, ${pprice}, ${sprice}
            </#if>
        <#else>
            <h1>Before starting you need to add users and/or products!</h1>
        </#if>

        <#if orders?has_content>
            <table id="orderTable" class="table table-bordered table-dark table-stripped mt-3">
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
        <div class="d-grid gap-5 d-md-block">
            <button id="openModalBtn" type="button" class="btn btn-sm btn-warning mt-3 custom-width-100" data-bs-toggle="modal" data-bs-target="#addOrderModal"> Add </button>
            <a href="/"><button id="backBtn" type="button" class="btn btn-sm btn-dark mt-3 custom-width-100"> Back </button></a>
        </div>
        <div class="modal" tabindex="-1" id="addOrderModal">
            <div class="modal-dialog">
                <div class="modal-content bg-dark">
                    <div class="modal-header text-center">
                        <h5 class="modal-title"> Add an order </h5>
                        <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="mb-3" id="orderForm" action="/shop" method="post">
                            <select class="form-select mb-2" id="selectedUser" name="selectedUser">
                                <option> Select user </option>
                                <#list users as user>
                                    <option value=${user.userId}>${user.name}</option>
                                </#list>
                            </select>

                            <select class="form-select mb-2" id="selectedItem" name="selectedItem">
                                <option> Select product </option>
                                <#list products as product>
                                    <option value=${product.itemId}>${product.name}</option>
                                </#list>
                            </select>

                            <div class="input-group align-items-center">
                                <span class="input-group-text mb-2" aria-label="Quantity"> Quantity </span>
                                <input class="form-control mb-2" id="amount" type="number" name="amount" value="1" min="1" step="1" aria-describedby="addon-wrapping">
                            </div>

                            <div class="input-group align-items-center">
                                <span class="input-group-text mb-2" aria-label="Purchase"> Purchase </span>
                                <input class="form-control mb-2" id="purchasePrice" type="number" name="purchasePrice" step="0.01">
                                <span class="input-group-text mb-2"> PLN </span>
                            </div>
                            <br>
                            <button id="addBtn" type="submit" class="btn btn-warning custom-width-100"> Confirm </button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal"> Back </button>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
