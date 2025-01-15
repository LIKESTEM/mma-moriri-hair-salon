<%@page import="java.util.Map"%>
<%@page import="com.groupa.mma_moriri.model.Stylist"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>choose Hairdresser | MmaMoriri</title>
        
        <link rel="stylesheet" href="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\css\bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">       
    <link rel="stylesheet" href="css/choose_hairdresser.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        
    </head>
    <body>

         <header>

        <div class="boudary">
    
            <nav>
                
    
                <div></div>
    
                <div></div>
            </nav>
    

    
        </div>

    </header>

    <a href="GetCustomerRecentHomePage"><button class="btn btn-primary">Home</button></a>

    <h1 id="topic">Select a Hairdresser</h1>

    <div class="container list_them">
        
        <%
             Map<Stylist, Integer> mapStylistRate = (Map<Stylist, Integer>) session.getAttribute("mapStylistRate");

             for (Map.Entry<Stylist, Integer> entry: mapStylistRate.entrySet()) {
                     Stylist stylist = entry.getKey();
                     String hairstylistName = stylist.getFirstname();
                     String surname = stylist.getLastname();
                     byte[] imageBytes = stylist.getImage();
                     int rate = entry.getValue();
                     Long stylistId = stylist.getStylist_id();
         %>

        <div id="red" class="hair_style">
            
            <form action="CreateAppointmentDetails" method="POST">
            
            <input type="number" hidden="" name="stylistId" value=<%=stylistId%>>

            <div id="pic_box"  class=" d-flex justify-content-center">
                <%
                    // Check if imageBytes is not null and not empty
                    if (imageBytes != null && imageBytes.length > 0) {
                    String base64Image = new String(java.util.Base64.getEncoder().encode(imageBytes));
                %>
                    <img id="stylist_pic" src="data:image/png;base64, <%= base64Image %>"
                        height="80" alt="hairstylist pic">
                <%
                    }
                %>
            </div>


            <div class="card_content">
                <h3><%=hairstylistName%> <%=surname%></h3>
            </div>
            <div class="container-fluid d-flex justify-content-center t"><input type="submit" class="next_button btn" value="Select"></div>
            
            </form>
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

    <script src="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\js\bootstrap.min.js"></script>
    <script src="hairstyle.js"></script>
    </body>
</html>

