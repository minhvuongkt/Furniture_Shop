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
                                        <li class="breadcrumb-item"><a href="customer">${description}</a>
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
                                                    <form class="form-material" method="post">
                                                        <input type="hidden" name="id" value="${useredit.id}">
                                                        <div class="form-group form-info">
                                                            <input type="text" name="fullname" class="form-control fill" value="${useredit.name}" autofocus required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Họ Tên</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="username" class="form-control fill" value="${useredit.username}" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Tài Khoản</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="email" name="email" class="form-control fill" value="${useredit.email}" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Email</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="number" name="phone" class="form-control fill" value="${useredit.phone}" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Số Điện Thoại</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="address" class="form-control fill" value="${useredit.address}" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Địa Chỉ</label>
                                                        </div>
                                                            <input type="hidden" name="password" value="${useredit.password}">
                                                        <div class="form-group form-info">
                                                            <select name="province" class="form-control" required>
                                                                <option value="" disabled selected hidden>Chọn Tỉnh Thành</option>
                                                                <c:forEach items="${listprovinces}" var="item">
                                                                    <c:if test="${useredit.province == item.name}">
                                                                        <option value="${item.name}" selected>${item.name}</option>
                                                                    </c:if>
                                                                    <c:if test="${useredit.province != item.name}">
                                                                        <option value="${item.name}">${item.name}</option>
                                                                    </c:if>
                                                                </c:forEach>   
                                                            </select>
                                                        </div>
                                                        <div class="form-group text-right">
                                                            <button type="submit" class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                                                Sửa Tài Khoản
                                                            </button>
                                                            <a href="<c:url value='/admin/customer/'></c:url>" class="btn waves-effect waves-light btn-inverse btn-outline-inverse">
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
