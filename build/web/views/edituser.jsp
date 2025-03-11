<%String pageName = "contact";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="./src/header.jsp" %>
<%@include file="./src/navbar.jsp" %>
<c:if test="${empty userclient}">
    <script> window.location.href = "<c:url value='/login'/>"</script>
</c:if>
<div class="untree_co-section">
    <div class="container">
        <c:if test="${not empty alert}">
            <script> notifications(`${alert}`, "top", 'center', '', 'danger', '');</script>
        </c:if>
        <c:if test="${not empty success}">
            <script> notifications(`${success}`, "top", 'center', '', 'success', '');</script>
        </c:if>
        <div class="row">
            <div class="col-md-12 mb-5 mb-md-0">
                <h2 class="h3 mb-3 text-black">Chỉnh Sửa Thông Tin Cá Nhân</h2>
                <div class="p-3 p-lg-5 border bg-white">
                    <form method="post">
                        <div class="form-group">
                            <label for="fullname" class="text-black">Họ Tên <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="fullname" name="fullname" value="${userclient.name}" required>
                        </div>
                        <div class="form-group">
                            <label for="username" class="text-black">Tài Khoản <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="username" name="username" value="${userclient.username}" readonly required>
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-black">Email <span class="text-danger">*</span></label>
                            <input type="email" class="form-control" id="email" name="email" value="${userclient.email}" required pattern=".+@gmail\.com" title="Email phải có định dạng @gmail.com">
                        </div>
                        <div class="form-group">
                            <label for="phone" class="text-black">Số Điện Thoại <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${userclient.phone}" required pattern="\d+" title="Số điện thoại phải là dạng số">
                        </div>
                        <div class="form-group">
                            <label for="province" class="text-black">Tỉnh Thành <span class="text-danger">*</span></label>
                            <select name="province" class="form-control" required style="font-size: 14px;" size="1">
                                <option value="" disabled selected hidden>Chọn Tỉnh Thành</option>
                                <c:forEach items="${listprovinces}" var="item">
                                    <c:if test="${userclient.province == item.name}">
                                        <option value="${item.name}" selected>${item.name}</option>
                                    </c:if>
                                    <c:if test="${useredit.province != item.name}">
                                        <option value="${item.name}">${item.name}</option>
                                    </c:if>
                                </c:forEach>    
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="address" class="text-black">Địa Chỉ <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="address" name="address" value="${userclient.address}" required>
                        </div>
                        <br>
                        <div class="form-group">
                            <button type="submit" class="btn btn-black btn-lg py-3 btn-block">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="./src/footer.jsp" %>