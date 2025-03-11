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
                                        <li class="breadcrumb-item"><a href="product">${description}</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Page-header end -->
                    <div class="pcoded-inner-content">
                        <!-- Main-body start -->
                        <div class="page-body">

                            <div class="main-body">
                                <div class="page-wrapper">
                                    <form action="<c:url value='/admin/product/'/>" method="get">
                                        <div class="input-group">
                                            <input name="searchValue" type="text" class="form-control"
                                                   placeholder="Nhập tên loại hàng cần tìm" value="${ProductSearchResult.getSearchValue()}" autofocus>
                                            <div class="input-group-btn">
                                                <button class="btn waves-effect waves-light btn-primary btn-outline-primary" type="submit">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                                <a href="<c:url value='/admin/add/product'/>" class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                                    <i class="icofont icofont-plus"></i>Thêm ${description}
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                    <p>
                                        Có <strong>${ProductSearchResult.getRowCount()}</strong> mặt hàng trong tổng số <strong>${ProductSearchResult.getPageCount()}</strong> trang
                                    </p> 
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <!-- Hover table card start -->
                                        <div class="card">
                                            <div class="card-header">
                                                <h5>Thông tin mặt hàng</h5>
                                            </div>
                                            <div class="card-block table-border-style">
                                                <div class="table-responsive">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>Ảnh SP</th> 
                                                                <th>Tên Mặt Hàng</th>
                                                                <th>Mô Tả</th>
                                                                <th>Giá Mặt Hàng</th>
                                                                <th>Trạng Thái</th>                                                               
                                                                <th>Actions</th>                                                                
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${ProductSearchResult.getData()}" var="item">
                                                                <tr>
                                                                    <th><img src="<c:url value='/assets/images/${item.photo}'/>" width="70px" height="70px" alt="No Photo"/></th>
                                                                    <td>${item.name}</td>
                                                                    <td>${item.detail}</td>
                                                                    <td>${item.price}</td>
                                                                    <td>${item.isSelling ? "Đang Bán" : "Ngừng Bán"}  </td>
                                                                    <td>
                                                                        <a href="<c:url value='/admin/edit/product/${item.id}'/>" style="padding: 5px"><i class="fa fa fa-wrench"></i></a>
                                                                        <a onclick="return deleteAlert(this)" href="<c:url value='/admin/product/?delete=true&id=${item.id}'/>" style="padding: 5px"><i class="fa fa-trash"></i></a>
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
                                                <c:forEach begin="1" end="${ProductSearchResult.getPageCount()}" var="i"> 
                                                    <c:choose>
                                                        <c:when test="${i == ProductSearchResult.getPage()}">
                                                            <li class="btn waves-effect waves-light btn-primary btn-outline-primary active">
                                                                <a href="#">${i}</a>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                                                <a href="<c:url value='/admin/product/?page=${i}&searchValue=${ProductSearchResult.getSearchValue()}'/>">${i}</a>
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
            if (confirm("Bạn có chắc muốn xoá mặt hàng này chứ?")) {
                window.location.href = anchor.href;
            } else {
                return false;
            }
        }
    </script>
    <%@include file="../src/footer.jsp" %>
