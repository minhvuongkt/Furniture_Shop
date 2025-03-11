package com.nhom13.controller.admin;

import com.nhom13.models.*;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class admin_customer extends HttpServlet {

    public class UserSearchResult extends SearchResult<User> {

        public UserSearchResult(String searchValue, int pageSize, int page, int rowCount, List<User> data) {
            super(searchValue, pageSize, page, rowCount, data);
        }
    }
    public final int Page_Size = 20;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Khách Hàng");
        int page = 1;
        var searchValue = request.getParameter("searchValue");
        var pageValue = request.getParameter("page");
        if (pageValue != null && pageValue != "") {
            page = Integer.parseInt(pageValue);
        }

        var data = CommonDataService.listCustomers(page, Page_Size, searchValue != null ? searchValue : "");
        var rowCount = CommonDataService.rowCountUsers(searchValue != null ? searchValue : "");

        var searchResult = new UserSearchResult(searchValue != null ? searchValue : "", Page_Size, page, rowCount, data);
        request.setAttribute("CustomerSearchResult", searchResult);
        
        var delete = request.getParameter("delete");
        if (delete != null && !delete.isEmpty()) {
            var userId = Integer.parseInt(request.getParameter("id"));
            var user = CommonDataService.getUser(userId);
            if (user != null) {
                if (CommonDataService.isUsedUser(userId)) {
                    request.setAttribute("alert", "Không thể xoá người dùng này.");
                    getServletContext().getRequestDispatcher("/views/admin/views/category.jsp").forward(request, response);
                    return;
                }
                if (CommonDataService.deleteUser(userId)) {
                    response.sendRedirect(request.getContextPath() + "/views/admin/views/account.jsp");
                    return;
                }
            }
        }
        getServletContext().getRequestDispatcher("/views/admin/views/customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../views/admin/views/customer.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
