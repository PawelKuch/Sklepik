<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

    <style>
        body {
            background-color:#21211f;;
            color: #ffffff;
        }
        a {
            color: white;
            text-decoration: none;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
        a:visited {
            color: white;
            text-decoration: none;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-warning py-0">
        <div class="container">
            <a class="navbar-brand" href="/"><p class="fs-3 fw-bolder"> MENU </p></a>
            <div class="collapse navbar-collapse">
                <a class="nav-link" href="/"> Shop </a>
                <a class="nav-link" href="/users"> Users </a>
                <a class="nav-link" href="/products"> Products </a>
            </div>
        </div>
    </nav>

    <div class="container align-items-center text-center">
        <#if orders?has_content>
            <table class="table table-bordered table-dark mt-3">
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
        <#if users?has_content && products?has_content>
            <div class="row justify-content-center align-items-center">
                <div class="col-3 mt-3 mb-3">
                    <button id="openModalBtn" type="button" class="btn btn-warning col-12" data-bs-toggle="modal" data-bs-target="#addOrderModal"> Add </button>
                </div>
            </div>
            <div class="modal" tabindex="-1" id="addOrderModal">
                <div class="modal-dialog">
                    <div class="modal-content bg-dark">
                        <div class="modal-header text-center">
                            <h5 class="modal-title"> Add an order </h5>
                            <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form class="mb-3" id="orderForm" action="/" method="post">
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
                                </div><br>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button id="addBtn" class="btn btn-warning col-2" onclick="submitForm()"> Confirm </button>
                            <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal"> Back </button>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
                <div class="col"><h1>Before starting you need to add users and/or products!</h1></div>
        </#if>

    </div>

    <script>
        function submitForm(){
            $("#orderForm").submit();
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
