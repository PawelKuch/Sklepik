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
        #container {
            align-items: center;
            text-align: center;
            max-width: 400px;
            margin-left: auto;
            margin-right: auto;
        }
        #backBtn {
            width: 80px;
            color: #FFFFFF;
        }
        #addBtn {
            color: #FFFFFF;
            background-color: #edb007;
            margin-right: auto;
            margin-left: auto;
        }
        #inputField {
            width: 400px;
            margin-right: auto;
            margin-left: auto;
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
   <div id="container">

   Enter a username:
           <form id="userForm" action="/users" method="post" clas="mt-3">
               <input id="inputField" type="text" name="userName" class="form-control mb-3" placeholder="first name">
               <button id="addBtn" type="submit" class="btn mb-3">Add user</button>
           </form>
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
        <a href="/"><button id="backBtn" class="btn btn-dark">Back</button></a>

   </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
