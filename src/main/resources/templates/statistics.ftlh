<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <style>
        #itemForm{
        display:none;
        }
    </style>
</head>

<body>
   <div id="container">
        <#if ownerStatistics?has_content>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th> STATYSTYKI </th>
                    </tr>
                    <tr>
                        <th>Imię</th>
                        <th>Całkowita ilość produktów</th>
                    </tr>
                </thead>
                <tbody>
                    <#list ownerStatistics as ownerStatistic>
                        <tr>
                            <td>${ownerStatistic.owner}</td>
                            <td>${ownerStatistic.amount}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>

        </#if>
   </div>




    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
