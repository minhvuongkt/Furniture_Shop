/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.nhom13.controller.admin;

import com.nhom13.service.CommonDataService;
import com.nhom13.utils.API;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Miu
 */
public class admin_login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validUser(request, response);
    }

    void validUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var emailuser = request.getParameter("emailuser");
        var password = request.getParameter("password");

        if (emailuser == null || password == null) {
            request.setAttribute("alert", "Có lỗi xảy ra! Vui lòng thử lại sau.");
            getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
        } else if (emailuser.isEmpty() || password.isEmpty()) {
            request.setAttribute("alert", "Vui lòng không để trống dữ liệu!");
            getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
        } else if (emailuser.contains("@") && !emailuser.contains("@gmail.com")) {

            request.setAttribute("alert", "Định dạng email chỉ được là @gmail.com");
            getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
        } else {
            var user = CommonDataService.login(emailuser, API.getSHA256Hash(password));
            if (user != null) {
                if (user.getRole()) {
                    request.getSession().setAttribute("useradmin", user);
                    request.setAttribute("success", "Đăng nhập thành công! Chào Mừng " + user.getName() + "<br>Tự động chuyển hướng sau 2 giây...");
                    response.setHeader("Refresh", "2; URL=../admin/");
                    getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("alert", "Tài khoản của bạn không có quyền hạn vào trang web này!!!");
                    getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("alert", "Tài Khoản/Email hoặc Mật Khẩu không chính xác! Vui lòng kiểm tra lại!!");
                getServletContext().getRequestDispatcher("/views/admin/views/login.jsp").forward(request, response);
            }
        }
    }
}
