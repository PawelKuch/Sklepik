<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="/static/js/usersJS.js"></script>
</head>

<body>
<#include "navbar1.ftlh">
   <div id="container" class="container col-5 align-items-center text-center">
        <#if users?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th> </th>
                        <th>Available users:</th>
                    </tr>
                </thead>
                <tbody>
                    <form action="/delete-users" method="POST" id="userList">
                        <#list users as user>
                            <tr>
                                <td> <input type="checkbox" name="selectedUser" value="${user.userId}"> </td>
                                <td>${user.name}</td>
                            </tr>
                        </#list>
                    </form>
                </tbody>
            </table>
        </#if>
       <div class="modal" tabindex="-1" id="addUserModal">
           <div class="modal-dialog">
               <div class="modal-content">
                   <div class="modal-header text-center">
                       <h5 class="modal-title"> Add user </h5>
                       <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                   </div>
                   <div class="modal-body">
                       <form id="userForm" action="/users" method="post" class="mt-3">
                           <div class="input-group align-items-center">
                               <span class="input-group-text mb-3"> User </span>
                               <input class="form-control mb-3" type="text" name="userName" class="form-control mb-3">
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
       <div class="row justify-content-center align-items-center">
           <div class="col-6 mt-3 mb-3">
               <button id="openModalBtn" type="button" class="btn btn-primary col-12" data-bs-toggle="modal" data-bs-target="#addUserModal"> Add </button>
           </div>
           <div class="col-6 mt-3 mb-3">
               <button class="btn btn-primary col-12" id="deleteUserBtn" type="button" > Delete user/s </button>
           </div>
       </div>
    </div>
    <#if userExists!false>
        <div class="alert alert-danger" role="alert">
            User has not been added! User already exists.
        </div>
    <#elseif isUserEmpty!false>
        <div class="alert alert-danger" role="alert">
            Input field of user is empty!
        </div>
    </#if>
   </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
