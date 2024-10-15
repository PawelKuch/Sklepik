<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="/static/js/shopJS.js"></script>
</head>

<body>
<#include "navbar1.ftlh">
    <div class="container align-items-center text-center">
        <#if expenses?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th colspan="6"> Expenses </th>
                    </tr>
                    <tr>
                        <th>Expense Date</th>
                        <th>Username</th>
                        <th>Product</th>
                        <th>Amount</th>
                        <th>Cost</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <#list expenses as expense>
                        <tr>
                            <td>${expense.expenseDateTime?datetime?string("dd-MM-yyyy")}</td>
                            <td>${expense.user.name}</td>
                            <td>${expense.item}</td>
                            <td>${expense.amount} PCS</td>
                            <td>${expense.expensePrice} PLN</td>
                            <td>${expense.totalExpenseValue} PLN</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
        <#if users?has_content && products?has_content>
            <div class="row justify-content-center align-items-center">
                <div class="col-3 mt-3 mb-3">
                    <button id="openExpenseModalBtn" type="button" class="btn btn-primary col-12" data-bs-toggle="modal" data-bs-target="#addExpenseModal"> Add expense</button>
                </div>
            </div>
            <div class="modal" tabindex="-1" id="addExpenseModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h5 class="modal-title"> Add an order </h5>
                            <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form class="mb-3" id="expenseForm" action="/expenses" method="post">
                                <select class="form-select mb-2" id="selectedUser" name="selectedUser">
                                    <option> Select user </option>
                                    <#list users as user>
                                        <option value=${user.userId}>${user.name}</option>
                                    </#list>
                                </select>

                                <div class="form-check form-control justify-content-start expenseFlagDiv">
                                    <input class="form-check-input" id="expenseFlag" name="isExpense" type="checkbox" value="true" checked="checked">
                                    <label class="form-check-label" for="expenseFlag"> product as expense </label>
                                </div>

                                <div class="input-group align-items-center">
                                    <span class="input-group-text mb-2" aria-label="Item"> Item </span>
                                    <input class="form-control mb-2" id="item" name="item" aria-describedby="addon-wrapping">
                                </div>

                                <div class="input-group align-items-center">
                                    <span class="input-group-text mb-2" aria-label="Quantity"> Quantity </span>
                                    <input class="form-control mb-2" id="amount" type="number" name="amount" value="1" min="1" step="1" aria-describedby="addon-wrapping">
                                    <span class="input-group-text mb-2"> PC/S </span>
                                </div>



                                <div class="input-group align-items-center">
                                    <span class="input-group-text mb-2" aria-label="Purchase"> Purchase </span>
                                    <input class="form-control mb-2" id="purchasePrice" type="number" name="purchasePrice" step="0.01">
                                    <span class="input-group-text mb-2"> PLN </span>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button id="addExpenseBtn" class="btn btn-primary col-2"> Confirm </button>
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
