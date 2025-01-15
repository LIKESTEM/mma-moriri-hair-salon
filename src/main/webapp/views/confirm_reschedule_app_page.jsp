
<%@page import="com.groupa.mma_moriri.model.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finalise Appointment | MmaMoriri</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/finalise_appointment_creation.css">

    </head>
    <body>

        <%
            Appointment appointment = (Appointment) session.getAttribute("appointment");

            String hairstyleName = (String)session.getAttribute("hairstyleName");
            String stylistName = (String)session.getAttribute("stylistName");
            String dateTime = appointment.getDateTime().toString();

        %>

         <header>

        <div class="boudary">

            <nav>
            </nav>
        </div>
    </header>
     <a href="GetCustomerRecentHomePage"><button class="btn btn-danger">Cancel</button></a>

    <main>
        <h1 class="text-center">Finalise Rescheduling an Appointment</h1>
        <form action="SaveRescheduledAppointment" method="GET">
        <div class="box_wrap container d-flex justify-content-center align-items-center">

            <div class="box d-flex justify-content-center">

                <div class="content_in_box">
                    <div class="detail">
                        <div class="row">
                            <div class="col-4">
                                <label for="exampleInputEmail1" class="form-label">Hairstyle Chosen</label>
                            </div>
                            <div class="col-6">
                                <label for="exampleInputEmail1" class="form-label"><%=hairstyleName%></label>
                            </div>
                        </div>
                    </div>

                    <div class="detail">
                        <div class="row">
                            <div class="col-4">
                                <label for="exampleInputEmail1" class="form-label">HairStylist Chosen</label>
                            </div>
                            <div class="col-6">
                                <label for="exampleInputEmail1" class="form-label"><%=stylistName%></label>
                            </div>
                        </div>
                    </div>

                    <div class="detail">
                        <div class="row">
                            <div class="col-4">
                                <label for="exampleInputEmail1" class="form-label">Date-Time</label>
                            </div>
                            <div class="col-6">
                                <label for="exampleInputEmail1" class="form-label"><%=dateTime%></label>
                            </div>
                        </div>
                    </div>

                    <div class="container-fluid d-flex justify-content-center">
                        <a href="#"><button type="submit" class="btn btn-primary" id="create_appointment">Confirm</button></a>
                    </div>
                </div>
            </div>
        </div>
    </form>
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
