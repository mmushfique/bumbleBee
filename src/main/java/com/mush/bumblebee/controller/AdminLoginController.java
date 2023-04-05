package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Admin;
import com.mush.bumblebee.service.AdminService;
import com.mush.bumblebee.service.AdminServiceImpl;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginController extends HttpServlet {
    private final AdminService service;
    public AdminLoginController(){
        this.service=AdminServiceImpl.getAdminServiceInstance();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminEmail = request.getParameter("adminEmail");
        String adminPassword = request.getParameter("password");
        String message="";

        try {
            Admin admin =service.verifyLogin(adminEmail,adminPassword);
            if (admin!=null)
            {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("loggedUser",admin.getAdminFirstName());
                httpSession.setAttribute("role","ADMIN");

                RequestDispatcher rd=request.getRequestDispatcher("adminPanel.jsp");
                rd.forward(request, response);
            }
            else {
                message="Incorrect username or password, please try again";
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            message=e.getMessage();
        }

        request.setAttribute("message", message);
        RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
