package com.nhom13.controller.admin;

import com.nhom13.models.Product;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class admin_product_add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Thêm");
        request.setAttribute("description", "Mặt Hàng");
        var listCategories = CommonDataService.listCategories();
        request.setAttribute("listCategories", listCategories);

        getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validData(request, response);
        //getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);

    }

    void validData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var nameproduct = request.getParameter("nameproduct");
        var detail = request.getParameter("detail");
        var price = request.getParameter("price");
        var status = request.getParameter("status");
        var categoryID = request.getParameter("categoryid");
        Part filePart = request.getPart("uploadPhoto"); // "uploadPhoto" là tên của input file trong form
        var photo = "";
        if (nameproduct.isEmpty() || detail.isEmpty() || price.isEmpty() || status.isEmpty() || categoryID.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập dữ liệu!");
            getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);
            return;
        }

        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("alert", "Ảnh mặt hàng không được để trống");
            getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);
            return;
        }
        if (filePart != null || filePart.getSize() > 0) {
            try {
                // Lấy file từ request
                 String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
                String uploadPath = getServletContext().getRealPath("") + "../../web/assets/images";
                String appPath = getServletContext().getRealPath("");
                String uploadPathBuild = appPath + "assets" + File.separator + "images";
                // Tạo thư mục upload nếu chưa có
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                File uploadDirBuild = new File(uploadPathBuild);
                if (!uploadDirBuild.exists()) {
                    uploadDirBuild.mkdir();
                }
                // Lưu file lên server
                filePart.write(uploadPath + File.separator + fileName);
                filePart.write(uploadPathBuild + File.separator + fileName);
                photo = fileName;

            } catch (Exception ex) {
                response.getWriter().println("File upload failed due to: " + ex.getMessage());
            }
        }
        if (photo.isEmpty()) {
            request.setAttribute("alert", "Không lấy được tên ảnh mặt hàng!!!");
            getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);
            return;
        }
        var ProductInsert = new Product(nameproduct, detail, Integer.parseInt(price), photo, Boolean.parseBoolean(status), Integer.parseInt(categoryID));
        if (CommonDataService.addProduct(ProductInsert)) {
            request.setAttribute("success", "Thêm mặt hàng mới thành công!");
            response.setHeader("Refresh", "1; URL=../product");
            getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);
        } else {
            request.setAttribute("alert", "Thêm mặt hàng mới không thành công! Có lỗi xảy ra.");
            getServletContext().getRequestDispatcher("/views/admin/views/add/product.jsp").forward(request, response);
        }
    }
}
