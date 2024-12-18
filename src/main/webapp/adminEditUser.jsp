<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Admin</title>

    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css"/>
    <link rel="stylesheet" href="assets/css/index.css"/>
    <!-- FONT AWRSOME -->
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
    />

    <link
            href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap"
            rel="stylesheet"
    />
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
            rel="stylesheet"
    />

    <!-- embed fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

    <link
            href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap"
            rel="stylesheet"
    />
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
            rel="stylesheet"
    />

    <link rel="stylesheet" href="assets/css/adminEditProduct.css"/>
    <link rel="stylesheet" href="assets/css/style.css"/>
</head>
<body>
<div class="container">
    <div>
        <i class="fa-solid fa-arrow-left"></i>
        <a class="back-cta" href="adminProductIndex">Trang Quản Lý</a>
    </div>
    <div class="title">Chỉnh sửa người dùng</div>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:set var="userId" value="${sessionScope.userId}"/>
    <div class="user-wraper">
        <form method="get" action="changeInfoUser" class="left">
            <input value="${user.getId()}" hidden="hidden" name="userId">
            <p class="user-header">Thông tin người dùng</p>
            <div class="user user-fullName">
                <label for="user-fullName" class="user-title mgr-20">Tên người dùng:</label>
                <input id="user-fullName" name="user-fullName" class="user-sub-input" value="${user.getFullName()}"></input>

            </div>

            <div class="user user-username">
                <label for="user-username" class="user-title mgr-20">Tên tài khoản:</label>
                <input id="user-username" name=" user-username" class="user-sub-input" value="${user.getUsername()}"></input>
            </div>

            <div class="user user-email">
                <label for="user-email" class="user-title mgr-20">Email:</label>
                <input id="user-email" name=" user-email" class="user-sub-input" value="${user.getEmail()}"></input>
            </div>

            <div class="user user-phone">
                <label for="user-phone" class="user-title mgr-20">Số điện thoại:</label>
                <input id="user-phone" name="user-phone" class="user-sub-input" value="0${user.getPhone()}"></input>
            </div>

            <div class="user user-status">
                <label for="user-status" class="user-title mgr-20">Trạng thái:</label>
                <c:choose>
                    <c:when test="${user.getStatus() == 1}">
                        <input id="user-status" name="user-status" class="user-sub-input" value="Đã xác thực"></input>
                    </c:when>
                    <c:otherwise>
                        <input id="user-status" name="user-status" class="user-sub-input" value="Chưa xác thực"></input>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="user user-role">
                <label class="user-title mgr-20">Vai trò: </label>
                <select id="selectOption" class="custom-select" name="user-role">
                    <c:choose>
                        <c:when test="${user.getRole() == 0}">

                            <option class="user-sub-input" selected="selected" value="0">Người dùng</option>
                            <option class="user-sub-input" value="1">Quản trị viên</option>
                        </c:when>
                        <c:otherwise>
                            <option class="user-sub-input" value="0">Người dùng</option>
                            <option class="user-sub-input" selected="selected" value="1">Quản trị viên</option>
                        </c:otherwise>
                    </c:choose>

                </select>
                <button type="submit" class="add-role btn btn-submit btn-admin">Lưu</button>
            </div>

        </form>
    </div>



</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var saveButton = document.querySelector('.add-role'); // Chọn nút "Lưu" bằng class

        // Lắng nghe sự kiện click trên nút "Lưu"
        saveButton.addEventListener('click', function (event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định của form

        });
    });
</script>

</body>
</html>

