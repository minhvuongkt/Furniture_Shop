
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<footer class="footer-section">
    <div class="container relative">

        <div class="sofa-img">
            <img src="${pageContext.request.contextPath}/assets/images/sofa.png" alt="Image" class="img-fluid">
        </div>

        <!--        <div class="row">
                    <div class="col-lg-8">
                        <div class="subscription-form">
                            <h3 class="d-flex align-items-center">
                                <span class="me-1">
                                    <img src="${pageContext.request.contextPath}/assets/images/envelope-outline.svg" alt="Image" class="img-fluid">
                                </span>
                                <span>Subscribe to Newsletter</span>
                            </h3>
        
                            <form action="#" class="row g-3">
                                <div class="col-auto">
                                    <input type="text" class="form-control" placeholder="Enter your name">
                                </div>
                                <div class="col-auto">
                                    <input type="email" class="form-control" placeholder="Enter your email">
                                </div>
                                <div class="col-auto">
                                    <button class="btn btn-primary">
                                        <span class="fa fa-paper-plane"></span>
                                    </button>
                                </div>
                            </form>
        
                        </div>
                    </div>
                </div>-->

        <div class="row g-5 mb-5">
            <div class="col-lg-4">
                <div class="mb-4 footer-logo-wrap">
                    <a href="#" class="footer-logo">Nhóm 13 -<span> Shop Bán Đồ Nội Thất</span></a>
                </div>
                <p class="mb-4">
                    Được thiết kế bởi nhóm 13 (Nguyễn Hoàng Anh - FE | Hoàng Lê Minh Vương - BE)
                </p>

                <ul class="list-unstyled custom-social">
                    <li><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ&pp=ygUXbmV2ZXIgZ29ubmEgZ2l2ZSB5b3UgdXA%3D"><span class="fa fa-brands fa-facebook-f"></span></a></li>
                    <li><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ&pp=ygUXbmV2ZXIgZ29ubmEgZ2l2ZSB5b3UgdXA%3D"><span class="fa fa-brands fa-twitter"></span></a></li>
                    <li><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ&pp=ygUXbmV2ZXIgZ29ubmEgZ2l2ZSB5b3UgdXA%3D"><span class="fa fa-brands fa-instagram"></span></a></li>
                    <li><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ&pp=ygUXbmV2ZXIgZ29ubmEgZ2l2ZSB5b3UgdXA%3D"><span class="fa fa-brands fa-linkedin"></span></a></li>
                </ul>
            </div>

            <div class="col-lg-8">
                <div class="row links-wrap">
                    <div class="col-6 col-sm-6 col-md-3">
                        <ul class="list-unstyled">
                            <li><a href="aboutus">Về chúng tôi</a></li>
                            <li><a href="service">Dịch vụ</a></li>
                        </ul>
                    </div>

                    <div class="col-6 col-sm-6 col-md-3">
                        <ul class="list-unstyled">
                            <li><a href="contact">Liên hệ</a></li>
                            <li>Số tổng đài: 19001909</li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <div class="border-top copyright">
            <div class="row pt-4">
                <div class="col-lg-6">
                    <p class="mb-2 text-center text-lg-start">
                        Copyright &copy;<%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>. All Rights Reserved. &mdash; Thiết Kế Bởi Nhóm 13.
                    </p>
                </div>

            </div>
        </div>

    </div>
</footer>
