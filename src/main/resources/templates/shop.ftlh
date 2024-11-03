<!DOCTYPE html>
<html lang="en">
<head>
    <title>SHOP</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/static/css/shop.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="/static/js/shopJS.js"></script>


</head>

<body>
<#include "navbar1.ftlh">
    <div class="container align-items-center text-center">
        <#if orders?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr class="border-table">
                        <th class="border-table" colspan="12"> Orders </th>
                    </tr>
                    <tr class="border-table">
                        <th class="border-table">Order Date</th>
                        <th class="border-table">Username</th>
                        <th class="border-table">Product</th>
                        <th class="border-table">Multipack</th>
                        <th class="border-table table-info">Amount</th>
                        <th class="border-table table-info">Price</th>
                        <th class="border-table table-info">Value</th>
                        <th class="border-table table-success">Sell Price</th>
                        <th class="border-table table-success">Revenue</th>
                        <th class="border-table table-success">Income</th>
                        <th class="border-table">Payment</th>
                        <th class="border-table">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <#list orders as order>
                        <tr class="border-table">
                            <td class="border-table">${order.orderDateTime?datetime?string("dd-MM-yyyy")}</td>
                            <td class="border-table">${order.user.name}</td>
                            <td class="border-table">${order.item.name}</td>
                            <td class="border-table"><#if order.isMultipack!false>Yes<#else> No </#if></td>
                            <td class="border-table table-info">${order.amount} PCS</td>
                            <td class="border-table table-info">${order.purchasePrice} PLN</td>
                            <td class="border-table table-info">${order.totalPurchaseValue} PLN</td>
                            <td class="border-table table-success">${order.sellPrice} PLN</td>
                            <td class="border-table table-success">${order.revenue} PLN</td>
                            <td class="border-table table-success">${order.income} PLN</td>
                            <#if order.isSettled!false>
                                <td class="border-table text-success">
                                    <i class="bi bi-check"></i>
                            <#else>
                                <td class="border-table text-danger">
                                    <i class="bi bi-x-circle"></i>
                                </td>
                            </#if>
                            <#if order.isSettled!false>
                                <td class="border-table"> - </td>
                            <#else>
                                <td>
                                    <div class="btn-group btn-group-sm">
                                        <a href="/home-page/${order.orderId}" class="btn btn-outline-primary">
                                            <i class="bi bi-check"></i>
                                        </a>
                                        <a href="/order-page/${order.orderId}" class="btn btn-outline-success">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="/delete-order/${order.orderId}" class="btn btn-outline-danger">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </div>
                                </td>
                            </#if>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>

        <#if users?has_content && products?has_content>
            <div class="row justify-content-center align-items-center">
                <div class="col-3 mt-3 mb-3">
                    <button id="openOrderModalBtn" type="button" class="btn btn-primary col-12" data-bs-toggle="modal" data-bs-target="#addOrderModal"> Add order</button>
                    </div>
                </div>
            </div>
            <div class="modal" tabindex="-1" id="addOrderModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h5 class="modal-title"> Add an order </h5>
                            <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form class="mb-3" id="orderForm" action="/home-page" method="post">
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
                                <div class="form-check form-control justify-content-start expenseFlagDiv">
                                    <input class="form-check-input" id="expenseFlag" name="isExpense" type="checkbox" value="false" checked="checked">
                                    <label class="form-check-label" for="expenseFlag"> product as expense </label>
                                </div>

                                <div class="form-control justify-content-start">
                                    <input class="form-check-input" type="checkbox" value="true" name="isMultipack" id="isMultipack">
                                    <label class="form-check-label" for="isMultipack">Multipack</label>
                                </div>

                                <div id="amount-div" class="input-group align-items-center">
                                    <span class="input-group-text mb-2" aria-label="Quantity"> Quantity </span>
                                    <input class="form-control mb-2" id="amount" type="number" name="amount" value="1" min="1" step="1" aria-describedby="addon-wrapping">
                                </div>

                                <div class="input-group align-items-center">
                                    <span class="input-group-text mb-2" aria-label="Purchase"> Purchase </span>
                                    <input class="form-control mb-2" id="purchasePrice" type="number" name="purchasePrice" step="0.01">
                                    <span class="input-group-text mb-2"> PLN </span>
                                </div>

                                <div id="inputSellPrice" class="input-group align-items-center">
                                    <span class="input-group-text mb-2" aria-label="SellPrice"> SellPrice </span>
                                    <input class="form-control mb-2" id="sellPrice" type="number" name="sellPrice" step="0.01">
                                    <span class="input-group-text mb-2"> PLN </span>
                                </div><br>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button id="addOrderBtn" class="btn btn-primary col-2"> Confirm </button>
                            <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal"> Back </button>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
                <div class="alert alert-danger">Before starting you need to add <a class="alert-link" href="/users">users</a> and/or <a class="alert-link" href="/products">products</a>!</div>
        </#if>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
