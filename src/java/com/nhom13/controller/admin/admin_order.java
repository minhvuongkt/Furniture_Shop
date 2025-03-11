/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.nhom13.controller.admin;

import com.nhom13.models.Order;
import com.nhom13.models.SearchResult;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class admin_order extends HttpServlet {

    public class OrderSearchResult extends SearchResult<Order> {

        public OrderSearchResult(String searchValue, int pageSize, int page, int rowCount, List<Order> data) {
            super(searchValue, pageSize, page, rowCount, data);
        }
    }
    public final int Page_Size = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Đơn Hàng");

        int page = 1;
        var searchValue = request.getParameter("searchValue");
        var pageValue = request.getParameter("page");
        if (pageValue != null && pageValue != "") {
            page = Integer.parseInt(pageValue);
        }

        var data = CommonDataService.listOrders(page, Page_Size, searchValue != null ? searchValue : "");
        var rowCount = CommonDataService.rowCountOrders(searchValue != null ? searchValue : "");
        var searchResult = new admin_order.OrderSearchResult(searchValue != null ? searchValue : "", Page_Size, page, rowCount, data);
        request.setAttribute("OrderSearchResult", searchResult);
        
        if (delete(request)) {
            response.sendRedirect(request.getContextPath() + "/views/admin/views/order.jsp");
            return;
        }

        getServletContext().getRequestDispatcher("/views/admin/views/order.jsp").forward(request, response);
    }

    boolean delete(HttpServletRequest request) {
        var delete = request.getParameter("delete");
        if (delete != null && !delete.isEmpty()) {
            var orderId = Integer.parseInt(request.getParameter("id"));
            var orderDel = CommonDataService.getOrder(orderId);
            if (orderDel != null) {
                if (CommonDataService.deleteOrder(orderId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/admin/views/order.jsp").forward(request, response);
    }
}
