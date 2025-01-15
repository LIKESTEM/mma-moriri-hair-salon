<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Change password | MmaMoriri</title>
    <link rel="stylesheet" href="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\css\bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/hairdresser_home.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <header>
        <div class="boudary">
            <nav></nav>
        </div>
    </header>
    <div class="intro_div d-flex container justify-content-between">
        <div>Hi, ${names}</div>
        <a href="Logout"><button class="btn logout_button">LogOut</button></a>
    </div>
    <div class="list_them container">
         <h1 class="card-title">Change Password</h1>
             <form action="setNewPassword" method="post">
               <div class="form-group">
                 <label for="currentPassword">Current Password:</label>
                 <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>
                <div class="form-group">
                  <label for="newPassword">New Password:</label>
                  <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                 </div>
                 <div class="form-group">
                   <label for="confirmPassword">Confirm New Password:</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                   </div>
                      <button type="submit" class="btn btn-primary">Change Password</button>
           </form>
    </div>
    <footer>
        <div class="container d-flex justify-content-between">
            <div class="footer_content1">
                © 2024 MmaMoriri. All rights reserved.
            </div>
            <div class="footer_content2">

            </div>
        </div>
    </footer>
</body>
</html>
