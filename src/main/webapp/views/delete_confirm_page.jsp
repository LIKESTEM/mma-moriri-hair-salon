<%@page import="com.groupa.mma_moriri.model.Customer"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search User | MmaMoriri</title>
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
          <h1>Search user</h1>
          <%
                Customer customer = (Customer) session.getAttribute("customer");
                String useUsername = customer.getUsername();
                String useNames = customer.getFirstname() + " " + customer.getLastname();
            %>

            <label for="username">Username: </label><br>
            <label for="usernameValue"><%=useUsername%></label><br>

            <label for="Name">Name: </label>
            <label for="nameValue"><%=useNames%></label><br>

            <a href="RemoveCustomer"><button type="submit">Delete Account</button></a>

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
</body>
</html>
