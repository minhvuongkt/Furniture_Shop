<%String pageName = "change-password";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>
<c:if test="${empty userclient}">
    <script> window.location.href = "<c:url value='/login'/>"</script>
</c:if>
<div class="untree_co-section">
    <div class="container">
        <c:if test="${not empty alert}">
            <script> notifications(`${alert}`, "top", 'center', '', 'danger', '');</script>
        </c:if>
        <c:if test="${not empty success}">
            <script> notifications(`${success}`, "top", 'center', '', 'success', '');</script>
        </c:if>
        <div class="row">
            <div class="col-md-12 mb-5 mb-md-0">
                <h2 class="h3 mb-3 text-black">Chỉnh Sửa Mật Khẩu</h2>
                <div class="p-3 p-lg-5 border bg-white">
                    <form method="post">
                        <div class="form-group">
                            <label for="oldPassword" class="text-black">Nhập Mật Khẩu Cũ <span class="text-danger">*</span></label>
                            <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="text-black">Mật Khẩu Mới <span class="text-danger">*</span></label>
                            <input type="password" class="form-control" id="newPassword" name="newPassword" required minlength="6">
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword" class="text-black">Xác Nhận Mật Khẩu <span class="text-danger">*</span></label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required minlength="6">
                        </div>
                        <br>
                        <div class="form-group">
                            <button type="submit" class="btn btn-black btn-lg py-3 btn-block">Xác Nhận</button>
                        </div>
                        <div id="successMessage" class="text-danger" style="display: none;">
                            Đổi mật khẩu thành công, tự động chuyển về trang người dùng sau 2 giây.
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="./src/footer.jsp" %>
