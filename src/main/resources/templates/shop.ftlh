<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <style>
        #itemForm {
            display:none;
        }
        #buttonAdd {
            display:block;
        }
        select {
            min-width: 100px;
        }
        option {
            min-width: 100px;
        }
    </style>
</head>

<body>
   <div id="container">

        <form id="orderForm" action="/shop" method="post" class="mt-3">
            <select name="selectedUser">
                <option> - </option>
                <#list users as user>
                    <option value=${user.userId}>${user.name}</option>
                </#list>
            </select>
            <select name="selectedItem">
                    <option> - </option>
                <#list products as product>
                    <option value=${product.itemId}>${product.name}</option>
                </#list>
            </select>
            <input type="number" name="amount" value="1" min="1" class="form_control mb-3" placeholder="1">
            <button type="submit" class="btn btn-dark"> Zatwierdź </button>
        </form>
        <#if owner?has_content && itemname?has_content && pprice?has_content && sprice?has_content>
            ${owner}, ${itemname}, ${pprice}, ${sprice}
        </#if>
        <button id="buttonAdd" class="btn btn-dark mt-5" onclick="toggleItemForm()">Dodaj zamówienie</button>

        <#if orders?has_content>
                    <table class="table table-bordered mt-3">
                        <thead>
                            <tr>
                                <th>Zamówienia w systemie:</th>
                            </tr>
                            <tr>
                                <th> ID zamówienia: </th>
                                <th> Użytkownik: </th>
                                <th> Produkt: </th>
                                <th> Ilość: </th>
                                <th> Cena: </th>
                                <th> Wartość: </th>
                                <th> Cena sprzedaży: </th>
                                <th> Przychód z sprzedaży: </th>
                                <th> Zysk: </th>

                            </tr>
                        </thead>
                        <tbody>
                            <#list orders as order>
                                <tr>
                                    <td><a href="/orderDetails/${order.orderId}">${order.orderId}</a></td>
                                    <td> ${order.user.name} </td>
                                    <td> ${order.item.name} </td>
                                    <td> ${order.amount} </td>
                                    <td> ${order.item.purchasePrice} </td>
                                    <td? ${order.totalPurchaseValue} </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </#if>
   </div>

        <script>
            function toggleOrderForm() {
                var orderForm = document.getElementById("orderForm");
                var addButton = document.getElementById("buttonAdd");
                if (orderForm.style.display === "none") {
                    orderForm.style.display = "block";
                    buttonAdd.style.display = "none";
                } else {
                    orderForm.style.display = "none";
                    buttonAdd.style.display = "block";
                }
            }
        </script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
