
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>appointment created | MmaMoriri</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/account_created_successful.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>

        <%
            String refNo = (String) session.getAttribute("refNo");
        %>

        <header>

        <div class="boudary">

            <nav>
            </nav>
        </div>

    </header>

    <a href="GetCustomerRecentHomePage"><button class="btn btn-primary">Home</button></a>

    <main>
        <div class="box_wrap container d-flex justify-content-center align-items-center">

            <div class="box d-flex justify-content-center ">
                <div class="">
                    <div class="d-flex justify-content-center">
                        <div class="check_box d-flex justify-content-center align-items-center">
                            <i class="fa-solid fa-check fa-beat"></i>
                    </div>
                    </div>
                    <div id="sub_topic">${msg}</div>
                </div>

            </div>

        </div>
    </main>

    <footer>

        <div class="container d-flex justify-content-between">
            <div class="footer_content1">

                © 2024 MmaMoriri. All rights reserved.
            </div>

            <div class="footer_content2">
                employee

            </div>
        </div>

    </footer>
    </body>
</html>
