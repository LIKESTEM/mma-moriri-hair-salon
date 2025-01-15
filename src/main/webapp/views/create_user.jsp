<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SignUp | MmaMoriri</title>

    <link rel="stylesheet" href="bootstrap-5.0.2-dist/bootstrap-5.0.2-dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/signup_page.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <header>
        <div class="boundary">
            <nav>
                <div></div>
                <div></div>
            </nav>
        </div>
    </header>

    <div class="login_cover_1 container">
        <div class="the_login container">
            <h5 id="signup">Sign Up</h5>
            <form action="CreateUser" method="POST">
                <div class="mb-3">
                    <label for="fName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="fName" name="firstname" required>
                </div>
                <div class="mb-3">
                    <label for="lName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lName" name="lastname" required>
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="email" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="PNum" class="form-label">Phone Number</label>
                    <input type="number" class="form-control" id="PNum" name="phone_number" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="cPassword" required>
                </div>
                <div class="mb-3">
                    <label for="usertype" class="form-label">Select a user: </label>
                    <select name="userType">
                        <option value="customer">Customer</>
                        <option value="stylist">Stylist</>
                        <option value="finOff">Financial Officer</>
                        <option value="admin">Admin</>
                    </select>
                </di>

                <div class="container d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary" id="submit_button_1">Submit</button>
                </div>
            </form>
        </div>

        <div class="container d-flex justify-content-center">
            <p>already have an account? <a id="login_link" href="Login">LogIn here</a></p>
        </div>
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
