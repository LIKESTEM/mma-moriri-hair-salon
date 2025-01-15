<%@page import="java.util.List"%>
<%@page import="com.groupa.mma_moriri.model.Payment"%>
<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Appointments | MmaMoriri</title>
        <link rel="stylesheet" href="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\css\bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/financial_officer_home.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <header>

        <div class="boudary">

            <nav>
            </nav>
        </div>
          <style>
                .btn:disabled {
                    background-color: grey;
                    cursor: not-allowed;
                }
            </style>
    </header>

    <%
        String username = (String)session.getAttribute("username");
        String names = (String) session.getAttribute("names");
    %>

    <div class="intro_div d-flex container justify-content-between">
    <div>Hi, <%=names%></div> <a href="Logout"><button class="btn logout_button">Logout</button></a>
    </div>

    <div class="list_them container ">


      <%
          List<Payment> list = (List<Payment>) session.getAttribute("filteredPayments");

          if (list != null && !list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                  Payment payment = list.get(i);
                  String refNo = payment.getAppRefNo();
                  Timestamp date = payment.getDateTime();
      %>

      <div class="container d-flex justify-content-center">
          <div class="indivitual_appointments">

              <form action="AddConfirmedApp" method="post">

                  <input type="hidden" name="username" value="<%=username%>">
                  <input type="hidden" name="appRefNo" value="<%=refNo%>">
                  <input type="hidden" name="status" value="true">

                  <div>
                      <div class="row">
                          <div class="col-9">

                              <div class="info_line">
                                  <div class="row">
                                      <div class="col">Reference Number: </div>
                                      <div class="col"><%=refNo%></div>
                                  </div>
                              </div>

                              <div class="info_line">
                                  <div class="row">
                                      <div class="col">Payment Date: </div>
                                      <div class="col"><%=date%></div>
                                  </div>
                              </div>

                          </div>
                          <div class="d-flex align-items-center col-3">

                              <input type="submit" class="btn check_button" value="Confirm">
                          </div>
                      </div>
                  </div>
              </form>

          </div>
      </div>
      <br/><br/>

      <%
              }
          } else {
      %>

      <div class="container">
          <p>No confirmed payments</p>
      </div>

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
     <script>
            function confirmButton(button) {
                button.value = "Confirmed";
                button.disabled = true;
            }
        </script>
    </body>

</html>
