<%
    if (session != null) {
        session.invalidate();
    }
%>

<script type="text/javascript">
    localStorage.removeItem('cart');
    window.location.href = "../home";
</script>