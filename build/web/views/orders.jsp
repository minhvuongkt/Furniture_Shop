<% String pageName = "orders";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>
<c:if test="${empty userclient}">
    <script> window.location.href = "<c:url value='/login'/>"</script>
</c:if>
<!-- Start Hero Section -->
<div class="hero">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-5">
                <div class="intro-excerpt">
                    <h1>Quản lý lịch sử đơn hàng</h1>
                    <h2>Kiểm tra lịch sử mua hàng của bạn</h2>
                </div>
            </div>
            <div class="col-lg-7">
            </div>
        </div>
    </div>
</div>
<!-- End Hero Section -->

<div class="untree_co-section before-footer-section">
    <div class="container">
        <div class="row mb-5">
            <div class="col-md-12">
                <div class="site-blocks-table">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Mã đơn hàng</th>
                                <th>Tổng giá</th>
                                <th>Trạng thái</th>
                                <th>Thời gian</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty myListOrders}">
                                <tr>
                                    <td colspan="6" style="text-align: center; font-style: italic;">
                                        Bạn không có đơn hàng nào
                                    </td></tr>
                                </c:if>
                                <c:if test="${not empty myListOrders}">
                                    <c:forEach items="${myListOrders}" var="item">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.total}</td>
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
                                        <td>${item.time}</td>
                                    </c:forEach>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="./src/footer.jsp" %>
