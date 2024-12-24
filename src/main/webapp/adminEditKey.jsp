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
    <div class="title">Chỉnh sửa khóa</div>
    <c:set var="publicKey" value="${sessionScope.publicKey}"/>
    <c:set var="keyId" value="${sessionScope.keyId}"/>
    <div class="user-wraper">
        <form method="get" action="changeInfoKey" class="left">
            <input value="${publicKey.getId()}" hidden="hidden" name="keyId">

            <div class="user user-username">
                <label for="keyId" class="user-title mgr-20">Public Key ID:</label>
                <input id="keyId" name="keyId" class="user-sub-input" value="${publicKey.getId()}"></input>
            </div>

            <div class="user user-role">
                <label class="user-title mgr-20">Trạng thái: </label>
                <select id="selectOption" class="custom-select" name="isValid">
                    <c:choose>
                        <c:when test="${publicKey.isValid() == 'true'}">
                            <option class="user-sub-input" selected="selected" value="true">Đang hoạt động</option>
                            <option class="user-sub-input" value="false">Vô hiệu hóa</option>
                        </c:when>
                        <c:otherwise>
                            <option class="user-sub-input" value="true">Đang hoạt động</option>
                            <option class="user-sub-input" selected="selected" value="false">Vô hiệu hóa</option>
                        </c:otherwise>
                    </c:choose>

                </select>
                <button type="submit" class="add-role btn btn-submit btn-admin">Lưu</button>
            </div>

        </form>
    </div>



</div>

</body>
</html>

