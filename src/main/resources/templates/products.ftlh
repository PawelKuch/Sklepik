<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

    <script src="/static/js/productJS.js"></script>
</head>

<body>
<#include "navbar1.ftlh">
   <div class="bg-secondary-subtle container align-items-center text-center col-5" id="container">
        <#if items?has_content>
            <table id="productsTable" class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th> </th>
                        <th>Available products:</th>
                    </tr>
                </thead>
                <tbody>
                    <form action="/delete-products" method="post" id="productList">
                        <#list items as item>
                            <tr>
                                <td><input type="checkbox" name="selectedItem" value="${item.itemId}"> </td>
                                <td>${item.name}</td>
                            </tr>
                        </#list>
                    </form>
                </tbody>
            </table>
        </#if>
        <div class="row justify-content-center align-items-center">
            <div class="col-6 mt-3 mb-3">
                <button id="openModalBtn" type="button" class="btn btn-primary col-12" data-bs-toggle="modal" data-bs-target="#addProductModal"> Add </button>
            </div>
            <div class="col-6 mt-3 mb-3">
                <button class="btn btn-primary col-12" id="delBtn" type="button" > Delete product/s </button>
            </div>
        </div>
        <div class="modal" tabindex="-1" id="addProductModal">
            <div class="modal-dialog">
                <div class="modal-content">
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
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="addBtn" class="btn btn-primary mb-1 col-2"> Add </button>
                        <button type="button" class="btn btn-outline-primary col-2" data-bs-dismiss="modal"> Back </button>
                    </div>
                </div>
            </div>
        </div>
        <#if itemExists!false>
            <div class="alert alert-danger" role="alert">
                Item has not been added! Item already exists.
            </div>
        <#elseif isItemEmpty!false>
            <div class="alert alert-danger" role="alert">
                Input field of product is empty!
            </div>
        </#if>
   </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
