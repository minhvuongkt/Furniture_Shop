package com.nhom13.controller;

import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import com.nhom13.utils.API;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class editpassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./views/editpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validData(request, response);
    }

    void validData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var user = (User) request.getSession().getAttribute("userclient");

        var oldPassword = request.getParameter("oldPassword");
        var newPassword = request.getParameter("newPassword");
        var confirmPassword = request.getParameter("confirmPassword");

        String regexAddress = "^[\\d]+\\s+[A-Za-z\\s]+$";
        if (user == null) {
            return;
        }
        if (newPassword == null || confirmPassword == null || oldPassword == null) {
            request.setAttribute("alert", "Vui lòng nhập đầy đủ dữ liệu!");
            getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
        } else if (newPassword.isEmpty() || confirmPassword.isEmpty() || oldPassword.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập đầy đủ dữ liệu !!");
            getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
        } else if (newPassword.length() < 6) {
            request.setAttribute("alert", "Mật khẩu mới quá ngắn !");
            getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
        } else {
            if (newPassword.equals(confirmPassword) == false) {
                request.setAttribute("alert", "Mật khẩu nhập lại không đúng!!");
                getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
            }
            else if (CommonDataService.checkOldPass(user.getId(), API.getSHA256Hash(oldPassword)) == false) {
                request.setAttribute("alert", "Mật khẩu cũ không đúng!!");
                getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
            }
            else if (CommonDataService.changePass(user.getId(), API.getSHA256Hash(newPassword))) {
                request.setAttribute("success", "Cập nhật mật khẩu mới thành công. Vui lòng đăng nhập lại!!");
                response.setHeader("Refresh", "2; URL=./views/logout.jsp");
                getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Cập nhật mật khẩu không thành công. Có lỗi xảy ra!!");
                getServletContext().getRequestDispatcher("/views/editpassword.jsp").forward(request, response);
            }
        }
    }
}
