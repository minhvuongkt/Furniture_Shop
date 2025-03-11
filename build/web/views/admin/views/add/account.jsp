<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../src/header.jsp" %>
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <%@include file="../../src/navbar.jsp" %>

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <%@include file="../../src/menu.jsp" %>
                <%@include file="../../src/loader.jsp" %>
                <div class="pcoded-content">
                    <div class="page-header">
                        <div class="page-block">
                            <div class="row align-items-center">
                                <div class="col-md-8">
                                    <div class="page-header-title">
                                        <h5 class="m-b-10">${title}</h5>
                                        <p class="m-b-0">${description}</p>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <ul class="breadcrumb">
                                        <li class="breadcrumb-item">
                                            <a href="index"> <i class="fa fa-home"></i> </a>
                                        </li>
                                        <li class="breadcrumb-item"><a href="#">${title}</a>
                                        </li>
                                        <li class="breadcrumb-item"><a href="account">${description}</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty alert}">
                        <script> notifications(`${alert}`, "top", 'center', '', 'danger', '');</script>
                    </c:if>
                    <c:if test="${not empty success}">
                        <script> notifications(`${success}`, "top", 'center', '', 'success', '');</script>
                    </c:if>
                    <!-- Page-header end -->
                    <div class="pcoded-inner-content">
                        <!-- Main-body start -->
                        <div class="main-body">
                            <div class="page-wrapper">
                                <!-- Page-body start -->
                                <div class="page-body">
                                    <div class="row">
                                        <div class="col-md">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>${title} ${description}</h5>
                                                </div>
                                                <div class="card-block">
                                                    <form method="post" class="form-material">
                                                        <div class="form-group form-info">
                                                            <input type="text" name="fullname" class="form-control" autofocus required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nhập Họ Tên</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="username" class="form-control" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nhập Tài Khoản</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="email" name="email" class="form-control" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Email</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="password" class="form-control" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nhập Mật Khẩu</label>
                                                        </div>
                                                        <div class="form-group text-right">
                                                            <button type="submit" class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                                                Thêm Tài Khoản
                                                            </button>
                                                            <a href="<c:url value='/admin/account/'></c:url>" class="btn waves-effect waves-light btn-inverse btn-outline-inverse">
                                                                    Quay Về
                                                                </a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>  
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
        </div>
    </div>
<%@include file="../../src/footer.jsp" %>
