package com.nhom13.controller;

import com.nhom13.models.Cart;
import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import com.nhom13.utils.API;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var listProvinces = CommonDataService.listProvinces();
        request.setAttribute("listprovinces", listProvinces);
        getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var listProvinces = CommonDataService.listProvinces();
        request.setAttribute("listprovinces", listProvinces);
        validData(request, response);
    }

    void validData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var fullname = request.getParameter("fullname");
        var username = request.getParameter("username");
        var email = request.getParameter("email");
        var password = request.getParameter("password");

        var phone = request.getParameter("phone");
        var address = request.getParameter("address");
        var province = request.getParameter("province");

        String regexAddress = "^[\\d]+\\s+[A-Za-z\\s]+$";

        if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty() || province.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập đầy đủ dữ liệu!");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else if (username.length() < 6) {
            request.setAttribute("alert", "Username không được dưới 6 ký tự!");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else if (!email.endsWith("@gmail.com")) {
            request.setAttribute("alert", "Email phải kết thúc là '@gmail.com' !");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else if (phone.length() < 10) {
            request.setAttribute("alert", "Số điện thoại không hợp lệ!");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else if (regexAddress.matches(address)) {
            request.setAttribute("alert", "Địa chỉ nhà không hợp lệ!");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else if (password.length() < 6) {
            request.setAttribute("alert", "Mật khẩu quá ngắn !");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else {
            var cart = (Cart) request.getSession().getAttribute("cart");
            var userInsert = new User(fullname, username, API.getSHA256Hash(password), email, phone, address, province, false, cart != null ? cart : new Cart());
            
            if (CommonDataService.registor(userInsert)) {

                request.setAttribute("success", "Đăng ký thành công! Chào mừng " + userInsert.getName() + " đến với shop nhóm 13!");

                request.getSession().setAttribute("userclient", userInsert);

                response.setHeader("Refresh", "2; URL=./home");
                getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Đăng ký tài khoản không thành công! Email/Username đã tồn tại.");
                getServletContext().getRequestDispatcher("/views/register.jsp").forward(request, response);
            }
        }
    }
}
