package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Brand;
import com.mush.bumblebee.service.BrandService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.*;

@DisplayName("Test viewSpecificBrand method with non-existing brand")
class BrandControllerTest {
    private BrandController brandController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private BrandService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.requestDispatcher=mock(RequestDispatcher.class);
        this.service=mock(BrandService.class);
        brandController = new BrandController();
    }

    @Test
    public void testDoGetViewAllBrands() throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand("Brand 1"));
        brandList.add(new Brand("Brand 2"));
        when(service.getAllBrands()).thenReturn(brandList);
        when(request.getRequestDispatcher("manageBrand.jsp")).thenReturn(requestDispatcher);
        Whitebox.setInternalState(brandController, "service", service);

        brandController.doGet(request, response);

        verify(request).setAttribute("message", "");
        verify(request).setAttribute("brandList", brandList);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetViewSpecificBrand() throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Brand> brandList = new ArrayList<>();
        Brand brand = new Brand("Brand 1");
        brandList.add(brand);
        when(service.getSpecificBrand(anyString())).thenReturn(brand);
        when(request.getParameter("type")).thenReturn("specific");
        when(request.getParameter("brandName")).thenReturn("Brand 1");
        when(request.getRequestDispatcher("manageBrand.jsp")).thenReturn(requestDispatcher);
        Whitebox.setInternalState(brandController, "service", service);

        brandController.doGet(request, response);

        verify(request).setAttribute("CRUDTYPE", "EDIT");
        verify(request).setAttribute("brandList", brandList);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostRegisterBrandSuccess() throws ServletException, IOException, SQLException, ClassNotFoundException {
        when(request.getParameter("type")).thenReturn("register");
        when(request.getParameter("brandName")).thenReturn("Brand 1");
        when(response.encodeRedirectURL("/bumbleBee/brand")).thenReturn("/bumbleBee/brand");
        when(service.registerBrand(any(Brand.class))).thenReturn(true);
        Whitebox.setInternalState(brandController, "service", service);

        brandController.doPost(request, response);

        verify(response).sendRedirect("/bumbleBee/brand");
    }

    @Test
    public void testDoPostRegisterBrandFailure() throws ServletException, IOException, SQLException, ClassNotFoundException {
        when(request.getParameter("type")).thenReturn("register");
        when(request.getParameter("brandName")).thenReturn("Brand 1");
        when(request.getRequestDispatcher("manageBrand.jsp")).thenReturn(requestDispatcher);
        when(service.registerBrand(any(Brand.class))).thenReturn(false);
        Whitebox.setInternalState(brandController, "service", service);

        brandController.doPost(request, response);

        verify(request).setAttribute("message", "Failed to add the brand Brand 1");
        verify(requestDispatcher).forward(request, response);
    }
}