<%
    request.getSession().removeAttribute("useradmin");
    response.sendRedirect(request.getContextPath() + "/admin/login");
%>

