<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="shopJS.js"></script>
</head>

<body>
<#include "navbar1.ftlh">
    <div class="container align-items-center text-center">
        <#if orders?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th colspan="11"> Settlements </th>
                    </tr>
                    <tr>
                        <th>Order Date</th>
                        <th>Username</th>
                        <th>Product</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Value</th>
                        <th>Sell Price</th>
                        <th>Revenue</th>
                        <th>Income</th>
                        <th>Settlement</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <#list orders as order>
                        <tr>
                            <td>${order.orderDateTime?datetime?string("dd-MM-yyyy HH:mm:ss")}</td>
                            <td>${order.user.name}</td>
                            <td>${order.item.name}</td>
                            <td>${order.amount} PCS</td>
                            <td>${order.purchasePrice} PLN</td>
                            <td>${order.totalPurchaseValue} PLN</td>
                            <td>${order.sellPrice} PLN</td>
                            <td>${order.revenue} PLN</td>
                            <td>${order.income} PLN</td>
                            <#if order.isSettled!false>
                                <td>settled</td>
                            <#else>
                                <td>unsettled</td>
                            </#if>
                            <#if order.isSettled!false>
                                <td> - </td>
                            <#else>
                                <td><a href="/orderSettlements/${order.orderId}">settle</a></td>
                            </#if>
                        </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
                <div class="alert alert-danger">No existing orders to be settled!</div>
        </#if>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
