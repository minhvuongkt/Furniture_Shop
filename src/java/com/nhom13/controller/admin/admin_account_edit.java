package com.nhom13.controller.admin;

import com.nhom13.dao.UserDAO;
import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import com.nhom13.utils.API;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class admin_account_edit extends HttpServlet {

    //getServletContext() lấy đường dẫn gốc tuyệt đối
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", "Cập Nhật");
        request.setAttribute("description", "Tài Khoản Admin");

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String id = pathInfo.substring(1);
            int userId = Integer.parseInt(id);
            var user = CommonDataService.getUser(userId);
            if (user != null) {
                request.setAttribute("useredit", user);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
        }

        getServletContext().getRequestDispatcher("/views/admin/views/edit/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Cập Nhật");
        request.setAttribute("description", "Tài Khoản Admin");

        validData(request, response);
        //getServletContext().getRequestDispatcher("/views/admin/views/add/account.jsp").forward(request, response);
    }

    void validData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id = Integer.parseInt(request.getParameter("id"));
        var fullname = request.getParameter("fullname");
        var username = request.getParameter("username");
        var email = request.getParameter("email");
        var password = request.getParameter("password");

        var user = CommonDataService.getUser(id);
        if (user != null) {
            request.setAttribute("useredit", user);
        }

        if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập đầy đủ dữ liệu!");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/account.jsp").forward(request, response);
        } else if (username.length() < 6) {
            request.setAttribute("alert", "Username không được dưới 6 ký tự!");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/account.jsp").forward(request, response);
        } else if (!email.endsWith("@gmail.com")) {
            request.setAttribute("alert", "Email phải kết thúc là '@gmail.com' !");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/account.jsp").forward(request, response);
        } else if (password.length() < 6) {
            request.setAttribute("alert", "Mật khẩu quá ngắn !");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/account.jsp").forward(request, response);
        } else {
            var userInsert = new User(id, fullname, username, API.getSHA256Hash(password), email, "0000", "ABC", "ABC", true, null);
            if (CommonDataService.updateUser(userInsert)) {
                request.setAttribute("success", "Cập Nhật tài khoản admin thành công!");
                response.setHeader("Refresh", "2; URL=../../account");
                getServletContext().getRequestDispatcher("/views/admin/views/add/account.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Cập Nhật tài khoản không thành công! Có lỗi xảy ra.");
                getServletContext().getRequestDispatcher("/views/admin/views/edit/account.jsp").forward(request, response);
            }
        }
    }

}
