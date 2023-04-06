<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Bumble Bee</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="bootstrap icons/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/login.css" />
                <link rel="stylesheet" href="css/common.css" />

  </head>

  <body>
    <form class="signUp" id="signupForm" method="POST" action="customers">
      <span id="switchLogin" class="bi bi-person-check"></span>
      <span class="bi bi-person-plus"></span>
      <h4>Sign up</h4>
      <div class="block"
        <div>
          <input class="form-control" type="text" id="firstName" name="customerFirstName" placeholder="First Name" required />
          <small>error</small>
        </div>
        <div>
          <input class="form-control" type="text" id="lastName" name="customerLastName" placeholder="Last Name" required/>
          <small>error</small>
        </div>
        <div>
          <input class="form-control" required type="date" id="dob" name="customerDOB" placeholder="Last Name" required/>
          <small>error</small>
        </div>
        <div>
          <input class="form-control" type="email" id="email" name="customerEmail" placeholder="Email" required/>
          <small id="emailError">error</small>
        </div>
        <div>
          <input class="form-control" type="password" id="password" name="password" placeholder="Password" required/>
          <small>error</small>
        </div>
        <div>
          <input class="form-control" type="password" id="repassword" name="repass" placeholder="Re-Enter Password" required/>
          <small>error</small>
        </div>
      </div>
      <button class="btn btn-primary btn-sm" type="submit" id="signupBtn" >Signup</button>
      <div style="color:red">${message}</div>
    </form>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
    <script src="js/common.js"></script>
  </body>

</html>