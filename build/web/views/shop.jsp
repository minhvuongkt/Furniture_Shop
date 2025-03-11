<% String pageName = "shop";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>

<div class="hero">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-5">
                <div class="intro-excerpt">
                    <h1>Sản phẩm <span class="d-block"></span></h1>
                    <p class="mb-4">Tại đây bạn sẽ tìm được một số sản phẩm phù hợp với mình!</p>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="hero-img-wrap"></div>
            </div>
        </div>
    </div>
</div>

<!-- Start Shop Product Section -->
<div class="product-section">
    <div class="container">
        <c:forEach items="${categories}" var="category">
            <div class="row">
                <!-- Column 1 -->
                <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
                    <h2 class="mb-4 section-title" style="display: flex; align-items: center;">
                        <span style="flex-grow: 1; height: 2px; background-color: #3b5d50;"></span>
                        <span style="padding: 0 10px;">${category.name}</span>
                        <span style="flex-grow: 1; height: 2px; background-color: #3b5d50;"></span>
                    </h2>
                </div>
                <!-- Columns with products -->
                <!-- Column 2 -->
                <c:forEach items="${products}" var="product">
                    <c:if test="${category.id == product.categoryID && product.isSelling}">
                        <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                            <div class="product-item" onclick="addToCart(${product.id}, '${product.name}', 1, ${product.price})">
                                <img src="./assets/images/${product.photo}" class="img-fluid product-thumbnail">
                                <h3 class="product-title">${product.name}</h3>
                                <strong class="product-price">$${product.price}</strong>
                                <span class="icon-cross">
                                    <img src="./assets/images/cross.svg" class="img-fluid">
                                </span>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <!-- Repeat similar columns for other products -->
            </div>
            <br><br>
        </c:forEach>
    </div>
</div>

<!-- End Shop Product Section -->

<%@include file="./src/footer.jsp" %>

<script>
    function addToCart(productId, productName, productQuantity, productPrice) {
        $.ajax({
            url: './shop',
            type: 'POST',
            data: {
                productId: productId,
                productName: productName,
                productPrice: productPrice,
                productQuantity: productQuantity
            },
            success: function ()   
            {
                notifications(`Sản phẩm đã được thêm vào giỏ hàng!`, "top", 'center', '', 'success', '');
                setTimeout(() => {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                alert('Đã xảy ra lỗi khi gửi yêu cầu đến máy chủ.');
            }
        });
    }
</script>

