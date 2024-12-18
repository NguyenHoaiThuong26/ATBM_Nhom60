<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.lang.Math" %>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Admin</title>

    <!-- reset CSS -->
    <link rel="stylesheet" href="./assets/css/reset.css"/>
    <link rel="stylesheet" href="./assets/css/index.css"/>
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

    <link rel="stylesheet" href="./assets/css/admin.css"/>
    <link rel="stylesheet" href="./assets/css/style.css"/>
</head>
<body>
<c:import url="./header.jsp"/>
<div class="container">
    <c:import url="./adminSideBar.jsp"/>
    <div class="main-content">
        <div id="manage-bill" class="content-wrapper">
            <div class="header-admin">
                <div class="header-title">Quản lý hóa đơn</div>
            </div>
            <div class="content-header">
                <div class="content-search">
                    <input
                            type="text"
                            placeholder="Tìm kiếm ..."
                            class="input-search"
                    />
                    <i class="icon fa-solid fa-magnifying-glass"></i>
                </div>
            </div>
            <div class="content">
                <table>
                    <thead>
                    <tr>
                        <th>Chỉnh sửa</th>
                        <th>ID</th>
                        <th>ID người dùng</th>
                        <th>Phương thức thanh toán</th>
                        <th>Ngày tạo hóa đơn</th>
                        <th>Tổng tiền đơn hàng</th>
                        <th>Trạng thái đơn hàng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.billList}" var="o">
                        <c:set var="price" value="${o.getTotalPrice()}"/>
                        <c:set var="roundedPrice" value="${Math.round(price)}"/>

                        <tr>
                            <td>
                                <a class="link" target="_blank" href="adminViewBill?billId=${o.getId()}">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                            </td>
                            <td>${o.getId()}</td>
                            <td>${o.getUserId()}</td>
                            <td>${o.getPaymentMethod()}</td>
                            <td>${o.getCreateDate()}</td>
                            <td>
                                <fmt:formatNumber var="formattedPrice" value="${roundedPrice}" pattern="###,###,###"/>
                                    ${formattedPrice}&nbsp;₫
                            </td>
                            <td>${o.getStatus() == 'IN_PROGRESS' ? 'Chờ xử lý' : (o.getStatus() == 'DONE' ? 'Đã nhận đơn' : 'Đang giao hàng')}</td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
