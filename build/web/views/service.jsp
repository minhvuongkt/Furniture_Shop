<% String pageName = "service"; %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>
<!-- Start Hero Section -->
			<div class="hero">
				<div class="container">
					<div class="row justify-content-between">
						<div class="col-lg-5">
							<div class="intro-excerpt">
								<h1>Dịch vụ</h1>
								<p class="mb-4">Xem các dịch vụ của chúng tôi!</p>
								<p><a href="home" class="btn btn-secondary me-2">Trang chủ</a></p>
							</div>
						</div>
						<div class="col-lg-7">
							<div class="hero-img-wrap">
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- End Hero Section -->

		

		<!-- Start Why Choose Us Section -->
		<div class="why-choose-section">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-6">
                <h2 class="section-title">Tại sao bạn nên chọn chúng tôi?</h2>
                <p>Trong thời gian trau dồi kinh nghiệm trên thị trường, chúng tôi cũng nắm được nhu cầu của khách hàng trên cả nước, nên khi người tiêu dùng lựa chọn Project13 là điểm đến chắc chắn các bạn sẽ được hưởng nhiều chính sách của chúng tôi bao gồm:</p>

                <div class="row my-5">
                    <div class="col-6 col-md-6">
                        <div class="feature">
                            <div class="icon">
                                <img src="./assets/images/truck.svg" alt="Image" class="imf-fluid">
                            </div>
                            <h3>Vận chuyển nhanh &amp; phí ship thấp</h3>
                            <p>Project13 đã liên kết với nhiều bên vận chuyển trên cả nước đảm bảo thời gian vận chuyển được tối ưu và phí ship luôn tối thiểu</p>
                        </div>
                    </div>

                    <div class="col-6 col-md-6">
                        <div class="feature">
                            <div class="icon">
                                <img src="./assets/images/bag.svg" alt="Image" class="imf-fluid">
                            </div>
                            <h3>Dễ dàng mua đồ </h3>
                            <p>Giao diện thân thiện, dễ dàng mua với chỉ 1 cú click chuột.</p>
                        </div>
                    </div>

                    <div class="col-6 col-md-6">
                        <div class="feature">
                            <div class="icon">
                                <img src="./assets/images/support.svg" alt="Image" class="imf-fluid">
                            </div>
                            <h3>Hỗ trợ 24/7</h3>
                            <p>Tổng đài chúng tôi luôn sẵn sàng hỗ trợ bạn 24/7 trừ các ngày nghỉ lễ.</p>
                        </div>
                    </div>

                    <div class="col-6 col-md-6">
                        <div class="feature">
                            <div class="icon">
                                <img src="./assets/images/return.svg" alt="Image" class="imf-fluid">
                            </div>
                            <h3>Hoàn tiền miễn phí</h3>
                            <p>Nếu bạn không hài lòng về sản phẩm, bạn có thể gọi vào tổng đài để được yêu cầu hoàn tiền.</p>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-lg-5">
                <div class="img-wrap">
                    <img src="./assets/images/why-choose-us-img.jpg" alt="Image" class="img-fluid">
                </div>
            </div>

        </div>
    </div>
</div>
		<!-- End Why Choose Us Section -->

		<!-- Start Product Section -->
		<div class="product-section">
    <div class="container">
        <div class="row">

            <!-- Start Column 1 -->
            <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
                <h2 class="mb-4 section-title">Luôn đặt chất lượng lên hàng đầu.</h2>
                <p class="mb-4">Với kinh nghiệm hơn 10 năm trong ngành, chúng tôi đảm bảo mọi sản phẩm của chúng tôi đều đạt chuẩn Âu, có độ bền cao và trên hết là mang lại cảm giác thoải mái nhất cho người mua. </p>
                <p><a href="shop" class="btn">Cửa hàng</a></p>
            </div> 
            <!-- End Column 1 -->

            <!-- Start Column 2 -->
            <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                <a class="product-item" href="shop">
                    <img src="./assets/images/product-1.png" class="img-fluid product-thumbnail">
                    <h3 class="product-title">Ghế cao</h3>

<!--                    <span class="icon-cross">
                        <img src="./assets/images/cross.svg" class="img-fluid">
                    </span>-->
                </a>
            </div> 
            <!-- End Column 2 -->

            <!-- Start Column 3 -->
            <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                <a class="product-item" href="shop">
                    <img src="./assets/images/product-2.png" class="img-fluid product-thumbnail">
                    <h3 class="product-title">Ghế làm việc</h3>

<!--                    <span class="icon-cross">
                        <img src="./assets/images/cross.svg" class="img-fluid">
                    </span>-->
                </a>
            </div>
            <!-- End Column 3 -->

            <!-- Start Column 4 -->
            <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                <a class="product-item" href="shop">
                    <img src="./assets/images/product-3.png" class="img-fluid product-thumbnail">
                    <h3 class="product-title">Ghế thương gia</h3>

<!--                    <span class="icon-cross">
                        <img src="./assets/images/cross.svg" class="img-fluid">
                    </span>-->
                </a>
            </div>
            <!-- End Column 4 -->

        </div>
    </div>
</div>
		<!-- End Product Section -->

		
<%@include file="./src/footer.jsp" %>