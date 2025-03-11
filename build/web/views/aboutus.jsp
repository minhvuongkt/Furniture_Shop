<% String pageName = "aboutus";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>
<!-- Start Hero Section -->
<div class="hero">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-5">
                <div class="intro-excerpt">
                    <h1>Về chúng tôi</h1>
                    <p class="mb-4">Chúng tôi tập hợp những chuyên gia về lĩnh vực nội thất có bề dày kinh nghiệm!.</p>
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

<!-- Start Team Section -->
<div class="untree_co-section">
    <div class="container">

        <div class="row mb-5">
            <div class="col-lg-5 mx-auto text-center">
                <h2 class="section-title">Team chúng tôi</h2>
            </div>
        </div>

       <div class="row justify-content-center">

    <!-- Start Column 1 -->
    <div class="col-12 col-md-6 col-lg-3 mb-5 mb-md-0">
        <img src="./assets/images/giang.jpg" class="img-fluid mb-5">
        <h3><a href="#"><span class="">Hoàng Lê</span> Minh Vương</a></h3>
        <span class="d-block position mb-4">CEO, Founder, Atty.</span>
        <p>Làm BackEnd</p>
        <p class="mb-0"><a href="#" class="more dark"><span class="icon-arrow_forward"></span></a></p>
    </div> 
    <!-- End Column 1 -->

    <!-- Start Column 2 -->
    <div class="col-12 col-md-6 col-lg-3 mb-5 mb-md-0">
        <img src="./assets/images/giang.jpg" class="img-fluid mb-5">
        <h3><a href="#"><span class="">Nguyễn Hoàng</span> Anh</a></h3>
        <span class="d-block position mb-4">CEO, Founder, Atty.</span>
        <p>Thiết kế FE, Làm UI cho người dùng trải nghiệm</p>
        <p class="mb-0"><a href="#" class="more dark"><span class="icon-arrow_forward"></span></a></p>
    </div> 
    <!-- End Column 2 -->           

</div>

    </div>
</div>
<!-- End Team Section -->
<%@include file="./src/footer.jsp" %>