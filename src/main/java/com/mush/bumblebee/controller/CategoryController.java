package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Category;
import com.mush.bumblebee.service.CategoryService;
import com.mush.bumblebee.service.CategoryServiceImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private final CategoryService service;

    public CategoryController(){
        this.service= CategoryServiceImpl.getCategoryServiceInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("type");

        if(flag!=null && flag.equals("specific"))
        {
            viewSpecificCategory(request, response);
        }
        else
        {
            viewAllCategorys(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag=request.getParameter("type");

        if(flag!=null && flag.equals("register"))
        {
            registerCategory(request, response);
        }
        else if(flag!=null && flag.equals("update"))
        {
            updateCategory(request, response);
        }
        else if(flag!=null && flag.equals("delete"))
        {
            deleteCategory(request, response);
        }
    }

    private void registerCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message="";

        String categoryName = request.getParameter("categoryName");
        Category category = new Category(categoryName);

        try {
            boolean result=service.registerCategory(category);
            if (result)
            {
                message="The category "+categoryName+" has been added sucessfully";
                response.sendRedirect("/bumbleBee/category");
            }
            else {
                message="Failed to add the category "+categoryName;
                request.setAttribute("message", message);
                RequestDispatcher rd=request.getRequestDispatcher("manageCategory.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
    }

    private void viewSpecificCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Category> categoryList=new ArrayList<Category>();
        Category category = new Category();

        String categoryName = request.getParameter("categoryName");

        try {
            category = service.getSpecificCategory(categoryName);
            if (category==null)
            {
                message="There is no category "+categoryName+" ,search with correct names";
                request.setAttribute("message", message);
            }
            else{
                categoryList.add(category);
                request.setAttribute("CRUDTYPE", "EDIT");
                request.setAttribute("categoryList", categoryList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
        RequestDispatcher rd = request.getRequestDispatcher("manageCategory.jsp");
        rd.forward(request, response);
    }

    private void viewAllCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Category> categoryList;

        try {
            categoryList = service.getAllCategorys();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            categoryList = new ArrayList<Category>();
        }

        request.setAttribute("message", message);
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher rd = request.getRequestDispatcher("manageCategory.jsp");
        rd.forward(request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = "";

        long id= Long.parseLong(request.getParameter("id"));
        String categoryName = request.getParameter("categoryName");
        Category category = new Category(id,categoryName);

        try {
            boolean result = service.updateCategory(category);
            if(result) {
                message = "Category has been successfully updated! Category name: " + category.getCategoryName();
                response.sendRedirect("/bumbleBee/category");
            }
            else {
                message = "Failed to update the product! Category name: " + category.getCategoryName();
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("manageCategory.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {

            message = e.getMessage();
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String message = "";

        String categoryName = request.getParameter("categoryName");

        try {
            boolean result = service.deleteCategory(categoryName);

            if(result) {
                message = "Category has been successfully deleted! Category name: " + categoryName;
                response.sendRedirect("/bumbleBee/category");
            }
            else {
                message = "Failed to delete the product! Category name: " + categoryName;
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("manageCategory.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }
    }
}
