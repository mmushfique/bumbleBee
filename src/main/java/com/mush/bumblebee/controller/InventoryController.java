package com.mush.bumblebee.controller;

import com.mush.bumblebee.domain.Inventory;
import com.mush.bumblebee.domain.Product;
import com.mush.bumblebee.service.InventoryService;
import com.mush.bumblebee.service.InventoryServiceImpl;
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

@WebServlet("/inventory")
public class InventoryController extends HttpServlet {
    private final InventoryService inventoryService;
    private final ProductService productService;

    public InventoryController(){
        this.inventoryService= InventoryServiceImpl.getInventoryServiceInstance();
        this.productService= ProductServiceImpl.getProductServiceInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("type");


        if (flag!=null && flag.equals("fetchProduct")){
            fetchProduct(request,response);
        }
        else
        {
            viewAllInventorys(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag=request.getParameter("type");

        if(flag!=null && flag.equals("create"))
        {
            registerInventory(request, response);
        }
    }

    public void registerInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message="";

        String inventoryUniqueId = UUID.randomUUID().toString();
        long inventoryQuantity= Long.parseLong(request.getParameter("inventoryQuantity"));
        String inventoryForProductId=request.getParameter("inventoryForProductId");

        Inventory inventory = new Inventory(inventoryUniqueId,inventoryQuantity,inventoryForProductId);
        try {
            boolean result=inventoryService.registerInventory(inventory);
            if (result)
            {
                message="The inventory "+inventoryUniqueId+" has been added successfully";
                response.sendRedirect("/bumbleBee/inventory");
            }
            else {
                message="Failed to add the inventory "+inventoryUniqueId;
                request.setAttribute("message", message);
                RequestDispatcher rd=request.getRequestDispatcher("manageInventory.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
    }


    private void viewAllInventorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        List<Inventory> inventoryList;

        try {
            inventoryList = inventoryService.getAllInventorys();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            inventoryList = new ArrayList<Inventory>();
        }

        request.setAttribute("message", message);
        request.setAttribute("inventoryList", inventoryList);
        RequestDispatcher rd = request.getRequestDispatcher("manageInventory.jsp");
        rd.forward(request, response);
    }

    private void fetchProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = "";
        List<Product> productList;


        try {
            productList=productService.getAllProducts();

        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            productList = new ArrayList<Product>();
        }

        request.setAttribute("productList", productList);
        request.setAttribute("CRUDTYPE", "FETCHED_DATA");
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("manageInventory.jsp");
        rd.forward(request, response);
    }
}
