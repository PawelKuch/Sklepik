<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

    <style>
        body{
            background-color: #21211f;
            color: #ffffff;
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

    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-warning py-0">
        <div class="container">
            <a class="navbar-brand" href="/"><p class="fs-3 fw-bolder"> MENU </p></a>
            <div class="collapse navbar-collapse">
                <a class="nav-link" href="/"> Shop </a>
                <a class="nav-link" href="/users"> Users </a>
                <a class="nav-link" href="/products"> Products </a>
            </div>
        </div>
    </nav>

   <div id="container" class="container col-5 align-items-center text-center">
        <#if users?has_content>
            <table class="table table-bordered table-dark mt-3">
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
        <div class="row justify-content-center align-items-center">
            <div class="col-5 mt-3 mb-3">
                <button type="button" class="btn btn-warning col-12" data-bs-toggle="modal" data-bs-target="#addUserModal"> Add user </button>
            </div>
        </div>
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
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="addBtn" class="btn btn-warning mb-1 col-2" onclick="submitForm()" data-bs-dismiss="modal"> Add </button>
                        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal"> Back </button>
                    </div>
                </div>
            </div>
           </div>
   </div>
    <script>
        function submitForm(){
            $("#userForm").submit();
        }
    </script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
