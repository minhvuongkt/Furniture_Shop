package com.nhom13.controller.admin;

import com.nhom13.models.User;
import com.nhom13.service.CommonDataService;
import com.nhom13.utils.API;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class admin_customer_add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Thêm");
        request.setAttribute("description", "Tài Khoản Khách Hàng");
        var listProvinces = CommonDataService.listProvinces();
        request.setAttribute("listprovinces", listProvinces);
        getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Thêm");
        request.setAttribute("description", "Tài Khoản Khách Hàng");
        var listProvinces = CommonDataService.listProvinces();
        request.setAttribute("listprovinces", listProvinces);
        validData(request, response);
        //getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
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
            getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
        } else if (username.length() < 6) {
            request.setAttribute("alert", "Username không được dưới 6 ký tự!");
            getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
        } else if (!email.endsWith("@gmail.com")) {
            request.setAttribute("alert", "Email phải kết thúc là '@gmail.com' !");
            getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
        } else if (phone.length() < 10) {
            request.setAttribute("alert", "Số điện thoại không hợp lệ!");
            getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
        } else if (regexAddress.matches(address)) {
            request.setAttribute("alert", "Địa chỉ nhà không hợp lệ!");
            getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
        } else if (password.length() < 6) {
            request.setAttribute("alert", "Mật khẩu quá ngắn !");
            getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
        } else {
            var userInsert = new User(fullname, username, API.getSHA256Hash(password), email, phone, address, province, false, null);
            if (CommonDataService.registor(userInsert)) {
                request.setAttribute("success", "Thêm tài khoản khách hàng mới thành công!");
                response.setHeader("Refresh", "2; URL=../customer");
                getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Thêm tài khoản khách hàng không thành công! Email/Username đã tồn tại.");
                getServletContext().getRequestDispatcher("/views/admin/views/add/customer.jsp").forward(request, response);
            }
        }
    }

}
