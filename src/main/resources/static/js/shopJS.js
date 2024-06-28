$(document).ready(function(){
    $("#addOrderBtn").click(function(){
        $("#orderForm").submit();
    });
    $("#addExpenseBtn").click(function(){
        $("#expenseForm").submit();

    });
    $(".expenseFlagDiv").hide();

});