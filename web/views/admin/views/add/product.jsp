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
                                        <li class="breadcrumb-item"><a href="product">${description}</a>
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
                                                    <form class="form-material" method="post" enctype="multipart/form-data" >
                                                        <div class="form-group form-info">
                                                            <input type="text" name="nameproduct" class="form-control" autofocus required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Tên Mặt Hàng</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="detail" class="form-control" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Mô Tả Mặt Hàng</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="int" name="price" class="form-control" required>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Giá Mặt Hàng</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <select name="categoryid" class="form-control fill" required>
                                                                <option value="" disabled selected hidden>Chọn Loại Hàng</option>
                                                                <c:forEach items="${listCategories}" var="item">
                                                                    <option value="${item.id}">${item.name}</option>
                                                                </c:forEach>   
                                                            </select>
                                                            <span class="form-bar"></span>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <select name="status" class="form-control fill" required>
                                                                    <option value="true">Mặt Hàng Đang Bán</option>
                                                                    <option value="false">Mặt Hàng Ngừng Bán</option>
                                                            </select>
                                                            <span class="form-bar"></span>
                                                        </div>

                                                        <div class="form-group form-info">
                                                            <label class="control-label">Ảnh mặt hàng</label>
                                                            <div class="col-sm">                
                                                                <input type="hidden" name="Photo"/>
                                                                <input type="file" class="form-control" name="uploadPhoto" accept="image/*"
                                                                       onchange="document.getElementById('Photo').src = window.URL.createObjectURL(this.files[0])" />
                                                            </div>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <div class="col-lg-offset-2 col-sm-10"> 
                                                                <img id="Photo" src="<c:url value='/assets/images/nophoto.png'/>" class="img img-bordered" style="width:200px" alt="NoPhoto" />
                                                            </div>
                                                        </div>

                                                        <div class="form-group text-right">
                                                            <button type="submit" class="btn waves-effect waves-light btn-primary btn-outline-primary">
                                                                Thêm Mặt Hàng
                                                            </button>
                                                            <a href="<c:url value='/admin/product/'></c:url>" class="btn waves-effect waves-light btn-inverse btn-outline-inverse">
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
