package com.nhom13.controller.admin;

import com.nhom13.models.Category;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class admin_category_edit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Cập Nhật");
        request.setAttribute("description", "Loại Hàng");
        
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String id = pathInfo.substring(1);
            int categoryId = Integer.parseInt(id);
            var category = CommonDataService.getCategory(categoryId);
            if (category != null) {
                request.setAttribute("category", category);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
        }
        
        getServletContext().getRequestDispatcher("/views/admin/views/edit/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Cập Nhật");
        request.setAttribute("description", "Loại Hàng");
        validData(request, response);
        //getServletContext().getRequestDispatcher("/views/admin/views/add/account.jsp").forward(request, response);
    }

    void validData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var categoryId = request.getParameter("id");
        var id = 0;
        var categoryName = request.getParameter("name");
        if (categoryName.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập dữ liệu!");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/category.jsp").forward(request, response);
        } else if (categoryId == null || categoryId == "0") {
            request.setAttribute("alert", "Có lỗi xảy ra, vui lòng thử lại sau");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/category.jsp").forward(request, response);
        } else {
            id = Integer.parseInt(categoryId);
            var categoryInsert = new Category(id, categoryName);
            if (CommonDataService.updateCategory(categoryInsert)) {
                request.setAttribute("success", "Cập Nhật loại hàng thành công!");
                response.setHeader("Refresh", "2; URL=../../category");
                getServletContext().getRequestDispatcher("/views/admin/views/edit/category.jsp").forward(request, response);
                //response.sendRedirect("../account");
            } else {
                request.setAttribute("alert", "Có lỗi xảy ra, vui lòng thử lại sau");
                response.setHeader("Refresh", "2; URL=../../category");
                getServletContext().getRequestDispatcher("/views/admin/views/edit/category.jsp").forward(request, response);
            }
        }
    }
}
