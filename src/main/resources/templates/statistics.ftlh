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
        <#if users?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th colspan="8">Available users:</th>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <th>Total purchase</th>
                        <th>Total expense</th>
                        <th>Total revenue</th>
                        <th>Total income</th>
                        <th>Orders quantity</th>
                        <th>Settled orders</th>
                        <th>Unsettled orders</th>
                    </tr>
                </thead>
                <tbody>
                    <#list statistics as statistic>
                        <tr></tr>
                        <tr>
                            <td>${statistic.user.name}</td>
                            <td>${statistic.totalPurchaseValue}</td>
                            <td>${statistic.totalExpenseValue}</td>
                            <td>${statistic.totalRevenueValue}</td>
                            <td>${statistic.totalIncomeValue}</td>
                            <td>${statistic.howManyOrders}</td>
                            <td>${statistic.howManySettledOrders}</td>
                            <td>${statistic.howManyUnsettledOrders}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
