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

public class edituser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var listProvinces = CommonDataService.listProvinces();
        request.setAttribute("listprovinces", listProvinces);
        getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validData(request, response);
    }
    void validData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var user = (User)request.getSession().getAttribute("userclient");
        
        var fullname = request.getParameter("fullname");
        var email = request.getParameter("email");

        var phone = request.getParameter("phone");
        var address = request.getParameter("address");
        var province = request.getParameter("province");
        
        
        String regexAddress = "^[\\d]+\\s+[A-Za-z\\s]+$";

        if (fullname.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || province.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập đầy đủ dữ liệu!");
            getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
        } else if (!email.endsWith("@gmail.com")) {
            request.setAttribute("alert", "Email phải kết thúc là '@gmail.com' !");
            getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
        } else if (phone.length() < 10) {
            request.setAttribute("alert", "Số điện thoại không hợp lệ!");
            getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
        } else if (regexAddress.matches(address)) {
            request.setAttribute("alert", "Địa chỉ nhà không hợp lệ!");
            getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
        }  else {
            var userEdit = new User(user.getId(), fullname, user.getUsername(), user.getPassword(), email, phone, address, province, user.getRole(), user.getCart());
            if (CommonDataService.updateUser(userEdit)) {
                request.setAttribute("success", "Cập nhật thông tin cá nhân thành công!!");
                request.getSession().setAttribute("userclient", userEdit);
                response.setHeader("Refresh", "2; URL=./user");
                getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Cập nhật thông tin không thành công. Có lỗi xảy ra!!");
                getServletContext().getRequestDispatcher("/views/edituser.jsp").forward(request, response);
            }
        }
    }
}
