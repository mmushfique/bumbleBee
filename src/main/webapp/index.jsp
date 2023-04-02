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
    role="ADMIN";
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

          <!--------------------------------- MAIN CARDS STARTS HERE ---------------------------->
          <div class="main__cards">

          <!------Card 1------->
            <div class="card">
              <i class="fa fa-user-o fa-2x text-lightblue" aria-hidden="true"></i>
              <div class="card_inner">
                <p class="text-primary-p">Number of Categories</p>
                <%
                //query="SELECT COUNT(*) FROM category";
                //ResultSet r = st.executeQuery(query);
                //r.next();
                %>
                <span class="font-bold text-title">67</span>
              </div>
            </div>

          <!------Card 2------->
            <div class="card">
              <i class="fa fa-bell" aria-hidden="true"></i>
              <div class="card_inner">
                <p class="text-primary-p">Number of brands</p>
                <%
                String query="SELECT COUNT(*) FROM brand";
                ResultSet r = st.executeQuery(query);
                r.next();
                %>
                <span class="font-bold text-title"><%=r.getLong(1)%></span>
              </div>
            </div>

          <!------Card 3------->
            <div class="card">
              <i
                class="fa fa-thumbs-up fa-2x text-green"
                aria-hidden="true"
              ></i>
              <div class="card_inner">
                <p class="text-primary-p">Number of products</p>
                <%
                %>
                <span class="font-bold text-title">100</span>
              </div>
            </div>

          <!------Card 4------->
            <div class="card">
               <i class="fa fa-flag" aria-hidden="true" ></i>

              <div class="card_inner">
                <p class="text-primary-p">Number of Customers</p>
                <%

                %>
                <span class="font-bold text-title">244</span>
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
            <a href="manageInventory">Inventory Management</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-user-secret" aria-hidden="true"></i>
            <a href="manageProduct">Product Management</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-building-o"></i>
            <a href="manageCategory">Category Management</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-users" aria-hidden="true"></i>
            <a href="manageBrand.jsp">Brand Management</a>
          </div>
          <h2>Information</h2>
          <div class="sidebar__link">
            <i class="fa fa-archive"></i>
            <a href="customer.jsp">Customer Details</a>
          </div>
          <div class="sidebar__link">
            <i class="fa fa-book" aria-hidden="true"></i>
            <a href="report.jsp">View reports</a>
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