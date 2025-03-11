package com.nhom13.controller;

import com.nhom13.models.Cart;
import com.nhom13.service.CommonDataService;
import com.nhom13.utils.API;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validUser(request, response);
    }

    void validUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var emailuser = request.getParameter("emailuser");
        var password = request.getParameter("password");

        if (emailuser == null || password == null) {
            request.setAttribute("alert", "Có lỗi xảy ra! Vui lòng thử lại sau.");
            getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else if (emailuser.isEmpty() || password.isEmpty()) {
            request.setAttribute("alert", "Vui lòng không để trống dữ liệu!");
            getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else if (emailuser.contains("@") && !emailuser.contains("@gmail.com")) {

            request.setAttribute("alert", "Định dạng email chỉ được là @gmail.com");
            getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else {
            var user = CommonDataService.login(emailuser, API.getSHA256Hash(password));
            if (user != null) {
                var cart = (Cart) request.getSession().getAttribute("cart");
                if (cart != null) {
                    if (user.getCart().getItems().size() < 1) { //check nếu user chưa có sp nào trong cart thì set từ session
                        user.setCart(cart);
                    } else {
                        //súp pơ hạt :V
                        //nếu user có sản phẩm trong db: 2 th:
                        //1. id sản phẩm trùng thì cộng quantity của sp từ cart user và cart session
                        //2. id không trùng thì thêm mới
                        //làm 1 list để lấy sau đó remove khi đã + quantity / add mới!
                        //nên để vòng foreach của cart session ở ngoài vì đang kiểm tra là item của cart
                        //session có trùng với item của cart user trong db không
                        for (var item : cart.getItems()) {
                            boolean found = false;

                            for (var itemUser : user.getCart().getItems()) {
                                if (item.getId() == itemUser.getId()) {
                                    // Tăng quantity -> cập nhật lại SQL -> xóa sản phẩm khỏi session
                                    itemUser.setQuantity(itemUser.getQuantity() + item.getQuantity());
                                    found = true;
                                    break; // Dừng vòng lặp khi tìm thấy sản phẩm trùng
                                }
                            }
                            if (!found) {
                                // Nếu không tìm thấy sản phẩm trong giỏ hàng của user, thêm sản phẩm mới
                                user.getCart().getItems().add(item);
                            }
                            // Cập nhật người dùng sau mỗi thay đổi
                            CommonDataService.updateUser(user);
                        }
                        // Sau khi xử lý tất cả các items, xóa các items khỏi cart
                        cart.getItems().clear();
                        //Sau khi clear thì có thể set lại các items để hiển thị
                        cart.items.addAll(user.getCart().getItems());
                        request.getSession().setAttribute("cart", cart);
                    }
                } else if (cart == null && user.getCart().items.isEmpty() == false) {
                    request.getSession().setAttribute("cart", user.getCart());
                }

                request.getSession().setAttribute("userclient", user);
                request.setAttribute("success", "Đăng nhập thành công! Chào Mừng " + user.getName() + "<br>Tự động chuyển hướng sau 2 giây...");
                response.setHeader("Refresh", "2; URL=./home");
                getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "Tài Khoản/Email hoặc Mật Khẩu không chính xác! Vui lòng kiểm tra lại!!");
                getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
            }
        }
    }

}
