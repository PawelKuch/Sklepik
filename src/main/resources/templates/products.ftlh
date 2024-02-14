<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body{
            background-color:#21211f;
            color:#ffffff;
        }
        .custom-width {
            width: 100px;
        }
        .custom-max-width-400 {
            max-width: 400px;
        }
        a {
            color:white;
            text-decoration:none;
        }
        a:hover {
            color:white;
            text-decoration:none;
        }
        a:visited{
             color:white;
             text-decoration:none;
        }
    </style>
</head>

<body>
   <div class="container align-items-center text-center custom-max-width-400" id="container">
        <#if items?has_content>
            <table id="productsTable" class="table table-bordered table-dark table-stripped mt-3">
                <thead>
                    <tr>
                        <th>Available products:</th>
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
        <button id="openModalBtn" type="button" class="btn btn-warning mt-3 custom-width" data-bs-toggle="modal" data-bs-target="#addProductModal"> Add </button>
        <a href="/"><button id="backBtn" class="btn btn-dark mt-3 custom-width"> Back </button></a>
        <div class="modal" tabindex="-1" id="addProductModal">
            <div class="modal-dialog">
                <div class="modal-content bg-dark">
                    <div class="modal-header text-center">
                        <h5 class="modal-title"> Add a product </h5>
                        <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="productForm" action="/products" method="post" class="mt-3">
                            <div class="input-group align-items-center">
                                <span class="input-group-text mb-3"> Product </span>
                                <input class="form-control mb-3" type="text" name="itemName" class="form-control mb-3">
                            </div>
                            <button id="addBtn" type="submit" class="btn btn-warning mb-1 custom-width"> Add </button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal"> Back </button>
                    </div>
                </div>
            </div>
        </div>
   </div>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
