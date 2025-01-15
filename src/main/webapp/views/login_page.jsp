<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>

    <link rel="stylesheet" href="bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login_page.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

<header>

    <div class="boudary">

        <nav>


            <div></div>

            <div></div>
        </nav>


    </div>
</header>

<p style="color:red; text-align:center;">${msg}</p>

<div class="login_cover_1 container">
    <div class="the_login container">
        <h5 id="signup">Log In</h5>
        <form action="CustomerHomePage" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="usertype" class="form-label">User Type</label>
                <select class="form-select" id="usertype" name="usertype" required>
                    <option value="admin">Admin</option>
                    <option value="customer">Customer</option>
                    <option value="financial_officer">Financial Officer</option>
                    <option value="stylist">Stylist</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
    <div class="container d-flex justify-content-center"><p>Don't have an account? <a id="signup_link" href="Signup">Signup</a></p></div>
</div>
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

<script src="js/bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>

</body>
</html>
