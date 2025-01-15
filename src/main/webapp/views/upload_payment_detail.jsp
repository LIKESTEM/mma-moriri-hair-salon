<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload payment | MmaMoriri</title>
    <link rel="stylesheet" href="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\css\bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/hairdresser_home.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <header>
        <div class="boudary">
            <nav> <a href="GetCustomerRecentHomePage"><button class="btn btn-danger">Cancel</button></a></nav>
        </div>
    </header>
    <div class="intro_div d-flex container justify-content-between">
        <div>Hi, ${names}</div>
        <a href="Logout"><button class="btn logout_button">LogOut</button></a>
    </div>
    <div class="list_them container">
    <h1>Upload PDF File</h1>
         <form action="AddPaymentAllRequired" method="post" enctype="multipart/form-data">
                  <label for="appRefNo">Enter appointment reference number, below: </label><br>
                  <input type="text" id="appRefNo" name="appRefNo"><br><br>

                  <label for="pdfFile">Upload a receipt in PDF format, below: </label><br>
                  <input type="file" id="pdfFile" name="pdfFile" accept=".pdf"><br><br>

                  <label for="totAmtPaid">Enter total amount paid: </label><br>
                  <label>R <input type="number" id="totAmtPaid" name="totAmtPaid"></label><br><br>
                  <input class="btn btn-success" type="submit" value="Upload">
              </form>
    </div>
    <footer>
        <div class="container d-flex justify-content-between">
            <div class="footer_content1">
                © 2024 MmaMoriri. All rights reserved.
            </div>
            <div class="footer_content2">
              customer
            </div>
        </div>
    </footer>
</body>
</html>
