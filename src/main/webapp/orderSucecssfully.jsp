<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chốt đơn thành công</title>
    <style>
        .information {
            padding-top: 100px;
        }
    </style>

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css" />
    <link rel="stylesheet" href="assets/css/index.css" />
    <link rel="stylesheet" href="assets/css/bill.css" />
    <link rel="stylesheet" href="assets/css/bill2.css" />
    <link rel="stylesheet" href="assets/css/profile.css" />
    <!-- <link rel="stylesheet" href="./assets/css/product.css"> -->

    <!-- embed fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    <!-- FONT GOOGLE -->
    <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;700&display=swap"
            rel="stylesheet"
    />

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

    <!-- Icon -->
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />
    <!-- styles -->
    <link rel="stylesheet" href="assets/css/style.css" />
    <link rel="stylesheet" href="assets/css/profile.css" />
    <link rel="stylesheet" href="assets/css/productDetail.css" />

    <!-- OWL CAROUSEL CSS -->
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css" />
    <link
            rel="stylesheet"
            href="assets/css/owl.theme.default.min.css"
    />
</head>
<body>
<%--    HEADER--%>
<c:import url="header.jsp"/>

<div class="information input-content">
    <h3>Thanh toán thành công! Vui lòng nhập chữ ký để xác thực đơn hàng</h3>
    <!-- Input for Signature -->
    <form action="./thank-you" method="post">
        <label for="signature">Chữ ký của bạn:</label>
        <input type="text" id="signature" class="input-name" name="signature" required style="width: 60%!important;">
        <input type="hidden" id="billId" name="billId" value="${billId}">
<%--        <span>${billId}</span>--%>
        <button type="submit">Ký xác nhận</button>
    </form>


    <button style="margin-top: 40px"><a href="home">Quay trở lại trang chủ</a></button>
    <c:if test="${not empty message}">
        <p class="key-alert <c:out value="${message.contains('thành công') ? 'success' : 'error'}" />" id="alertMessage">
                ${message}
        </p>
    </c:if>
</div>
<script>
    setTimeout(function() {
        const alertMessage = document.getElementById('alertMessage');
        if (alertMessage) {
            alertMessage.style.transition = "opacity 0.5s";
            alertMessage.style.opacity = 0;


            setTimeout(() => {
                alertMessage.style.display = 'none';
            }, 500);
        }
    }, 3000);
</script>

</body>
</html>