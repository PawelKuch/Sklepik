<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>

<body>
     <div class="container">
       <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
         <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
           <span class="fs-4">Simple Shop</span>
         </a>

         <ul class="nav nav-pills">
           <li class="nav-item"><a href="/" class="nav-link">Shop</a></li>
           <li class="nav-item"><a href="/users" class="nav-link">Users</a></li>
           <li class="nav-item"><a href="/products" class="nav-link">Products</a></li>
         </ul>
       </header>
     </div>

    <script>
        $(document).ready(function() {
            var currentUrl = window.location.pathname;
            $(".nav-link").each(function() {
                var linkUrl = $(this).attr("href");
                if(currentUrl === linkUrl){
                    $(this).addClass("active");
                }
            });
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
