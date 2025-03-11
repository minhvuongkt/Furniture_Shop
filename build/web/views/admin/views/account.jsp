<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../src/header.jsp" %>
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <%@include file="../src/navbar.jsp" %>

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <%@include file="../src/menu.jsp" %>
                <%@include file="../src/loader.jsp" %>
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

                    <!-- Page-header end -->
                    <div class="pcoded-inner-content">
                        <!-- Main-body start -->
                        <div class="main-body">
                            <c:if test="${not empty alert}">
                                <script> notifications(`${alert}`, "top", 'center', '', 'danger', '');</script>
                            </c:if>
                            <c:if test="${not empty success}">
                                <script> notifications(`${success}`, "top", 'center', '', 'success', '');</script>
                            </c:if>
                            <div class="page-wrapper">
                                <!-- Page-body start -->
                                <div class="page-body">
                                    <!-- Hover table card start -->
                                    <div class="form-group text-right">
                                        <a href="<c:url value='/admin/add/account'/>" class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                            <i class="icofont icofont-plus"></i>Thêm ${description}
                                        </a>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>${title} ${description}</h5>
                                        </div>
                                        <div class="card-block table-border-style">
                                            <div class="table-responsive">
                                                <table class="table table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Họ Tên</th>
                                                            <th>Tài Khoản</th>
                                                            <th>Email</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${listAd}" var="item">
                                                            <tr>
                                                                <td>${item.name}</td>
                                                                <td>${item.username}</td>
                                                                <td>${item.email}</td>
                                                                <td>
                                                                    <a href="<c:url value='/admin/edit/account/${item.id}'/>" style="padding: 5px"><i class="fa fa fa-wrench"></i></a>
                                                                    <a onclick="return deleteAlert(this)" href="<c:url value='/admin/account/${item.id}'/>" style="padding: 5px"><i class="fa fa-trash"></i></a>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Hover table card end -->
                                </div>  
                            </div>
                        </div>
                    </div>
                </div>  
            </div>
        </div>
    </div>
</div>
<script>
    function deleteAlert(anchor) {
        if (confirm("Bạn có chắc muốn xoá tài khoản này chứ?")) {
            window.location.href = anchor.href;
        } else {
            return false;
        }
    }
</script>
<%@include file="../src/footer.jsp" %>
