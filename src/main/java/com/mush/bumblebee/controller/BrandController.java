package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Brand;
import com.mush.bumblebee.service.BrandService;
import com.mush.bumblebee.service.BrandServiceImpl;
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

@WebServlet("/brand")
public class BrandController extends HttpServlet {
    private final BrandService service;

    public BrandController(){
        this.service= BrandServiceImpl.getBrandServiceInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("type");

        if(flag!=null && flag.equals("specific"))
        {
            viewSpecificBrand(request, response);
        }
        else
        {
            viewAllBrands(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag=request.getParameter("type");

        if(flag!=null && flag.equals("register"))
        {
            registerBrand(request, response);
        }
        else if(flag!=null && flag.equals("update"))
        {
            updateBrand(request, response);
        }
        else if(flag!=null && flag.equals("delete"))
        {
            deleteBrand(request, response);
        }
    }

    private void registerBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message="";

        String brandName = request.getParameter("brandName");
        Brand brand = new Brand(brandName);

        try {
            boolean result=service.registerBrand(brand);
            if (result)
            {
                message="The brand "+brandName+" has been added sucessfully";
                response.sendRedirect("/bumbleBee/brand");
            }
            else {
                message="Failed to add the brand "+brandName;
                request.setAttribute("message", message);
                RequestDispatcher rd=request.getRequestDispatcher("manageBrand.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
    }

    private void viewSpecificBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Brand> brandList=new ArrayList<Brand>();
        Brand brand = new Brand();

        String brandName = request.getParameter("brandName");

        try {
            brand = service.getSpecificBrand(brandName);
            if (brand==null)
            {
                message="There is no brand "+brandName+" ,search with correct names";
                request.setAttribute("message", message);
            }
            else{
                brandList.add(brand);
                request.setAttribute("CRUDTYPE", "EDIT");
                request.setAttribute("brandList", brandList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
        RequestDispatcher rd = request.getRequestDispatcher("manageBrand.jsp");
        rd.forward(request, response);
    }

    private void viewAllBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Brand> brandList;

        try {
            brandList = service.getAllBrands();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            brandList = new ArrayList<Brand>();
        }

        request.setAttribute("message", message);
        request.setAttribute("brandList", brandList);

        RequestDispatcher rd = request.getRequestDispatcher("manageBrand.jsp");
        rd.forward(request, response);
    }

    private void updateBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = "";

        long id= Long.parseLong(request.getParameter("id"));
        String brandName = request.getParameter("brandName");
        Brand brand = new Brand(id,brandName);

        try {
            boolean result = service.updateBrand(brand);
            if(result) {
                message = "Brand has been successfully updated! Brand name: " + brand.getBrandName();
                response.sendRedirect("/bumbleBee/brand");
            }
            else {
                message = "Failed to update the product! Brand name: " + brand.getBrandName();
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("manageBrand.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {

            message = e.getMessage();
        }
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String message = "";

        String brandName = request.getParameter("brandName");

        try {
            boolean result = service.deleteBrand(brandName);

            if(result) {
                message = "Brand has been successfully deleted! Brand name: " + brandName;
                response.sendRedirect("/bumbleBee/brand");
            }
            else {
                message = "Failed to delete the product! Brand name: " + brandName;
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("manageBrand.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }



    }

}
