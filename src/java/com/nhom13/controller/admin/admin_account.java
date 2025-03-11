package com.nhom13.controller.admin;

import com.nhom13.dao.UserDAO;
import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class admin_account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Tài Khoản Admin");
        request.setAttribute("listAd", CommonDataService.listAdmins());
        var delete = request.getParameter("delete");
        if (delete != null && !delete.isEmpty()) {
            var userId = Integer.parseInt(request.getParameter("id"));
            var user = CommonDataService.getUser(userId);
            if (user != null) {
                if (CommonDataService.isUsedUser(userId)) {
                    request.setAttribute("alert", "Không thể xoá người dùng này.");
                    getServletContext().getRequestDispatcher("/views/admin/views/account.jsp").forward(request, response);
                    return;
                }
                if (CommonDataService.deleteUser(userId)) {
                    response.sendRedirect(request.getContextPath() + "/views/admin/views/account.jsp");
                    return;
                }
            }
        }
        getServletContext().getRequestDispatcher("/views/admin/views/account.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/admin/views/account.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
