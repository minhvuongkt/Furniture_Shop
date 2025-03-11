package com.nhom13.controller.admin;

import com.nhom13.models.Category;
import com.nhom13.models.SearchResult;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class admin_category extends HttpServlet {

    public class CategorySearchResult extends SearchResult<Category> {

        public CategorySearchResult(String searchValue, int pageSize, int page, int rowCount, List<Category> data) {
            super(searchValue, pageSize, page, rowCount, data);
        }
    }
    public final int Page_Size = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Loại Hàng");

        int page = 1;
        var searchValue = request.getParameter("searchValue");
        var pageValue = request.getParameter("page");
        if (pageValue != null && pageValue != "") {
            page = Integer.parseInt(pageValue);
        }

        var data = CommonDataService.listCategories(page, Page_Size, searchValue != null ? searchValue : "");
        var rowCount = CommonDataService.rowCountCategories(searchValue != null ? searchValue : "");
        var searchResult = new admin_category.CategorySearchResult(searchValue != null ? searchValue : "", Page_Size, page, rowCount, data);
        request.setAttribute("CategorySearchResult", searchResult);

        var delete = request.getParameter("delete");
        if (delete != null && !delete.isEmpty()) {
            var cateID = Integer.parseInt(request.getParameter("id"));
            var category = CommonDataService.getCategory(cateID);
            var categoryInUsed = CommonDataService.isUsedCategory(cateID);
            if (categoryInUsed) {
                request.setAttribute("alert", "Không thể xoá loại hàng này.<br>Loại hàng này đang được sử dụng.");
                getServletContext().getRequestDispatcher("/views/admin/views/category.jsp").forward(request, response);
                return;
            }
            if (category != null && !categoryInUsed) {
                if (CommonDataService.deleteCategory(cateID)) {
                    response.sendRedirect(request.getContextPath() + "/views/admin/views/category.jsp");
                    return;
                }
            }
        }
        getServletContext().getRequestDispatcher("/views/admin/views/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../views/admin/views/category.jsp").forward(request, response);
    }
}
