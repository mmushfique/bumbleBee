package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Category;
import com.mush.bumblebee.service.CategoryService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.*;

class CategoryControllerTest {
    private CategoryController categoryController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private CategoryService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.requestDispatcher=mock(RequestDispatcher.class);
        this.service=mock(CategoryService.class);
        categoryController = new CategoryController();
    }

    @Test
    public void testDoGetViewAllCategorys() throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Category 1"));
        categoryList.add(new Category("Category 2"));
        when(service.getAllCategorys()).thenReturn(categoryList);
        when(request.getRequestDispatcher("manageCategory.jsp")).thenReturn(requestDispatcher);
        Whitebox.setInternalState(categoryController, "service", service);

        categoryController.doGet(request, response);

        verify(request).setAttribute("message", "");
        verify(request).setAttribute("categoryList", categoryList);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetViewSpecificCategory() throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category("Category 1");
        categoryList.add(category);
        when(service.getSpecificCategory(anyString())).thenReturn(category);
        when(request.getParameter("type")).thenReturn("specific");
        when(request.getParameter("categoryName")).thenReturn("Category 1");
        when(request.getRequestDispatcher("manageCategory.jsp")).thenReturn(requestDispatcher);
        Whitebox.setInternalState(categoryController, "service", service);

        categoryController.doGet(request, response);

        verify(request).setAttribute("CRUDTYPE", "EDIT");
        verify(request).setAttribute("categoryList", categoryList);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostRegisterCategorySuccess() throws ServletException, IOException, SQLException, ClassNotFoundException {
        when(request.getParameter("type")).thenReturn("register");
        when(request.getParameter("categoryName")).thenReturn("Category 1");
        when(response.encodeRedirectURL("/bumbleBee/category")).thenReturn("/bumbleBee/category");
        when(service.registerCategory(any(Category.class))).thenReturn("created");
        Whitebox.setInternalState(categoryController, "service", service);

        categoryController.doPost(request, response);

        verify(response).sendRedirect("/bumbleBee/category");
    }

    @Test
    public void testDoPostRegisterCategoryFailure() throws ServletException, IOException, SQLException, ClassNotFoundException {
        when(request.getParameter("type")).thenReturn("register");
        when(request.getParameter("categoryName")).thenReturn("Category 1");
        when(request.getRequestDispatcher("manageCategory.jsp")).thenReturn(requestDispatcher);
        when(service.registerCategory(any(Category.class))).thenReturn("You already have a category:Category 1, try a different one");
        Whitebox.setInternalState(categoryController, "service", service);

        categoryController.doPost(request, response);

        verify(request).setAttribute("message", "You already have a category:Category 1, try a different one");
        verify(requestDispatcher).forward(request, response);
    }
}