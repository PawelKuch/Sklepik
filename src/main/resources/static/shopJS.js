    $(document).ready(function(){
        $("#addBtn").click(function(){
            $("#orderForm").submit();
        });

        $("#isForSaleCheck").change(function(){
            if($(this).prop("checked")){
                $("#sellPrice").val("0");
                $("#inputSellPrice").hide();

            }else {
                $("#inputSellPrice").show();
                $("#sellPrice").val("");
            }
        });
    });