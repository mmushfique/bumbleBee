package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Brand;
import com.mush.bumblebee.domain.Category;
import com.mush.bumblebee.domain.Product;
import com.mush.bumblebee.service.BrandService;
import com.mush.bumblebee.service.BrandServiceImpl;
import com.mush.bumblebee.service.CategoryService;
import com.mush.bumblebee.service.CategoryServiceImpl;
import com.mush.bumblebee.service.ProductService;
import com.mush.bumblebee.service.ProductServiceImpl;
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

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductController(){
        this.productService=ProductServiceImpl.getProductServiceInstance();
        this.brandService= BrandServiceImpl.getBrandServiceInstance();
        this.categoryService= CategoryServiceImpl.getCategoryServiceInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("type");

        if(flag!=null && flag.equals("specific"))
        {
            viewSpecificProduct(request, response);
        }
        else if (flag!=null && flag.equals("fetchBrandAndCategory")){
            fetchBrandAndCategory(request,response);
        }
        else if (flag!=null && flag.equals("search")){
            searchProduct(request, response);
        }
        {
            viewAllProducts(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag=request.getParameter("type");

        if(flag!=null && flag.equals("create"))
        {
            registerProduct(request, response);
        }
        else if(flag!=null && flag.equals("update"))
        {
            updateProduct(request, response);
        }
        else if(flag!=null && flag.equals("delete"))
        {
            deleteProduct(request, response);
        }
    }

    public void registerProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message="";

        String productUniqueID = UUID.randomUUID().toString();
        String productName = request.getParameter("productName");
        Double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        String productDescription = request.getParameter("productDescription");
        String productBrand = request.getParameter("productBrand");
        String productCategory = request.getParameter("productCategory");


        Product product = new Product(productUniqueID,productName,productPrice,productDescription,productBrand,productCategory);
        try {
            boolean result=productService.registerProduct(product);
            if (result)
            {
                message="The product "+productName+" has been added successfully";
                response.sendRedirect("/bumbleBee/product");
            }
            else {
                message="Failed to add the product "+productName;
                request.setAttribute("message", message);
                RequestDispatcher rd=request.getRequestDispatcher("manageProduct.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
    }

    private void viewSpecificProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        Product product=new Product();
        List<Category> categoryList;
        List<Brand> brandList;

        String productUniqueId = request.getParameter("productUniqueId");

        try {
            product = productService.getSpecificProduct(productUniqueId);
            brandList=brandService.getAllBrands();
            categoryList = categoryService.getAllCategorys();
            if (product==null)
            {
                message="The product "+productUniqueId+" is not found";
                request.setAttribute("message", message);
            }
            else{
                request.setAttribute("CRUDTYPE", "EDIT");
                request.setAttribute("product", product);
                request.setAttribute("brandList", brandList);
                request.setAttribute("categoryList", categoryList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
            categoryList = new ArrayList<Category>();
            brandList=new ArrayList<>();
        }
        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        Product product=new Product();
        List<Category> categoryList;
        List<Brand> brandList;

        String productName = request.getParameter("productName");

        try {
            product = productService.searchProduct(productName);
            brandList=brandService.getAllBrands();
            categoryList = categoryService.getAllCategorys();
            if (product==null)
            {
                message="The product "+productName+" is not found";
                request.setAttribute("message", message);
            }
            else{
                request.setAttribute("CRUDTYPE", "EDIT");
                request.setAttribute("product", product);
                request.setAttribute("brandList", brandList);
                request.setAttribute("categoryList", categoryList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
            categoryList = new ArrayList<Category>();
            brandList=new ArrayList<>();
        }
        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.forward(request, response);
    }
    private void viewAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Product> productList;

        try {
            productList = productService.getAllProducts();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            productList = new ArrayList<Product>();
        }

        request.setAttribute("message", message);
        request.setAttribute("productList", productList);
        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = "";

        String productUniqueId = request.getParameter("productUniqueId");
        String productName = request.getParameter("productName");
        Double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        String productDescription = request.getParameter("productDescription");
        String productBrand = request.getParameter("productBrand");
        String productCategory = request.getParameter("productCategory");

        Product product = new Product(productUniqueId,productName,productPrice,productDescription,productBrand,productCategory);

        try {
            boolean result = productService.updateProduct(product);
            if(result) {
                message = "Product has been successfully updated! Product name: " + product.getProductName();
                response.sendRedirect("/bumbleBee/product");
            }
            else {
                message = "Failed to update the product! Product name: " + product.getProductName();
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {

            message = e.getMessage();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String message = "";

        String productUniqueId = request.getParameter("productUniqueId");

        try {
            boolean result = productService.deleteProduct(productUniqueId);

            if(result) {
                message = "Product has been successfully deleted! Product name: ";
                response.sendRedirect("/bumbleBee/product");
            }
            else {
                message = "Failed to delete the product! Product name: " ;
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }
    }

    private void fetchBrandAndCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = "";
        List<Category> categoryList;
        List<Brand> brandList;

        try {
            brandList=brandService.getAllBrands();
            categoryList = categoryService.getAllCategorys();

        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            categoryList = new ArrayList<Category>();
            brandList=new ArrayList<>();
        }

        request.setAttribute("brandList", brandList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("CRUDTYPE", "FETCHED_DATA");
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.forward(request, response);
    }
}
