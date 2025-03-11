package com.nhom13.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class contact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getInfoContact(request);
        getServletContext().getRequestDispatcher("/views/contact.jsp").forward(request, response);
    }

    void getInfoContact(HttpServletRequest request) {
        var fname = request.getParameter("fname");
        var lname = request.getParameter("lname");
        var email = request.getParameter("email");
        var message = request.getParameter("message");
        if (fname == null || lname == null || email == null || message == null) {
            request.setAttribute("alert", "Vui lòng không để trống thông tin!!!");
        } else if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || message.isEmpty()) {
            request.setAttribute("alert", "Vui lòng không để trống thông tin!!!");
        } else if (!email.contains("@gmail.com")) {
            request.setAttribute("alert", "Chỉ chấp nhận email đuôi là @gmail.com!!!");
        } else {
            request.setAttribute("success", "Đã gửi về cho tổng đài, hãy thường xuyên kiểm tra hòm thư.");
        }
    }
}
