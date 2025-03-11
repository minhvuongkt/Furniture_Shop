<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Trang Quản Trị - Đăng Nhập</title>
        <!-- Meta -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Favicon icon -->

        <link rel="icon" href="<c:url value='/assets_admin/images/favicon.ico'/>" type="image/x-icon">
        <!-- Google font-->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
        <!-- Required Fremwork -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/bootstrap/css/bootstrap.min.css'/>">
        <!-- waves.css -->
        <link rel="stylesheet" href="<c:url value='/assets_admin/pages/waves/css/waves.min.css'/>" type="text/css" media="all">
        <!-- themify-icons line icon -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/icon/themify-icons/themify-icons.css'/>">
        <!-- ico font -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/icon/icofont/css/icofont.css'/>">
        <!-- Font Awesome -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/icon/font-awesome/css/font-awesome.min.css'/>">
        <!-- Style.css -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/style.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/pages/notification/notification.css'/>">
        
        <!-- Required Jquery -->
        <script type="text/javascript" src="<c:url value='/assets_admin/js/jquery/jquery.min.js '/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/jquery-ui/jquery-ui.min.js '/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/popper.js/popper.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/bootstrap/js/bootstrap.min.js'/> "></script>
        <!-- waves js -->
        <script src="<c:url value='/assets_admin/pages/waves/js/waves.min.js'/>"></script>
        <!-- jquery slimscroll js -->
        <script type="text/javascript" src="<c:url value='/assets_admin/js/jquery-slimscroll/jquery.slimscroll.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/common-pages.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/pages/notification/notification.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/bootstrap-growl.min.js'/>"></script>
        
    </head>

    <body themebg-pattern="theme1">
        <%@include file="../src/loader.jsp" %>
        <section class="login-block">
            <!-- Container-fluid starts -->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <!-- Authentication card start -->

                        <form class="md-float-material form-material" method="post" >
                            <c:if test="${not empty alert}">
                                <script> notifications(`${alert}`, "top", 'center', '', 'danger', '');</script>
                            </c:if>
                            <c:if test="${not empty success}">
                                <script> notifications(`${success}`, "top", 'center', '', 'success', '');</script>
                            </c:if>
                            <div class="auth-box card">
                                <div class="card-block">
                                    <div class="row m-b-20">
                                        <div class="col-md-12">
                                            <h3 class="text-center">Đăng Nhập</h3>
                                        </div>
                                    </div>
                                    <div class="form-group form-primary">
                                        <input type="text" name="emailuser" class="form-control" required>
                                        <span class="form-bar"></span>
                                        <label class="float-label" >Nhập Tài Khoản Hoặc Email</label>
                                    </div>
                                    <br>
                                    <div class="form-group form-primary">
                                        <input type="password" name="password" class="form-control" required>
                                        <span class="form-bar"></span>
                                        <label class="float-label" for="password">Nhập Mật Khẩu</label>
                                    </div>
                                    <div class="row m-t-30">
                                        <div class="col-md-12">
                                            <button type="submit" class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20">Đăng Nhập</button>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-md-10">
                                            <p class="text-inverse text-left"><a href="../"><b>Quay Về</b></a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- end of form -->
                    </div>
                    <!-- end of col-sm-12 -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container-fluid -->
        </section>
        
    </body>

</html>
