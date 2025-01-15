<%@page import="java.util.List"%>
<%@page import="com.groupa.mma_moriri.model.Payment"%>
<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>appointment_selected | MmaMoriri </title>

        <link rel="stylesheet" href="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\css\bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/selected_appointment.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />


    </head>
    <body>

                <%
                      String file = (String)session.getAttribute("file");
        %>

        <header>

        <div class="boudary">

            <nav>
            </nav>
        </div>
    </header>

        <div class="contain_pdf container ">

        <%
                Payment payment = (Payment) session.getAttribute("payment");
                Long fanOffId = (Long) session.getAttribute("fanOffId");

                // Retrieve the PDF receipt content from the session attribute
                byte[] receiptContent = (byte[]) payment.getReceiptPdf();

                if (receiptContent != null && receiptContent.length > 0) {
                    // Convert receipt content to Base64 encoding
                    String base64EncodedReceipt = new String(java.util.Base64.getEncoder().encode(receiptContent));
            %>
                    <p>Reference number: ${payment.getAppRefNo()}</p>
                    <p>Proof Of Payment</p>
                    <!-- Embed the PDF receipt using the Base64-encoded content -->
                    <embed src="data:application/pdf;base64, <%= base64EncodedReceipt %>"
                           type="application/pdf" width="100%" height="500px">
                    <p>Amount paid: R ${payment.getTotalAmtPaid()}</p>

            <%
                } else {
            %>
                    <p>No receipt available.</p>
            <%
                }
            %>

            <form action="UpdatePaymentByFanOff" method="post">
            <input type"hidden" value="<%=fanOffId%>" name="fanOffId" readonly>

            <div>
                <label>Status: </label>
                <select name="status">
                    <option value="true">TRUE</option>
                    <option value="false">FALSE</option>
                </select>
            </div>


            <div class="info_line">
                <div class="row">
                    <div class="col-3"><label for="disabledTextInput" class="form-label">Reason for cancelation</label></div>
                    <div class="col-4"><div class="mb-3">

                        <input type="textarea" id="disabledTextInput" class="form-control" name="statusDesc" placeholder="Reason">
                      </div></div>
                </div>
            </div>

            <div class="info_line">
                <div class="row">
                    <div class="col"><input class="btn btn-primary" type="submit" value="CONFIRM"></div>
                </div>
            </div>

        </form>

        </div>
        <br/><br/>

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
