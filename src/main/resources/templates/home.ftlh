<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>SKLEPIK</title>

    <style>
        #container{
            width:100%;
            margin-left:auto;
            margin-right: auto;
        }
        #menu{
            text-align:center;
            border: 2px solid #000000;
        }
        .menuTile{
            border: solid 1px #000000;
            float:left;
        }

    </style>
</head>

<body>
   <div id="container">
        <div id="menu">
            <a href="/shop">
                <div class="menuTile">
                    Shop
                </div>
            </a>
            <a href="/products">
                <div class="menuTile">
                    Products
                </div>
            </a>
            <a href="/statistics">
                <div class="menuTile">
                    Statistics
                </div>
            </a>
            <div style="clear:both;"> </div>
        </div>
   </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
