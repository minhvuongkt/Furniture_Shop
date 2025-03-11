package com.nhom13.controller;

import com.nhom13.models.*;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class cart extends HttpServlet {

    public class PhotoProduct {

        int id;
        String photo;

        public PhotoProduct(int id, String photo) {
            this.id = id;
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var carts = (Cart) request.getSession().getAttribute("cart");
        var listPhoto = new ArrayList<PhotoProduct>();
        if (carts != null) {
            request.setAttribute("listProducts", carts.items);
            for (var it : carts.items) {
                var photoPrd = CommonDataService.getPhotoProduct(it.getId());
                var photoo = new cart.PhotoProduct(it.getId(), photoPrd);
                listPhoto.add(photoo);
            }
            request.setAttribute("listPhoto", listPhoto);
        }
        getServletContext().getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var carts = (Cart) request.getSession().getAttribute("cart");
        var listPhoto = new ArrayList<PhotoProduct>();
        if (carts != null) {
            request.setAttribute("listProducts", carts.items);
            for (var it : carts.items) {
                var photoPrd = CommonDataService.getPhotoProduct(it.getId());
                var photoo = new cart.PhotoProduct(it.getId(), photoPrd);
                listPhoto.add(photoo);
            }
            request.setAttribute("listPhoto", listPhoto);
        }

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "clear":
                    clearCart(request, response);
                    break;
                default:

                    break;
            }
        }
        getServletContext().getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }

    void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));
        var cart = (Cart) request.getSession().getAttribute("cart");
        var user = (User) request.getSession().getAttribute("userclient");
        if (cart != null) {
            for (var item : cart.items) {
                if (item.getId() == productId) {
                    item.setQuantity(newQuantity);
                    break;
                }
            }
            request.getSession().setAttribute("cart", cart);
        }
        if (user != null) {
            user.setCart(cart);
            CommonDataService.updateUser(user);
        }
    }
    void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        var cart = (Cart) request.getSession().getAttribute("cart");
        var user = (User) request.getSession().getAttribute("userclient");

        if (cart != null) {
            cart.items.removeIf(item -> item.getId() == productId);
            request.getSession().setAttribute("cart", cart);
        }
        if (user != null) {
            user.setCart(cart);
            CommonDataService.updateUser(user);
        }
    }

    void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("cart");
        var user = (User) request.getSession().getAttribute("userclient");
        if (user != null) {
            user.setCart(new Cart());
            CommonDataService.updateUser(user);
        }
        response.setHeader("Refresh", "0; URL=./cart");
    }
}
