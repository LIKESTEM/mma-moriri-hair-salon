<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.groupa.mma_moriri.model.Customer" %>
<%@ page import="com.groupa.mma_moriri.model.Appointment" %>
<%@ page import="com.groupa.mma_moriri.model.HairStyle" %>
<%@ page import="com.groupa.mma_moriri.service.CustomerService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/client_home.css">
        <link rel="stylesheet" href="css/https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
    <header>

        <div class="boudary">

            <nav>
            </nav>
        </div>

    </header>

	  <div class="intro_div d-flex container justify-content-between">
        <div>Hi, ${names}</div> <a href="Logout"><button class="btn logout_button">LogOut</button></a>
    </div>

    <div class="container hold_content">

        <div>
            <div class="row">
                <div class="container col-3 side_bar">

                    <h3 class="text-center">SideBar</h3>

                    <ul>
                        <li id="view_appointment"><a href="#list_appointments">Appointments</a></li>
                        <li id="view_update"><a href="#update_details">Update Details</a></li>
                        <li id="view_update"><a href="GetNewPassWordSet">Update Password</a></li>
                        <li id="view_reschedule"><a href="#rescedule_appointment">Reschedule</a></li>
                        <li id="view_create"><a href="GetAllHairStyles">Create Appointment</a></li>
                        <li id="view_uploadPayment"><a href="UploadPaymentDetailsPage">Upload Payment</a></li>
                        <li id="view_uploadPayment"><a href="GetDeleteConfirmPage">Delete Account</a></li>
                        <li id="view_update"><a href="GetClientsConfirmedAppointments">Rate Service</a></li>

                        <li><a href=""></a></li>
                    </ul>

                </div>



            <div class="col-9 content_of_template">
                <div class="list_appointments" id="list_appointments">
                    <%
                        List<Appointment> list = (List<Appointment>) session.getAttribute("list");
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                Appointment app = list.get(i);
                                String refNo = app.getRefNo();
                                Timestamp dateTime = app.getDateTime();
                                Integer styleId = app.getStyleId();
                                boolean active = app.isActive();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    %>
                    <div class="container d-flex justify-content-center">
                        <div class="indivitual_appointments">
                        <form action="DeleteAppointment" method="POST">
                        <input type="hidden" value="<%=refNo%>" name="refNo">
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
                                                <div class="col"><button type="submit" class="btn btn-danger mb-3">Cancel</button></div>
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
                        } else {
                    %>
                            <p>No Appointments</p>
                    <%
                        }
                    %>
                </div>
                    <div class="update_details dont_show" id="update_details">
                        <div class="container hold_it">

                            <form action="/UpdateCustomer" method="post">
                            <div class="Appointment_box">
                                <div class="line_info">
                                    <div class="row">
                                        <div class="col">
                                            First Name
                                        </div>

                                        <div class="col">
                                            <input type="text" class="form-control" name="firstname" required="" id="exampleInputEmail1" aria-describedby="emailHelp">
                                        </div>
                                    </div>
                                </div>

                                <div class="line_info">
                                    <div class="row">
                                        <div class="col">
                                            Last Name
                                        </div>

                                        <div class="col">
                                            <input type="text" class="form-control" name="lastname" required="" id="exampleInputEmail1" aria-describedby="emailHelp">
                                        </div>
                                    </div>
                                </div>

                                <div class="line_info">
                                    <div class="row">
                                        <div class="col">
                                            Phone Number
                                        </div>

                                        <div class="col">
                                            <input type="number" class="form-control" name="phone_number" required="" id="exampleInputEmail1" aria-describedby="emailHelp">
                                        </div>
                                    </div>
                                </div>

                                <div class="line_info">
                                    <div class="row">
                                        <div class="col">

                                        </div>

                                        <div class="col">
                                            <input type="submit" class="form-control sub_23" value="Update"  id="updateDetails" aria-describedby="emailHelp">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </form>
                        </div>
                    </div>

                <div class="rescedule_appointment dont_show" id="rescedule_appointment">
                    <div class="container hold_it">
                        <form action="RescheduleAppointmentPage" method="post">
                            <div class="Appointment_box">
                                <div class="line_info">
                                    <div class="row">
                                        <div class="col">
                                            Appointment Ref-Number
                                        </div>
                                        <div class="col">
                                            <input type="text" class="form-control" required="" name="refNo" id="exampleInputEmail1" aria-describedby="emailHelp">
                                        </div>
                                    </div>
                                </div>
                                <div class="line_info">
                                    <div class="row">
                                        <div class="col">
                                        </div>
                                        <div class="col">
                                            <input type="Submit" class="btn btn-primary" id="exampleInputEmail1" value="Submit">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                </div>
            </div>
        </div>



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
    <script src="${pageContext.request.contextPath}/js/client_home.js"></script>

</body>
</html>