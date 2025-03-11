<%String pageName = "contact";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>
<div class="hero">
    <c:if test="${empty userclient}">
    <script> window.location.href = "<c:url value='/login'/>"</script>
    </c:if>
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-5">
                <div class="intro-excerpt">
                    <h1>Quản lý thông tin người dùng</h1>
                    <p class="mb-4"></p>
                    <p><a href="home" class="btn btn-secondary me-2">Trang chủ</a></p>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="hero-img-wrap">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="untree_co-section">
    <div class="container">
        <!-- Phần thông tin người dùng -->
        <div class="row">
            <div class="col-md-12">
                <div class="border p-4 rounded bg-white">
                    <!-- Thông tin cá nhân -->
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h2 class="h3 mb-0 text-black">Thông Tin Cá Nhân</h2>
                        <a class="btn btn-primary" href="edituser">Sửa</a>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="fullname" class="text-black">Họ Tên</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" value="${userclient.name}" readonly>
                        </div>
                        <div class="col-md-6">
                            <label for="username" class="text-black">Tài Khoản</label>
                            <input type="text" class="form-control" id="username" name="username" value="${userclient.username}" readonly>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="email" class="text-black">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="${userclient.email}" readonly>
                        </div>
                        <div class="col-md-6">
                            <label for="phone" class="text-black">Số Điện Thoại</label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${userclient.phone}" readonly>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="province" class="text-black">Tỉnh Thành</label>
                            <input type="text" class="form-control" id="province" name="province" value="${userclient.province}" readonly>
                        </div>
                        <div class="col-md-6">
                            <label for="address" class="text-black">Địa Chỉ</label>
                            <input type="text" class="form-control" id="address" name="address" value="${userclient.address}" readonly>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Phần mật khẩu -->
        <div class="row mt-4">
            <div class="col-md-12">
                <div class="border p-4 rounded bg-white">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h2 class="h3 mb-0 text-black">Mật Khẩu</h2>
                        <a class="btn btn-primary" href="editpassword">Sửa</a>
                    </div>
                    <div class="form-group mb-3">
                        <label for="password" class="text-black">Mật Khẩu</label>
                        <input type="password" class="form-control" id="password" name="password" value="********" readonly>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="./src/footer.jsp" %>