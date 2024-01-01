<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <style>
        #productForm{
        display:none;
        }
        #addButton{
        display:block;
        }
    </style>
</head>

<body>
   <div id="container">

        <#if items?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Dostępne produkty:</th>
                    </tr>
                </thead>
                <tbody>
                    <#list items as item>
                        <tr>
                            <td>${item.name}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
        <button id="addButton" class="btn btn-dark" onclick="toggleProductForm()"> Dodaj produkt </button>

        <form id="productForm" action="/products" method="post" class="mt-3">
            <input type="text" name="itemName" class="form-control mb-3" placeholder="nazwa produktu">
            <button type="submit" class="btn btn-dark">Zatwierdź</button>
        </form>
   </div>

  <script>
        function toggleProductForm() {
            var productForm = document.getElementById("productForm");
            var addButton = document.getElementById("addButton");
            if (productForm.style.display === "none") {
                  productForm.style.display = "block";
                  addButton.style.display = "none"
            } else {
                  productForm.style.display = "none";
                  addButton.style.display = "block";
            }
        }
  </script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
