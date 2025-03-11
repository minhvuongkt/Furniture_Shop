package com.nhom13.controller;

import com.nhom13.models.Cart;
import com.nhom13.models.InfoOrder;
import com.nhom13.models.Order;
import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

public class thankyou extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var cart = (Cart) request.getSession().getAttribute("cart");
        var user = (User) request.getSession().getAttribute("userclient");
        
        if (cart != null && user != null) {
            var infoOrder = new InfoOrder(user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getProvince());
            var item = new Order(cart, infoOrder, user.getId(), calculateTotalPrice(cart), 0);
            CommonDataService.addOrder(item);
            request.getSession().removeAttribute("cart");
            user.setCart(new Cart());
            CommonDataService.updateUser(user);
        }
        getServletContext().getRequestDispatcher("/views/thankyou.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/thankyou.jsp").forward(request, response);
    }

    protected int calculateTotalPrice(Cart cart) {
        int totalPrice = 0;
        if (cart != null) {
            for (Cart.CartItem item : cart.getItems()) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
        }
        return totalPrice;
    }
}
