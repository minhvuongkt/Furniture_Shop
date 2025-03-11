<% String pageName = "cart";%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Giỏ Hàng</title>
        <link rel="stylesheet" href="<c:url value='assets/css/style.css'/>">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
    </head>
    <body>
        <div class="hero">
            <div class="container">
                <div class="row justify-content-between">
                    <div class="col-lg-5">
                        <div class="intro-excerpt">
                            <h1>Giỏ Hàng</h1>
                        </div>
                    </div>
                    <div class="col-lg-7">

                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="orderConfirmationModal" tabindex="-1" aria-labelledby="orderConfirmationLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="orderConfirmationLabel">Xác Nhận Đặt Hàng</h5>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn chắc chắn muốn chuyển đến bước đặt hàng chứ?<br>
                        <strong>Lưu ý: thao tác này sẽ không hoàn tác!</strong>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="dismiss-modal" data-dismiss="modal">Huỷ</button>
                        <button type="button" class="btn btn-primary" id="confirmOrder">Đặt Hàng</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="untree_co-section before-footer-section">
            <div class="container">
                <div class="row mb-5">
                    <form action="cart" method="post">
                        <div class="site-blocks-table">
                            <c:if test="${not empty listProducts}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th class="product-thumbnail">Hình Sản Phẩm</th>
                                            <th class="product-name">Tên Sản Phẩm</th>
                                            <th class="product-price">Giá Cả</th>
                                            <th class="product-quantity">Số Lượng</th>
                                            <th class="product-total">Tổng Tiền</th>
                                            <th class="product-remove">Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody id="cart-items">
                                        <c:forEach items="${listProducts}" var="prd">
                                            <tr>
                                                <td class="product-thumbnail">
                                                    <c:forEach items="${listPhoto}" var="pt">
                                                        <c:if test="${prd.id == pt.id}">
                                                            <img src="<c:url value='/assets/images/${pt.photo}'/>" width="70px" height="70px" alt="No Photo" class="img-fluid">
                                                        </c:if>
                                                    </c:forEach>
                                                </td>

                                        <input type="hidden" name="productId" value="${prd.id}"> 
                                        <input type="hidden" name="quantity" value="${prd.quantity}"> 
                                        <td class="product-name">
                                            <h2 class="h5 text-black">${prd.name}</h2>
                                        </td>
                                        <td class="product-price">$<span id="product-price-value_${prd.id}" class="product-price-value_${prd.id}">${prd.price}</span></td>
                                        <td class="product-quantity">
                                            <div class="input-group mb-3 d-flex align-items-center quantity-container" style="max-width: 120px;">
                                                <div class="input-group-prepend">
                                                    <button class="btn btn-outline-black decrease" type="button" 
                                                            onclick="updateQuantity(${prd.id}, -1)">−</button>
                                                </div>
                                                <input type="text" class="form-control text-center quantity-amount" 
                                                       id="quantity_${prd.id}" name="quantity_${prd.id}" value="${prd.quantity}" min="1" readonly>
                                                <div class="input-group-append">
                                                    <button class="btn btn-outline-black increase" type="button"
                                                            onclick="updateQuantity(${prd.id}, 1)">+</button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="product-total">$<span id="total-price_${prd.id}" class="total-price_${prd.id}">${prd.quantity * prd.price}</span></td>
                                        <td class="product-remove">
                                            <button type="submit" class="btn btn-black btn-sm" name="action" value="delete" 
                                                    onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?')">
                                                X
                                            </button>                                                    
                                        </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br>
                                <div class="row">
                                    <div class="col-md">
                                        <button class="btn btn-danger btn-sm btn-block" type="submit" name="action" value="clear"
                                                onclick="return confirm('Bạn có chắc chắn muốn xóa tất cả sản phẩm?')">
                                            Xóa giỏ hàng
                                        </button>
                                    </div>
                                    <br>
                                </div>
                            </c:if>

                            <c:if test="${empty listProducts}">
                                <div class="container">
                                    <div class="row justify-content-between">
                                        <div class="col-lg-5">
                                            <div class="intro-excerpt">
                                                <h1>Bạn chưa thêm sản phẩm nào vào giỏ cả :(</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row mb-5">
                            <div class="col-md-6">
                                <a class="btn btn-outline-black btn-sm btn-block" href="shop">Tiếp Tục Mua Sắm</a>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty listProducts}">
                        <div class="col-md-6 pl-5">
                            <div class="row justify-content-end">
                                <div class="col-md-7">
                                    <div class="row">
                                        <div class="col-md-12 text-right border-bottom mb-5">
                                            <h3 class="text-black h4 text-uppercase">Tổng tiền trong giỏ hàng:</h3>
                                        </div>
                                    </div>
                                    <div class="row mb-5">
                                        <div class="col-md-6">
                                            <span class="text-black">Tổng:</span>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <strong class="text-black">$<span id="cart-total">0.00</span></strong>
                                        </div>
                                    </div>
                                    <c:if test="${empty userclient}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <a class="btn btn-black btn-lg py-3 btn-block" id="orderBtn" href="<c:url value='/login'/>">Đặt Hàng</a>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty userclient}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <a class="btn btn-black btn-lg py-3 btn-block" href="#" id="orderBtn"
                                                   data-bs-toggle="modal" data-bs-target="#orderConfirmationModal">Đặt hàng
                                                </a>
                                            </div>
                                        </div>
                                    </c:if>


                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>

            <script>
                var orderModal = document.getElementById('orderConfirmationModal');

                document.getElementById('orderBtn').addEventListener('click', function () {
                    <c:if test="${not empty userclient}">
                    var bootstrapModal = new bootstrap.Modal(orderModal);
                    bootstrapModal.show();
                    </c:if>
                });
                document.getElementById('confirmOrder').addEventListener('click', function () {
                    window.location.href = "<c:url value='/thankyou'/>";
                });

                function updateQuantity(productId, amount) {
                    const quantityInput = document.getElementById('quantity_' + productId);
                    let quantity = parseInt(quantityInput.value);
                    if (amount !== 0) {
                        quantity = Math.max(1, quantity + amount);
                        quantityInput.value = quantity;

                        $.ajax({
                            url: 'cart',
                            type: 'POST',
                            data: {
                                action: 'update',
                                productId: productId,
                                quantity: quantity
                            },
                            success: function (response) {
                                updateProductTotal(productId);
                                updateCartTotal();
                            },
                            error: function () {
                                alert('Error updating cart.');
                            }
                        });
                    } else {
                        updateProductTotal(productId);
                        updateCartTotal();
                    }
                }

                function updateProductTotal(productId) {
                    const quantityInput = document.getElementById('quantity_' + productId);
                    const priceSpan = document.getElementById('product-price-value_' + productId);
                    const totalPriceSpan = document.getElementById('total-price_' + productId);

                    let quantity = parseInt(quantityInput.value);
                    let price = parseFloat(priceSpan.textContent);
                    let total = quantity * price;

                    totalPriceSpan.textContent = total.toFixed(2);
                }
                function updateCartTotal() {
                    let cartTotal = 0;
                    const cartItems = document.getElementById('cart-items').getElementsByTagName('tr');
                    for (let i = 0; i < cartItems.length; i++) {
                        const totalPriceSpan = cartItems[i].querySelector('.total-price_' + cartItems[i].querySelector('input[name="productId"]').value);
                        let total = parseFloat(totalPriceSpan.textContent);
                        cartTotal += total;
                    }
                    document.getElementById('cart-total').textContent = cartTotal.toFixed(2);
                }
                window.onload = updateCartTotal;
            </script>
    </body>
</html>