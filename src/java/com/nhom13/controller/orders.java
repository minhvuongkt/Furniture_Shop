package com.nhom13.controller;

import com.nhom13.models.Cart;
import com.nhom13.models.Order;
import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class orders extends HttpServlet {

    public class OrderClient {

        int id, total, status;
        Date time;

        public OrderClient(int id, int total, int status, Date time) {
            this.id = id;
            this.total = total;
            this.status = status;
            this.time = time;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public int getTotal() {
            return total;
        }

        public int getStatus() {
            return status;
        }

        public Date getTime() {
            return time;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var user = (User) request.getSession().getAttribute("userclient");
        if (user != null) {
            var listOrders = CommonDataService.listOrders();
            List<Order> myListOrders = new ArrayList<>();
            for (var order : listOrders) {
                if (order.getUserid() == user.getId()) {
                    myListOrders.add(order);
                }
            }
            if (myListOrders.size() > 0) {
                var listOrderClient = new ArrayList<OrderClient>();
                for (var order : myListOrders) {
                    listOrderClient.add(new OrderClient(order.getId(), order.getTotalprice(), order.getStatus(), order.getTimeorder()));
                }
                request.setAttribute("myListOrders", listOrderClient);
            }
        }

        getServletContext().getRequestDispatcher("/views/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/orders.jsp").forward(request, response);
    }
}
