<%@ page import="java.util.List" %>
<%@page import="com.groupa.mma_moriri.model.Payment"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Financial Officer | MmaMoriri</title>

    <link rel="stylesheet" href="css/financial_officer_home.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Add your CSS files here -->
</head>
<body>
<header>
    <!-- Your header content here -->
</header>

    <%
        String names = (String) session.getAttribute("names");
    %>

<div class="intro_div d-flex container justify-content-between">
    <div>Hi, <%=names%></div>
    <a href="Logout"><button class="btn logout_button">Logout</button></a>
</div>

<div class="list_them container">
    <c:forEach var="payment" items="${payments}">
        <div class="container d-flex justify-content-center">
            <div class="indivitual_appointments">
                <form action="UserPaymentPage" method="post">
                    <input type="text" hidden name="appRefNo" value="${payment.getAppRefNo()}" id="">

                    <div>
                        <div class="row">
                            <div class="col-9">
                                <div class="info_line">
                                    <div class="row">
                                        <div class="col">Appointment reference number: </div>
                                        <div class="col">${payment.getAppRefNo()}</div>
                                    </div>
                                </div>
                                <div class="info_line">
                                    <div class="row">
                                        <div class="col">Payment Date</div>
                                        <div class="col">${payment.dateTime}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex align-items-center col-3">
                                <input type="submit" class="btn check_button" value="Check">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

<footer>
    <!-- Your footer content here -->
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
