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
                        
                        <!-- Page-header start -->
                        <div class="page-header">
                            <div class="page-block">
                                <div class="row align-items-center">
                                    <div class="col-md-8">
                                        <div class="page-header-title">
                                            <h5 class="m-b-10">Trang chủ</h5>
                                            <p class="m-b-0">Chào mừng đến trang quản trị nhóm 13</p>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="breadcrumb">
                                            <li class="breadcrumb-item">
                                                <a href="index.html"> <i class="fa fa-home"></i> </a>
                                            </li>
                                            <li class="breadcrumb-item"><a href="./">Trang chủ</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Page-header end -->
                    <!--<div class="pcoded-content">-->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <div class="page-body">
                                        <div class="row">
                                            <!-- SITE VISIT CHART start -->
                                            <div class="col-md-12 col-lg-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Khách Hàng</h5>
                                                        <!--<span>lorem ipsum dolor sit amet, consectetur adipisicing elit</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <div id="morris-site-visit"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- SITE VISIT CHART Ends -->
                                            <!-- Bar Chart start -->
                                            <div class="col-md-12 col-lg-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Đơn Hàng</h5>
                                                        <!--<span>lorem ipsum dolor sit amet, consectetur adipisicing elit</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <div id="morris-bar-chart"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Bar Chart Ends -->
                                            <!-- EXTRA AREA CHART start -->
                                            <div class="col-lg-12">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Lợi Nhuận Từng Khu Vực</h5>
                                                        <!--<span>lorem ipsum dolor sit amet, consectetur adipisicing elit</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <div id="morris-extra-area"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- EXTRA AREA CHART Ends -->
                                            <!-- Area Chart start -->
                                            <!--<div class="col-lg-12">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Area Chart</h5>
                                                        <span>lorem ipsum dolor sit amet, consectetur adipisicing elit</span>
                                                    </div>
                                                    <div class="card-block">
                                                        <div id="area-example"></div>
                                                    </div>
                                                </div>
                                            </div>-->
                                            <!-- Area Chart Ends -->
                                            <!-- LINE CHART start -->
                                            <!--<div class="col-md-12 col-lg-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Line Chart</h5>
                                                        <span>lorem ipsum dolor sit amet, consectetur adipisicing elit</span>
                                                    </div>
                                                    <div class="card-block">
                                                        <div id="line-example"></div>
                                                    </div>
                                                </div>
                                            </div>-->
                                            <!-- LINE CHART Ends -->
                                            <!-- Donut chart start -->
                                            <!--<div class="col-md-12 col-lg-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Donut Chart</h5>
                                                        <span>lorem ipsum dolor sit amet, consectetur adipisicing elit</span>
                                                    </div>
                                                    <div class="card-block">
                                                        <div id="donut-example"></div>
                                                    </div>
                                                </div>
                                            </div>-->
                                            <!-- Donut chart Ends -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="styleSelector">

                            </div>
                        </div>
                    </div>  
                </div>
                </div>
            </div>
        </div>
    <!--</div>-->
        <!-- Morris Chart js -->
    <script src="../assets_admin/js/raphael/raphael.min.js"></script>
    <script src="../assets_admin/js/morris.js/morris.js"></script>
    <script src="../assets_admin/pages/chart/morris/morris-custom-chart.js"></script>
    <!-- Warning Section Starts -->
    <%@include file="../src/footer.jsp" %>
    