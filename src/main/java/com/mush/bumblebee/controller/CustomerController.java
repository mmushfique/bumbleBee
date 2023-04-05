/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Customer;
import com.mush.bumblebee.service.CustomerService;
import com.mush.bumblebee.service.CustomerServiceImpl;
import com.mush.bumblebee.util.PasswordEncryption;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet("/customers")
public class CustomerController extends HttpServlet {

    private final CustomerService service;

    public CustomerController() {
        this.service = CustomerServiceImpl.getCustomerServiceInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("type");

        if (flag != null && flag.equals("customerDet")) {
            viewACustomerWithAllDetails(request, response);
        } else {
            viewAllCustomers(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";

        String customerUniqueID = UUID.randomUUID().toString();
        String customerFirstName = request.getParameter("customerFirstName");
        String customerLastName = request.getParameter("customerLastName");
        String customerDOB = request.getParameter("customerDOB");
        String customerEmail = request.getParameter("customerEmail");
        String password = request.getParameter("password");
        password = PasswordEncryption.encryptPassword(password);

        Customer customer = new Customer(customerUniqueID, customerFirstName, customerLastName, customerDOB, customerEmail, password);

        try {
            boolean result = service.signUp(customer);
            if (result) {
                message = "The product " + customerFirstName + " has been added successfully";
                response.sendRedirect("/bumbleBee/index.jsp");
            } else {
                message = "Failed to add the product " + customerFirstName;
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("customerSignUp.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
    }

    private void viewAllCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Customer> customerList;

        try {
            customerList = service.getAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            customerList = new ArrayList<Customer>();
        }

        request.setAttribute("message", message);
        request.setAttribute("customerList", customerList);

        RequestDispatcher rd = request.getRequestDispatcher("manageCustomer.jsp");
        rd.forward(request, response);
    }

    private void viewACustomerWithAllDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        Customer customer;
        String customerUniqueId=request.getParameter("customerUniqueId");

        try {
            customer = service.getACustomerWithAllDetails(customerUniqueId);
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            customer = new Customer();
        }

        request.setAttribute("message", message);
        request.setAttribute("customer", customer);
        request.setAttribute("table", "table");

        RequestDispatcher rd = request.getRequestDispatcher("manageCustomer.jsp");
        rd.forward(request, response);
    }
}
