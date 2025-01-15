<%@page import="java.util.List"%>
<%@page import="com.groupa.mma_moriri.model.HairStyle"%>
<%@page import="org.springframework.web.multipart.MultipartFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>choose Hairstyle | MmaMoriri</title>
        
        <link rel="stylesheet" href="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\css\bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">       
    <link rel="stylesheet" href="css/choose_hairstyle.css">
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

    <h1 id="topic">Select a HairStyle</h1>

    <div class="container list_them">
        
        <%

          List<HairStyle> list = (List<HairStyle>) session.getAttribute("hairStyleList");

          for (int i = 0; i < list.size(); i++) {
              HairStyle hairStyle = list.get(i);
              String hairstyleName = hairStyle.getName();
              double price = hairStyle.getPrice();
              byte[] imageBytes = hairStyle.getImage();

              // Check if imageBytes is not null and not empty
              if (imageBytes != null && imageBytes.length > 0) {
                  String base64Image = new String(java.util.Base64.getEncoder().encode(imageBytes));
                                                                                                
        %>

        <div id="red" class="hair_style">
            <form action="GetStylistsPage" method="POST">

                <input type="hidden" name="hairstyleName" value="<%=hairstyleName%>">

            <div id="pic_box"  class=" d-flex justify-content-center">
                <img src="data:image/png;base64, <%= base64Image %>"
                     alt="pic" style="max-width: 100%; max-height: 100%;">
            </div>
            
            
            <div class="card_content">
                <h3><%=hairstyleName%> - R<%=price%></h3>
            </div>

            <div class="container-fluid d-flex justify-content-center t"><input type="submit" class="next_button btn" value="Select"></div>
            
            
            </form>
        </div>


                <%
                }
                  }  
                %>
                
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

    <script src="bootstrap-5.0.2-dist\bootstrap-5.0.2-dist\js\bootstrap.min.js"></script>
    <script src="hairstyle.js"></script>
    </body>
</html>
