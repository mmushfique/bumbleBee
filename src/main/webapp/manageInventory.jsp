<%response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    HttpSession httpSession = request.getSession();
    String user = (String) httpSession.getAttribute("loggedUser");
    String role = (String) httpSession.getAttribute("role");
    if (role != null && role.equals("ADMIN")) {
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
           <a id="us" class="active_link" href="adminPanel.jsp">Dashboard</a>
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
              <h1>Manage Inventory</h1>
              <br>
              <div style="color:red">${message}</div>
              <br>
              <div class="search" style="display:flex;margin-left:300px ">

                <!------Command to fetch brand and categories to create data------->
              <form class="form-inline" action="inventory" method="get">
                <div>
                    <input type="hidden" name="type" value="fetchProduct"/>
                    <button type="submit" style="background:lightgreen" >New inventory</button>
                </div>
              </form>


                <!------Form to search all data------->
              <form class="form-inline" action="inventory" method="get">
                <div>
                    <button type="submit" style="background:lightgreen" >All inventorys</button>
                </div>
              </form>

                <!------Form to search -----
              <form class="form-inline" action="inventory" method="get">
                <div>
                  <input type="hidden" name="type" value="search"/>
                  <input type="text" placeholder="Search by inventory name" name="">
                  <button type="submit"><i class="fa fa-search"></i></button>
                </div>
              </form>-->
         </div>
         <br>
         </center>

         <div class="container">
                    <div class="inner">
                        <div class="row">
                            <div class="col-md-10">


                               <tag:if test="${CRUDTYPE == 'FETCHED_DATA'}">
                                  <form action="inventory" method="post" class="editForm">
                                    <input type="hidden" name="type" value="create"/>

                                    <h3>Add new inventory</h3>

                                    <select class="form-select" name="inventoryForProductId">
                                            <option selected value="${product.productUniqueId}">Select product</option>
                                        <tag:forEach items="${productList}" var="product">
                                            <option value="${product.productUniqueId}">${product.productName}</option>
                                        </tag:forEach>
                                    </select>
                                    <br>
                                     <label>Enter the number of products added</label>
                                     <input type="number" step=".01" required name="inventoryQuantity"/>

                                    <button type="submit" >Save record</button>
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
                                        <th scope="col" style="min-width: 120px">Inventory Unique ID</th>
                                        <th scope="col" style="min-width: 70px">Inventory added</th>
                                        <th scope="col" style="min-width: 120px">Record created At</th>
                                        <th scope="col" style="min-width: 120px">Product Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tag:forEach var="inventory" items="${inventoryList}">
                                        <tr>
                                            <td>${inventory.inventoryUniqueId}</td>
                                            <td>${inventory.inventoryQuantity}</td>
                                            <td>${inventory.inventoryUpdatedTime}</td>
                                            <td>${inventory.inventoryForProductName}</td>

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
                                    <a href="inventory">Inventory Management
                                      <form class="form-inline" action="category" method="get">
                                          <button type="submit" style="background:lightgreen" ></button>
                                      </form>
                                  </a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-user-secret" aria-hidden="true"></i>
                       <a href="product">Product Management
                          <form class="form-inline" action="category" method="get">
                              <button type="submit" style="background:lightgreen" ></button>
                          </form>
                      </a>
          </div>
          <div class="sidebar__link">
                      <i class="fa fa-building-o"></i>
                      <a href="category">Category Management
                          <form class="form-inline" action="category" method="get">
                              <button  type="submit" style="background:lightgreen" ></button>
                          </form>
                      </a>
                    </div>

                    <div class="sidebar__link">
                      <i class="fa fa-users" aria-hidden="true"></i>
                      <a href="brand">Brand Management
                          <form class="form-inline" action="brand" method="get">
                              <button  type="submit" style="background:lightgreen" ></button>
                          </form>
                      </a>
                    </div>
          <h2>Information</h2>
          <div class="sidebar__link">
            <i class="fa fa-archive"></i>
                        <a href="customers">Customer Details
                          <form class="form-inline" action="brand" method="get">
                              <button  type="submit" style="background:lightgreen" ></button>
                          </form>
                      </a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-book" aria-hidden="true"></i>
                        <a href="adminPanel.jsp">View reports
                          <form class="form-inline" action="brand" method="get">
                              <button  type="submit" style="background:lightgreen" ></button>
                          </form>
                      </a>
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