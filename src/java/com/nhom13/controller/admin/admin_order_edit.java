package com.nhom13.controller.admin;

import com.nhom13.controller.cart;
import com.nhom13.service.CommonDataService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class admin_order_edit extends HttpServlet {

    public class PhotoProduct {

        int id;
        String photo;

        public PhotoProduct(int id, String photo) {
            this.id = id;
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Đơn Hàng");

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String id = pathInfo.substring(1);
            int orderId = Integer.parseInt(id);
            var order = CommonDataService.getOrder(orderId);
            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("productitems", order.getProductitems().items);
                var listPhoto = new ArrayList<PhotoProduct>();
                request.setAttribute("listProducts", order.getProductitems().items);
                for (var it : order.getProductitems().items) {
                    var photoPrd = CommonDataService.getPhotoProduct(it.getId());
                    var photoo = new PhotoProduct(it.getId(), photoPrd);
                    listPhoto.add(photoo);
                }
                request.setAttribute("listPhoto", listPhoto);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
        }

        getServletContext().getRequestDispatcher("/views/admin/views/edit/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Quản Lý");
        request.setAttribute("description", "Đơn Hàng");

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String id = pathInfo.substring(1);
            int orderId = Integer.parseInt(id);
            var order = CommonDataService.getOrder(orderId);
            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("productitems", order.getProductitems().items);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
        }
        handleOrder(request, response);
        //getServletContext().getRequestDispatcher("/views/admin/views/edit/order.jsp").forward(request, response);
    }

    void handleOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var orderId = request.getParameter("orderId");
        var status = request.getParameter("status");
        if (orderId == null || status == null) {
            request.setAttribute("alert", "Có lỗi xảy ra! Vui lòng thử lại sau.");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/order.jsp").forward(request, response);
        } else if (orderId.isEmpty() || status.isEmpty()) {
            request.setAttribute("alert", "Có lỗi xảy ra! Vui lòng thử lại sau.");
            getServletContext().getRequestDispatcher("/views/admin/views/edit/order.jsp").forward(request, response);
        } else {
            var id = Integer.parseInt(orderId);
            var sts = Integer.parseInt(status);

            if (sts == 2) {
                var reason = request.getParameter("reason");
                var email = request.getParameter("email");
                //Gửi mail về email khách hàng lý do từ chối đơn hàng và cập nhật...
                //Không hỗ trợ tính lăng lày :3
            }

            if (CommonDataService.updateOrderStatus(sts, id)) {
                request.setAttribute("success", "Cập nhật trạng thái đơn hàng thành công!");
                response.setHeader("Refresh", "1; URL=../../order");
                getServletContext().getRequestDispatcher("/views/admin/views/edit/order.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Có lỗi xảy ra! Vui lòng thử lại sau.");
                getServletContext().getRequestDispatcher("/views/admin/views/edit/order.jsp").forward(request, response);
            }
        }
    }
}
