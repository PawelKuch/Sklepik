<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="src/main/resources/static/orderJS.js"></script>
    <script src="/static/js/orderJS.js"></script>

</head>

<body>
<#include "navbar1.ftlh">
<div class="container align-items-center text-center">
    <div class="row justify-content-center">
        <div class="col-6">
            <form class="mb-3" id="updateOrderForm" action="/order-page/${originalOrder.orderId}" method="POST" >
                <label for="selectedUser">User</label>
                <select class="form-select mb-2" id="selectedUser" name="selectedUser">
                    <#list users as user>
                        <option value="${user.userId}" ${(user.userId == originalOrder.user.userId) ? string("selected", "")} > ${user.name}</option>
                    </#list>
                </select>

                <select class="form-select mb-2" id="selectedItem" name="selectedItem">
                    <#list items as item>
                        <option value="${item.itemId}" ${(item.itemId == originalOrder.item.itemId) ? string("selected", "")}> ${item.name}</option>
                    </#list>
                </select>

                <div class="input-group align-items-center">
                    <span class="input-group-text mb-2" aria-label="Quantity"> Quantity </span>
                    <input class="form-control mb-2" id="amount" type="number" name="amount" value="${originalOrder.amount}" min="1" step="1" aria-describedby="addon-wrapping">
                </div>

                <div class="input-group align-items-center">
                    <span class="input-group-text mb-2" aria-label="Purchase"> Purchase </span>
                    <input class="form-control mb-2" id="purchasePrice" type="number" name="purchasePrice" value="${originalOrder.purchasePrice}" min="1" step="1" aria-describedby="addon-wrapping">
                    <span class="input-group-text mb-2">PLN</span>
                </div>

                <div class="input-group align-items-center">
                    <span class="input-group-text mb-2" aria-label="sellPrice"> sellPrice </span>
                    <input class="form-control mb-2" id="sellPrice" type="number" name="sellPrice" value="${originalOrder.sellPrice}" min="1" step="1" aria-describedby="addon-wrapping">
                    <span class="input-group-text mb-2">PLN</span>
                </div>
            </form>
        </div>
        <div class="col-2">
            <button class="btn btn-primary" id="updateOrderBtn">Update</button>
        </div>
    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
