<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Admin - ${title} ${description}</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <meta name="keywords" content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
        <meta name="author" content="Codedthemes" />
        <!-- Favicon icon -->
        <link rel="icon" href="<c:url value='/assets_admin/images/favicon.ico'/>" type="image/x-icon">
        <!-- Google font-->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
        <!-- waves.css -->
        <link rel="stylesheet" href="<c:url value='/assets_admin/pages/waves/css/waves.min.css'/>" type="text/css" media="all">
        <!-- Required Fremwork -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/bootstrap/css/bootstrap.min.css'/>">
        <!-- waves.css -->
        <link rel="stylesheet" href="<c:url value='/assets_admin/pages/waves/css/waves.min.css'/>" type="text/css" media="all">
        <!-- themify icon -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/icon/themify-icons/themify-icons.css'/>">
        <!-- font-awesome-n -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/font-awesome-n.min.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/font-awesome.min.css'/>">
        <!-- Font Awesome -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/icon/font-awesome/css/font-awesome.min.css'/>">
        <!-- scrollbar.css -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/jquery.mCustomScrollbar.css'/>">
        <!-- ico font -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/icon/icofont/css/icofont.css'/>">
        <!-- Style.css -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/style.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/jquery.mCustomScrollbar.css'/>">
        <!-- morris chart -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/css/morris.js/css/morris.css'/>">
        <!-- notification -->
        <link rel="stylesheet" type="text/css" href="<c:url value='/assets_admin/pages/notification/notification.css'/>">


        <!-- Required Jquery -->
        <script type="text/javascript" src="<c:url value='/assets_admin/js/jquery/jquery.min.js'/> "></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/jquery-ui/jquery-ui.min.js'/> "></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/popper.js/popper.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/bootstrap/js/bootstrap.min.js'/> "></script>
        <!-- waves js -->
        <script src="<c:url value='/assets_admin/pages/waves/js/waves.min.js'/>"></script>
        <!-- jquery slimscroll js -->
        <script type="text/javascript" src="<c:url value='/assets_admin/js/jquery-slimscroll/jquery.slimscroll.js'/>"></script>
        <!-- slimscroll js -->
        <script src="<c:url value='/assets_admin/js/jquery.mCustomScrollbar.concat.min.js '/>"></script>

        <!-- menu js -->
        <script src="<c:url value='/assets_admin/js/pcoded.min.js'/>"></script>
        <script src="<c:url value='/assets_admin/js/vertical/vertical-layout.min.js'/>"></script>
        <script src="<c:url value='/assets_admin/js/jquery.mCustomScrollbar.concat.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/script.js'/>"></script>
        <!-- start notification -->
        <script type="text/javascript" src="<c:url value='/assets_admin/pages/notification/notification.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assets_admin/js/bootstrap-growl.min.js'/>"></script>
        <!-- /end notification -->
        <c:if test="${empty useradmin}">
            <script type="text/javascript"> window.location.href = "<c:url value='/admin/login'/>"</script>
        </c:if>
    </head>

    <body>

