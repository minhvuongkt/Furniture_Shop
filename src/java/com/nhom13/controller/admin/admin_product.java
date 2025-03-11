package com.nhom13.controller.admin;

import com.nhom13.models.Product;
import com.nhom13.models.SearchResult;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class admin_product extends HttpServlet {

    public class ProductSearchResult extends SearchResult<Product> {

        public ProductSearchResult(String searchValue, int pageSize, int page, int rowCount, List<Product> data) {
            super(searchValue, pageSize, page, rowCount, data);
        }
    }
    public final int Page_Size = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Mặt Hàng");

        int page = 1;
        var searchValue = request.getParameter("searchValue");
        var pageValue = request.getParameter("page");
        if (pageValue != null && pageValue != "") {
            page = Integer.parseInt(pageValue);
        }

        var data = CommonDataService.listProducts(page, Page_Size, searchValue != null ? searchValue : "");
        var rowCount = CommonDataService.rowCountProducts(searchValue != null ? searchValue : "");
        var searchResult = new admin_product.ProductSearchResult(searchValue != null ? searchValue : "", Page_Size, page, rowCount, data);
        request.setAttribute("ProductSearchResult", searchResult);
        if (delete(request)) {
            response.sendRedirect(request.getContextPath() + "/views/admin/views/product.jsp");
            return;
        }
        getServletContext().getRequestDispatcher("/views/admin/views/product.jsp").forward(request, response);
    }

    boolean delete(HttpServletRequest request) {
        var delete = request.getParameter("delete");
        if (delete != null && !delete.isEmpty()) {
            var prd = Integer.parseInt(request.getParameter("id"));
            var product = CommonDataService.getProduct(prd);
            if (product != null) {
                if (CommonDataService.deleteProduct(prd)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/admin/views/product.jsp").forward(request, response);
    }
}
