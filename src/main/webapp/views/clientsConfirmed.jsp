<%@ page import="com.groupa.mma_moriri.model.Appointment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hairdresser login | MmaMoriri</title>
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
             <%
                 List<Appointment> confirmed = (List<Appointment>) session.getAttribute("confirmed");
                    if (confirmed != null) {
                        for (int i = 0; i < confirmed.size(); i++) {
                             Appointment app = confirmed.get(i);
                             String refNo = app.getRefNo();
                             Timestamp dateTime = app.getDateTime();
                             Integer styleId = app.getStyleId();
                              boolean active = app.isActive();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                              if(active){
      %>
                                <div class="container d-flex justify-content-center">
                                    <div class="indivitual_appointments">
                                    <form action="startRatting" method="GET">
                                     <input type="hidden" name="refNo" value="<%=refNo%>">
                                        <div class="container">
                                            <div class="row">
                                                <div class="">
                                                    <div class="info_line">
                                                        <div class="row">
                                                            <div class="col-6">Appointment reference number: </div>
                                                            <div class="col-6"><%= refNo %></div>
                                                        </div>
                                                    </div>

                                                    <div class="info_line">
                                                        <div class="row">
                                                            <div class="col-6">Hairstyle ID: </div>
                                                            <div class="col-6"><%= styleId %></div>
                                                        </div>
                                                    </div>

                                                    <div class="info_line">
                                                        <div class="row">
                                                            <div class="col">Appointment Date</div>
                                                            <div class="col"><%= sdf.format(dateTime) %></div>
                                                        </div>
                                                    </div>

                                                   <div class="info_line">
                                                        <div class="row">
                                                             <div class="col">Appointment status: </div>
                                                            <div class="col"><%= active ? "Active" : "Inactive" %></div>
                                                        </div>
                                                    </div>
                                                        <hr>
                                                    <div class="info_line">
                                                        <div class="row">
                                                             <div class="col"> </div>
                                                            <div class="col"><button type="submit" class="btn btn-primary mb-3">RATE</button></div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        </form>
                                    </div>
                                </div>
                                <br><br>
                                <%
                                      }
                                     }
                                    } else {
                                %>
                                        <p>No Appointments</p>
                                <%
                                    }
                                        %>
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
