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
                                        <li class="breadcrumb-item"><a href="./order">${description}</a>
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
                                            <div class="form-group text-right">

                                                <!-- Modal -->
                                                <div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="cancelModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <form id="cancelForm" method="post">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="cancelModalLabel">Hủy Đơn Hàng</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <input type="hidden" name="orderId" value="${order.id}" />
                                                                    <input type="hidden" name="status" value="2" />
                                                                    <input type="hidden" name="email" value="${order.infoorder.email}" />
                                                                    <div class="form-group">
                                                                        <label for="reason">Lý do hủy đơn sẽ được gửi về email khách hàng ${order.infoorder.email}:</label>
                                                                        <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                                    <button type="submit" class="btn btn-danger">Xác Nhận Hủy</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="dropdown-primary dropdown open">
                                                    <button class="btn btn-primary dropdown-toggle waves-effect waves-light" type="button" id="dropdown-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Hành Động</button>
                                                    <div class="dropdown-menu" aria-labelledby="dropdown-2" data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
                                                        <form id="approveForm" method="post" action="<c:url value='/admin/edit/order/${order.id}'></c:url>" style="display:none;">
                                                            <input type="hidden" name="orderId" value="${order.id}" />
                                                            <input type="hidden" name="status" value="1" />
                                                        </form>
                                                        <a class="dropdown-item waves-light waves-effect" href="#" onclick="document.getElementById('approveForm').submit();">Duyệt Đơn</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item waves-light waves-effect" href="" data-toggle="modal" data-target="#cancelModal">Hủy Đơn</a>
                                                    </div>
                                                </div>
                                                <a href="<c:url value='/admin/order/'></c:url>" class="btn waves-effect waves-light btn-inverse btn-outline-inverse">
                                                        Quay Về
                                                    </a>
                                                </div>
                                                <!-- Start Table Order -->
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Thông Tin ${description}</h5>
                                                    <div class="card-header-right">
                                                        <ul class="list-unstyled card-option">
                                                            <li><i class="fa fa-minus minimize-card"></i></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="card-block">
                                                    <form class="form-material">
                                                        <div class="form-group form-info">
                                                            <input type="text" name="id" class="form-control fill" value="${order.id}" readonly>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label" >Mã Đơn Hàng</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="id" class="form-control fill" value="${order.totalprice}$" readonly>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label" >Tổng Tiền Đơn Hàng</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <input type="text" name="timeorder" class="form-control fill" value="${order.timeorder}" readonly>
                                                            <span class="form-bar"></span>
                                                            <label class="float-label" >Thời Gian Đặt</label>
                                                        </div>
                                                        <div class="form-group form-info">
                                                            <span class="form-bar"></span>
                                                        </div>
                                                    </form>

                                                </div>
                                            </div>
                                            <!-- /End Table Order -->

                                            <!-- Start Table Product Items -->
                                            <div class="card"> 
                                                <div class="card-header">
                                                    <h5>Các Mặt Hàng Đã Đặt</h5>  
                                                    <div class="card-header-right">
                                                        <ul class="list-unstyled card-option">
                                                            <li><i class="fa fa-minus minimize-card"></i></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="card-block table-border-style">
                                                    <div class="table-responsive">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>Ảnh Mặt Hàng</th>
                                                                    <th>Tên Mặt Hàng</th>
                                                                    <th>Số Lượng</th>
                                                                    <th>Giá</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="items" items="${productitems}">
                                                                    <tr>
                                                                        <td class="product-thumbnail">
                                                                            <c:forEach items="${listPhoto}" var="pt">
                                                                                <c:if test="${items.id == pt.id}">
                                                                                    <img src="<c:url value='/assets/images/${pt.photo}'/>" width="70px" height="70px" alt="NoPhoto" class="img-fluid">
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </td>
                                                                        <td>${items.name}</td>
                                                                        <td>${items.quantity}</td>
                                                                        <td>${items.price}</td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /End Table Product Items -->

                                            <!-- Start Table Info Customer Order -->
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>Thông Tin Khách Hàng</h5>
                                                    <div class="card-header-right">
                                                        <ul class="list-unstyled card-option">
                                                            <li><i class="fa fa-minus minimize-card"></i></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="card-block table-border-style">
                                                    <div class="table-responsive">
                                                        <table class="table">
                                                            <thead>
                                                                <tr>
                                                                    <th>Họ Tên</th>
                                                                    <th>Email</th>
                                                                    <th>Số điện thoại</th>
                                                                    <th>Địa Chỉ</th>
                                                                    <th>Tỉnh/Thành</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>${order.infoorder.name}</td>
                                                                    <td>${order.infoorder.email}</td>
                                                                    <td>${order.infoorder.phone}</td>
                                                                    <td>${order.infoorder.address}</td>
                                                                    <td>${order.infoorder.province}</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /End Table Info Customer Order -->


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
