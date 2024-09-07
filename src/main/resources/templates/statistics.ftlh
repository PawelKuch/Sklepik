<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/static/css/statistics.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="/static/static/shopJS.js"></script>

</head>

<body>
<#include "navbar1.ftlh">
    <div class="container align-items-center text-center">
        <#if users?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th colspan="6">Users orders statistics</th>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <th>Total purchase</th>
                        <th >Total revenue</th>
                        <th>Total income</th>
                        <th>Orders quantity</th>

                    </tr>
                </thead>
                <tbody>
                    <#list ordersStatistics as statistic>
                        <tr></tr>
                        <tr>
                            <td>${statistic.name}</td>
                            <td>${statistic.totalPurchaseValue} PLN</td>
                            <td>${statistic.totalRevenueValue} PLN</td>
                            <td>${statistic.totalIncomeValue} PLN</td>
                            <td>${statistic.howManyOrders}</td>

                        </tr>
                    </#list>
                </tbody>
            </table>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th colspan="3">User expenses statistics </th>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <th>Total value</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <tr></tr>
                    <#if expensesStatistics?has_content>
                        <#list expensesStatistics as statistic>
                            <tr>
                                <td>${statistic.userName}</td>
                                <td>${statistic.totalExpenseValue} PLN</td>
                                <td>${statistic.howManyExpenses}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="3"> No expenses to show </td>
                        </tr>
                    </#if>
                </tbody>
            </table>

            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th colspan="6">General statistics</th>
                    </tr>
                    <tr>
                        <th>General purchase value</th>
                        <th>General expense value</th>
                        <th>General revenue</th>
                        <th>General income</th>
                        <th>Orders</th>
                        <th>Expenses</th>
                    </tr>
                </thead>
                <tbody>
                    <tr></tr>
                    <tr>
                        <td>${generalStatistics.totalPurchaseValue} PLN</td>
                        <td>${generalStatistics.totalExpenseValue} PLN</td>
                        <td>${generalStatistics.totalRevenue} PLN</td>
                        <td>${generalStatistics.totalIncome} PLN</td>
                        <td>${generalStatistics.howManyOrders}</td>
                        <td>${generalStatistics.howManyExpenses}</td>
                    </tr>
                </tbody>
            </table>

            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th colspan="3">Entities quantity</th>
                    </tr>
                    <tr>
                        <th>Orders</th>
                        <th>Users</th>
                        <th>Items</th>
                    </tr>
                </thead>
                <tbody>
                    <tr></tr>
                    <tr>
                        <td>${generalStatistics.howManyOrders} </td>
                        <td>${generalStatistics.howManyUsers} </td>
                        <td>${generalStatistics.howManyItems} </td>

                    </tr>
                </tbody>
            </table>
        </#if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
