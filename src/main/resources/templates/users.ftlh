<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <style>
        body{
            background-color: #21211f;
            color: #ffffff;
        }
        .custom-width-400 {
            max-width: 400px;
        }
        .custom-width-100 {
            width: 100px;
        }
        a {
            color:white;
            text-decoration:none;
        }
        a:hover {
            color:white;
            text-decoration:none;
        }
        a:visited {
            color:white;
            text-decoration:none;
        }
        #usersTable {
            color:white;
        }
    </style>
</head>

<body>
   <div class="container custom-width-400 align-items-center text-center">
        <#if users?has_content>
            <table id="usersTable" class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Available users:</th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as user>
                        <tr>
                            <td>${user.name}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
        <button id="openModalBtn" type="button" class="btn btn-warning mt-3 custom-width-100" data-bs-toggle="modal" data-bs-target="#addUserModal"> Add user </button>
        <a href="/"><button id="backBtn" class="btn btn-dark mt-3 custom-width-100">Back</button></a>
    <div class="modal" tabindex="-1" id="addUserModal">
            <div class="modal-dialog">
                <div class="modal-content bg-dark">
                    <div class="modal-header text-center">
                        <h5 class="modal-title"> Add a user </h5>
                        <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body align-items-center">
                        <form id="userForm" action="/users" method="post" clas="mt-3">
                            <div class="input-group align-items-center">
                                <span class="input-group-text mb-3"> User name </span>
                                <input class="form-control mb-3" id="inputField" type="text" name="userName" class="form-control mb-3"><br>
                            </div>
                            <button id="addBtn" type="submit" class="btn btn-warning mb-1 custom-width-100"> Add </button>
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
