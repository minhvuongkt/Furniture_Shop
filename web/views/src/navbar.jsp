<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.nhom13.utils.API"%>
<nav class="custom-navbar navbar navbar-expand-md navbar-dark" aria-label="Furni navigation bar">
    <div class="container">
        <a class="navbar-brand" href="home">Project13<span>.</span></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarsFurni">
            <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                <li class="nav-item ${pageName eq 'home' ? 'active' : ''}">
                    <a class="nav-link" href="home"><strong>Trang chủ</strong></a>
                </li>
                <li class="nav-item ${pageName eq 'shop' ? 'active' : ''}">
                    <a class="nav-link" href="shop"><strong>Sản phẩm</strong></a>
                </li>
                <li class="nav-item ${pageName eq 'aboutus' ? 'active' : ''}">
                    <a class="nav-link" href="aboutus"><strong>Về chúng tôi</strong></a>
                </li>
                <li class="nav-item ${pageName eq 'service' ? 'active' : ''}">
                    <a class="nav-link" href="service"><strong>Dịch vụ</strong></a>
                </li>
                <li class="nav-item ${pageName eq 'contact' ? 'active' : ''}">
                    <a class="nav-link" href="contact"><strong>Liên Hệ</strong></a>
                </li>
            </ul>

            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">


                <li>
                    <a class="nav-link"  href="cart"><img src="${pageContext.request.contextPath}/assets/images/cart.svg" alt="Cart" width="24" height="24">
                        <c:if test="${not empty cart.items}">
                            ( ${cart.items.size()} SP )
                        </c:if>
                        <c:if test="${empty cart.items}">
                            ( 0 SP )
                        </c:if>
                    </a>
                </li>
                <c:if test="${empty userclient}">
                    <li><a class="nav-link" href="login"><img src="${pageContext.request.contextPath}/assets/images/login.png" alt="" width="24" height="24"></a></li>
                        </c:if>
                        <c:if test="${not empty userclient}">
                    <li><a class="nav-link" href="user"><img src="${pageContext.request.contextPath}/assets/images/user.svg" alt="" width="24" height="24">
                        Hi ${API.getName(userclient.name)}
                        </a></li>
                    <li><a class="nav-link" href="orders"><img src="${pageContext.request.contextPath}/assets/images/envelope-outline.svg" alt="" width="24" height="24"></a></li>
                    <li>
                        <a href="#logout" class="nav-link" onclick="window.location.href = `<c:url value='/views/logout.jsp'/>`">
                            <i class="ti-layout-sidebar-left"></i>
                        </a>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>
</nav>
