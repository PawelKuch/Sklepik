    $(document).ready(function(){
        $("#addBtn").click(function(){
            $("#orderForm").submit();
        });

        $("#isForSaleCheck").change(function(){
            if($(this).prop("checked")){
                $("#sellPrice").attr("disabled", "disabled");
            }else {
                $("#sellPrice").removeAttr("disabled");
            }
        });
    });