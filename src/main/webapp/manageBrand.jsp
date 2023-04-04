<%response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.mush.bumblebee.dao.DbConnection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    HttpSession httpSession = request.getSession();
    String user = (String) httpSession.getAttribute("loggedUser");
    String role = (String) httpSession.getAttribute("role");
    role="ADMIN";//need to remove this after login is implemented
    if (role != null && role.equals("ADMIN")) {
    Connection connection = DbConnection.getConnection();
    Statement st = connection.createStatement();
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="bootstrap icons/bootstrap-icons.css">
    <link
      rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/admin.css" />
    <link rel="stylesheet" href="css/adminPanel.css" />

    <title>Bumble Bee</title>

  </head>
   <body id="body">
     <div class="container">
       <nav class="navbar">
         <div class="nav_icon" onclick="toggleSidebar()">
           <i class="fa fa-bars" aria-hidden="true"></i>
         </div>
         <div class="navbar__left">
           <a id="us" class="active_link" href="index.jsp">Dashboard</a>
           <a href="#">Admin</a>
         </div>

       </nav>

       <main>
         <div class="main__container">
           <!-- MAIN TITLE STARTS HERE -->

           <div class="main__title">
             <img src="#" alt="" />
             <div class="main__greeting">
               <h1>Hello <%=user%></h1>
               <p>Welcome to your admin dashboard</p>
             </div>
           </div>
           <!--------------------------------- MAIN TITLE ENDS HERE ----------------------------->

  <!----------------------------------------------------------------------------------------------------------->
         <br>
         <div id="usDiv">
           <center>
              <h1>Manage Brands</h1>
              <br>
              <div class="search" style="display:flex;margin-left:300px ">

                <!------Command to create data------->
                 <button type="submit" style="background:lightblue">
                 <a  style="text-decoration:none" href='manageBrand.jsp?T=CREATE'>New brand</a></button>


                <!------Form to search all data------->
              <form class="form-inline" action="brand" method="get">
                <div>
                    <button type="submit" style="background:lightgreen" >All brands</button>
                </div>
              </form>

                <!------Form to search data------->
              <form class="form-inline" action="brand" method="get">
                <div>
                  <input type="hidden" name="type" value="specific"/>
                  <input type="hidden" name ="brandName" value="${brandList[0].brandName}"/>
                  <input type="text" placeholder="Search by brand name" name="brandName">
                  <button type="submit"><i class="fa fa-search"></i></button>
                </div>
              </form>
         </div>
         <br>
         </center>

         <div class="container">
                    <div class="inner">
                        <div class="row">
                            <div class="col-md-10">


                                <%if(request.getParameter("T")!=null){ %>
                                  <form action="brand" method="post" class="editForm">
                                    <input type="hidden" name="type" value="register"/>
                                    <label>Enter brand name</label>
                                    <input type="text" required name="brandName"/>
                                    <button name="btnCreate" id="btnCreate" type="submit" >Create Brand</button>
                                  </form>
                                  <br><br><br><br>
                               <%}%>

                                <!------Edit form loads here------->
                               <tag:if test="${CRUDTYPE == 'EDIT'}">
                                  <form action="brand" method="post" class="editForm">
                                    <input type="hidden" name="type" value="update"/>
                                    <input type="hidden" name="id" value="${brandList[0].id}"/>
                                    <label>Edit brand name</label>
                                    <input type="text" required name="brandName" value="${brandList[0].brandName}"/>
                                    <button type="submit" >Update Brand </button>

                                  </form>
                                  <br><br><br><br>
                               </tag:if>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-10">
                            <table class="styled-table">
                                <thead class="bg-light">
                                    <tr>
                                        <th scope="col" style="min-width: 120px">Brand Id</th>
                                        <th scope="col" style="min-width: 120px">Brand Name</th>
                                        <th scope="col" style="min-width: 120px">Edit</th>
                                        <th scope="col" style="min-width: 120px">Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tag:forEach var="brand" items="${brandList}">
                                        <tr>
                                            <td>${brand.id}</td>
                                            <td>${brand.brandName}</td>
                                            <td>
                                               <form class="form-inline" action="brand" method="get">
                                                   <input type="hidden" name ="type" value="specific"/>
                                                   <input type="hidden" name ="brandName" value="${brand.brandName}"/>
                                                   <button type="submit" style="background:yellow;padding-right:20px;padding-left:20px;" >Edit</button>
                                                </form>
                                            </td>
                                            <td>
                                               <form class="form-inline" action="brand" method="post">
                                                   <input type="hidden" name ="type" value="delete"/>
                                                   <input type="hidden" name ="brandName" value="${brand.brandName}"/>
                                                   <button type="submit" style="background:red;padding-right:20px;padding-left:20px;" >Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </tag:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<!--------------------------------------------------------------------------------------------------------------------------->
  </div>
      </main>

      <div id="sidebar">
        <div class="sidebar__title">
          <div class="sidebar__img">
            <h1>Bumble Bee</h1>
          </div>
          <i
            onclick="closeSidebar()"
            class="fa fa-times"
            id="sidebarIcon"
            aria-hidden="true"
          ></i>
        </div>

       <div class="sidebar__menu">
          <div class="sidebar__link active_menu_link">
            <i class="fa fa-home"></i>
            <a href="index.jsp">Home</a>
          </div>
          <h2>Manage</h2>
          <div class="sidebar__link">
            <i class="fa fa-user-secret" aria-hidden="true"></i>
            <a href="inventory">Inventory Management</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-user-secret" aria-hidden="true"></i>
            <a href="product">Product Management</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-building-o"></i>
            <a href="category">Category Management
                <form class="form-inline" action="category" method="get">
                    <button class="ancbutton" type="submit" style="background:lightgreen" ></button>
                </form>
            </a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-users" aria-hidden="true"></i>
            <a href="brand">Brand Management
                <form class="form-inline" action="brand" method="get">
                    <button class="ancbutton" type="submit" style="background:lightgreen" ></button>
                </form>
            </a>
          </div>
          <h2>Information</h2>
          <div class="sidebar__link">
            <i class="fa fa-archive"></i>
            <a href="customer">Customer Details</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-book" aria-hidden="true"></i>
            <a href="report">View reports</a>
          </div>
          <div class="sidebar__logout">
            <i class="fa fa-power-off"></i>
            <a href="LogoutServlet">Log out</a>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    <script src="js/adminPanel.js"></script>

        <%} else {
            String redirectURL = "/bumbleBee/errorDis.html";
            response.sendRedirect(redirectURL);
        }%>
  </body>
</html>