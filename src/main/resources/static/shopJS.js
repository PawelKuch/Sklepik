    $(document).ready(function(){
        $("#addBtn").click(function(){
            $("#orderForm").submit();
        });
        //$("#expenseFlag").hide();
        $("#expenseFlag").attr("checked", "checked");
        $("#isExpenseFlag").change(function(){
            if($(this).prop("checked")){
                $("#sellPrice").val("0");
                $("#inputSellPrice").hide();
                $("#expenseFlag").removeAttr("checked");
            }else {
                $("#inputSellPrice").show();
                $("#sellPrice").val("");
                $("#isExpenseFlag").removeAttr("checked");
                $("#expenseFlag").attr("checked", "checked");
            }
        });
    });