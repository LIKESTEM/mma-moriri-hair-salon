<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>admin | MmaMoriri</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/search_user.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <h1 class="headeache">Search user</h1>
    <form action="SearchUser" method="post">




        <div class="container social_dis">

        <div class="info_line">
            <div class="row">
                 <div class="col-6"><label for="staticEmail" class="form-label">Email</label> </div>
                <div class="col-6"><input type="email" class="form-control" id="exampleFormControlInput1" id="username" name="useUsername" placeholder=""></div>
            </div>
<hr>
        <div class="info_line">
            <div class="row">
                 <div class="col-6"><label for="staticEmail" class="form-label">User Type</label> </div>
                <div class="col-6"><select class="form-select" aria-label="Default select example" name="useUsertype">
                                  <option value="customer">Customer</option>
                                              <option value="stylist">Stylist</option>
                                              <option value="financial_officer">Financial Officer</option>
                                              <option value="admin">Admin</option>
                                 </select></div>
            </div>
<hr>
        <div class="info_line">
            <div class="row">
                 <div class="col-6"></div>
                <div class="col-6"><button type="submit" class="btn btn-primary mb-3">Search User</button></div>
            </div>

            </div>
    </form>
</body>
</html>
