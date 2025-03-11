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
                                        <li class="breadcrumb-item"><a href="./order">${description}</a>
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
                            <div class="page-wrapper">
                                <!-- Page-body start -->
                                <div class="page-body">
                                    <form action="<c:url value='/admin/order/'/>" method="get">
                                        <div class="input-group">
                                            <input name="searchValue" type="text" class="form-control"
                                                   placeholder="Nhập mã đơn hàng cần tìm" value="${OrderSearchResult.getSearchValue()}" autofocus>
                                            <div class="input-group-btn">
                                                <button class="btn waves-effect waves-light btn-primary btn-outline-primary" type="submit">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                    <p>
                                        Có <strong>${OrderSearchResult.getRowCount()}</strong> đơn hàng trong tổng số <strong>${OrderSearchResult.getPageCount()}</strong> trang
                                    </p> 
                                    <!-- Hover table card start -->
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Thông tin đơn hàng</h5>
                                        </div>
                                        <div class="card-block table-border-style">
                                            <div class="table-responsive">
                                                <table class="table table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Mã Đơn Hàng</th>
                                                            <th>Tổng Tiền</th>
                                                            <th>Thời Gian</th>
                                                            <th>Trạng Thái</th>
                                                            <th>Actions</th>                                                                
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${OrderSearchResult.getData()}" var="item">
                                                            <tr>
                                                                <td>${item.id}</td>
                                                                <td>${item.totalprice}$</td>
                                                                <td>${item.timeorder}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${item.status == 0}">
                                                                            Chờ duyệt
                                                                        </c:when>
                                                                        <c:when test="${item.status == 1}">
                                                                            Đã duyệt
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Từ chối
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <td>
                                                                    <c:if test="${item.status == 0}"> 
                                                                        <a href="<c:url value='/admin/edit/order/${item.id}'/>" style="padding: 5px"><i class="fa fa fa-wrench"></i></a>
                                                                    </c:if>
                                                                    
                                                                    <a onclick="return deleteAlert(this)" href="<c:url value='/admin/order/?delete=true&id=${item.id}'/>" style="padding: 5px"><i class="fa fa-trash"></i></a>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <ul class="pagination d-flex justify-content-center">
                                            <c:forEach begin="1" end="${OrderSearchResult.getPageCount()}" var="i"> 
                                                <c:choose>
                                                    <c:when test="${i == OrderSearchResult.getPage()}">
                                                        <li class="btn waves-effect waves-light btn-primary btn-outline-primary active">
                                                            <a href="#">${i}</a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                                            <a href="<c:url value='/admin/order/?page=${i}&searchValue=${OrderSearchResult.getSearchValue()}'/>">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
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
        if (confirm("Bạn có chắc muốn xoá đơn hàng này chứ?")) {
            window.location.href = anchor.href;
        } else {
            return false;
        }
    }
</script>
<%@include file="../src/footer.jsp" %>
