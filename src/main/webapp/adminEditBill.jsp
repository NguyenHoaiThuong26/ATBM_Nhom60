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
    <div class="title">Chỉnh sửa hóa đơn</div>
    <c:set var="bill" value="${sessionScope.bill}"/>
    <c:set var="billId" value="${sessionScope.billId}"/>
    <div class="user-wraper">
        <form method="get" action="changeInfoBill" class="left">
            <input value="${bill.getId()}" hidden="hidden" name="billId">

            <div class="user user-username">
                <label for="bill-id" class="user-title mgr-20">Mã hóa đơn:</label>
                <input id="bill-id" name="bill-id" class="user-sub-input" value="${bill.getId()}"></input>
            </div>

            <div class="user user-role">
                <label class="user-title mgr-20">Trạng thái đơn hàng: </label>
                <select id="selectOption" class="custom-select" name="bill-status">
                    <c:choose>
                        <c:when test="${bill.getStatus() == 'IN_PROGRESS'}">
                            <option class="user-sub-input" selected="selected" value="IN_PROGRESS">Chờ xử lý</option>
                            <option class="user-sub-input" value="DONE">Đã nhận đơn</option>
                            <option class="user-sub-input" value="IN_SHIPPING">Đang giao hàng</option>
                        </c:when>

                        <c:when test="${bill.getStatus() == 'DONE'}">
                            <option class="user-sub-input" value="IN_PROGRESS">Chờ xử lý</option>
                            <option class="user-sub-input" selected="selected" value="DONE">Đã nhận đơn</option>
                            <option class="user-sub-input" value="IN_SHIPPING">Đang giao hàng</option>
                        </c:when>

                        <c:otherwise>
                            <option class="user-sub-input" value="IN_PROGRESS">Chờ xử lý</option>
                            <option class="user-sub-input" value="DONE">Đã nhận đơn</option>
                            <option class="user-sub-input" selected="selected"  value="IN_SHIPPING">Đang giao hàng</option>
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

