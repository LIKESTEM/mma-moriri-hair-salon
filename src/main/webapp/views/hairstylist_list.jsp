<%@page import="java.util.Map"%>
<%@page import="com.groupa.mma_moriri.model.Stylist"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HairDresser | MmaMoriri</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="hairstylist.css">
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

            <h1 id="topic">Mma_Moriri's Stylists</h1>
            
            
        <div class="container list_them">

        <%
            Map<Stylist, Integer> mapStylistRate = (Map<Stylist, Integer>) session.getAttribute("mapStylistRate");
            
            for (Map.Entry<Stylist, Integer> entry: mapStylistRate.entrySet()) {
                    Stylist stylist = entry.getKey();
                    String name = stylist.getFirstname();
                    String surname = stylist.getLastname();
                    byte[] imageBytes = stylist.getImage();
                    int rate = entry.getValue();
                    String phoneNum = "" + stylist.getPhone_number();
                    String username = stylist.getUsername();
        %>
        
        <div class="container hairstylist_box" onmouseover="popItUp()">
            <div class="row">
                <div class="col-6 d-flex align-items-center">
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
                <div class=" col-6">

                    <div>
                        <h3><%=name%> <%=surname%></h3>
                        <h4><%=phoneNum%></h4>
                        
                        <%
                            for(int j=0; j< 5; j++){
                                
                            if(rate > 0){
                                
                                %>
                                
                                <i class="fa fa-star star_rating_yellow" aria-hidden="true"></i>
                            
                                <%
                                
                                rate--;
                            }else{
                                %>
                                <i class="fa fa-star star_rating_black" aria-hidden="true"></i>
                                
                                <%
                                        } 

                                }
                        %>
                    </div>

                </div>
            </div>
                    
                    <div class="more_info" id="info">
                                   
                               </div>
                    
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
        
        
        <script src="hairstylist.js"> </script>
    </body>
</html>
