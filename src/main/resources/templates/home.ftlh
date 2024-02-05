<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>SKLEPIK</title>

    <style>
        body{
            background-color: #21211f;
            color: #ffffff;
        }
        #container{
            width: 625px;
            margin-left: auto;
            margin-right: auto;
            margin-top: auto;
            margin-bottom: auto;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        #menu{
            text-align:center;
        }
        .menuTile{
            color: #FFFFFF;
            background-color: #edb007;
            width: 200px;
            height: 200px;
            float:left;
            margin-left: 5px;
            border-radius: 10px;
            font-weight: 800px;
            font-size: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
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
   <div id="container">
        <div id="menu">
            <a href="/shop">
                <button class="menuTile"> Shop </button>
            </a>
            <a href="/users">
                <button class="menuTile"> Users </button>
            </a>
            <a href="/products">
                <button class="menuTile"> Products </button>
            </a>
            <div style="clear:both;"> </div>
        </div>
   </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
