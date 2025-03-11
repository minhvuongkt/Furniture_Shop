package com.nhom13.controller.admin;

import com.nhom13.models.*;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class admin_category_add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Thêm");
        request.setAttribute("description", "Loại Hàng");

        getServletContext().getRequestDispatcher("/views/admin/views/add/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Thêm");
        request.setAttribute("description", "Loại Hàng");
        validData(request, response);
        //getServletContext().getRequestDispatcher("/views/admin/views/add/account.jsp").forward(request, response);
    }

    void validData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var categoryName = request.getParameter("name");
        if (categoryName.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập dữ liệu!");
            getServletContext().getRequestDispatcher("/views/admin/views/add/category.jsp").forward(request, response);
        } else {
            var categoryInsert = new Category(categoryName);
            if (CommonDataService.addCategory(categoryInsert)) {
                request.setAttribute("success", "Thêm loại hàng mới thành công!");
                response.setHeader("Refresh", "1; URL=../category");
                getServletContext().getRequestDispatcher("/views/admin/views/add/category.jsp").forward(request, response);
                //response.sendRedirect("../account");
            } else {
                request.setAttribute("alert", "Thêm loại hàng không thành công! Loại hàng này đã tồn tại.");
                getServletContext().getRequestDispatcher("/views/admin/views/add/category.jsp").forward(request, response);
            }
        }
    }
}
