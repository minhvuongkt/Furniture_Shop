package com.nhom13.controller;

import com.nhom13.models.*;
import com.nhom13.service.CommonDataService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class shop extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var listCategories = CommonDataService.listCategories();
        request.setAttribute("categories", listCategories);
        var listProducts = CommonDataService.listProducts();
        request.setAttribute("products", listProducts);
        getServletContext().getRequestDispatcher("/views/shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (getProduct(request, response)) {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true}");
                getServletContext().getRequestDispatcher("/views/shop.jsp").forward(request, response);
            }
            //getServletContext().getRequestDispatcher("/views/shop.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"error\": \"Invalid product data\"}");
        }
    }

    boolean getProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));

        var productItem = new Cart.CartItem(productId, productName, productQuantity, productPrice);

        var user = (User) request.getSession().getAttribute("userclient");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        boolean productExists = false;
        for (var item : cart.items) {
            if (item.getId() == productId) {
                item.setQuantity(item.getQuantity() + productQuantity);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            cart.items.add(productItem);
        }
        request.getSession().setAttribute("cart", cart);
        if (user != null) {
            user.setCart(cart);
            CommonDataService.updateUser(user);
        }
        return true;
    }
}
