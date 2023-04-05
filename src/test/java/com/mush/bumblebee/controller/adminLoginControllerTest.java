package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Admin;
import com.mush.bumblebee.service.AdminService;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.*;

@DisplayName("Admin login controller tests")
class adminLoginControllerTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private RequestDispatcher requestDispatcher;
    private AdminService service;
    private AdminLoginController adminLoginController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.httpSession=mock(HttpSession.class);
        this.requestDispatcher=mock(RequestDispatcher.class);
        this.service=mock(AdminService.class);
        adminLoginController=new AdminLoginController();
    }

    @Test
    public void testDoPostWithValidCredentials() throws Exception {

        String adminEmail = "admin@gmail.com";
        String adminPassword = "admin";
        Admin admin = new Admin();
        admin.setAdminFirstName("admin123");

        when(request.getParameter("adminEmail")).thenReturn(adminEmail);
        when(request.getParameter("password")).thenReturn(adminPassword);
        when(request.getSession()).thenReturn(httpSession);
        when(service.verifyLogin(adminEmail,adminPassword)).thenReturn(admin);
        Whitebox.setInternalState(adminLoginController, "service", service);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(requestDispatcher);

        adminLoginController.doPost(request, response);

        verify(httpSession).setAttribute("loggedUser","admin123");
        verify(httpSession).setAttribute("role", "ADMIN");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostWithInvalidCredentials() throws Exception {
        String adminEmail = "adminexample.com";
        String adminPassword = "password";
        when(request.getParameter(Mockito.anyString())).thenReturn(adminEmail);
        when(request.getParameter(Mockito.anyString())).thenReturn(adminPassword);

        when(service.verifyLogin(adminEmail, adminPassword)).thenReturn(null);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(requestDispatcher);
        Whitebox.setInternalState(adminLoginController, "service", service);
        adminLoginController.doPost(request, response);

        verify(request).setAttribute("message", "Incorrect username or password, please try again");
        verify(requestDispatcher).forward(request, response);
    }

}