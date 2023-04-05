<%@page import="java.io.PrintWriter"%>


<!-- <%@page contentType="text/html" pageEncoding="UTF-8" %> -->
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="bootstrap icons/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/login.css" />
                <link rel="stylesheet" href="css/common.css" />

  </head>

  <body>
    
     <header id="header">
           <jsp:include page="header.jsp" />  
        </header>
   

    <form class="login" id="loginForm" action="adminLogin" method="POST">
      <span id="switchSignup" class="bi bi-person-plus"></span>
      <span class="bi bi-person-check"></span>
      <h4>Sign In</h4>
      <div class="block">
        <input class="form-control" type="email" id="emailcheck" name="adminEmail" placeholder="username" />
        <input class="form-control" type="password" id="passwordcheck" name="password"
               placeholder="Password" />
        <small> </small>
      </div>
 <div class="btn-group w-100 ">
        <button class="btn btn-primary btn-sm" type="submit" id="loginBtn">Login</button>
        <a class="btn btn-danger btn-sm" id="forgotpassBtn"  href="#">Forgot Password?</a>
      </div>

    </form>



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
    <script src="js/common.js"></script>
  </body>

</html>