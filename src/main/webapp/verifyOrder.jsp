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
    <link rel="stylesheet" href="assets/css/productDetail.css" />
    <link rel="stylesheet" href="assets/css/verifyOrder.css">
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


<h2>Thông tin đặt hàng</h2>
<form id="orderForm">
    <label for="name">Họ và tên:</label>
    <input type="text" id="name" name="name" required>

    <label for="address">Địa chỉ:</label>
    <input type="text" id="address" name="address" required>

    <label for="publicKey">Public Key:</label>
    <input type="text" id="publicKey" name="publicKey" placeholder="Nhập hoặc phát sinh key mới" required>

    <button id="generateKeyBtn" type="button">Phát sinh Key</button>

    <p id="keyInfo" style="display: none; color: green;">Public Key đã được phát sinh và nhập tự động!</p>

    <button type="submit">Thanh toán</button>
</form>

<p id="resultMessage"></p>


</body>
<script>document.getElementById("generateKeyBtn").addEventListener("click", () => {
    fetch("/key/generate", { method: "POST" })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                // Hiển thị public key và điền vào form
                document.getElementById("publicKey").value = data.publicKey;
                document.getElementById("keyInfo").style.display = "block";
                document.getElementById("keyInfo").innerText = "Public Key đã được phát sinh và nhập tự động!";
            } else {
                alert("Phát sinh Key thất bại, vui lòng thử lại.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi phát sinh Key.");
        });
});

// Xử lý gửi đơn hàng
document.getElementById("orderForm").addEventListener("submit", event => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = {
        name: formData.get("name"),
        address: formData.get("address"),
        publicKey: formData.get("publicKey"),
    };

    fetch("/order/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                document.getElementById("resultMessage").innerText = "Đặt hàng thành công!";
                document.getElementById("resultMessage").style.color = "green";
            } else {
                document.getElementById("resultMessage").innerText = "Đặt hàng thất bại: " + data.message;
                document.getElementById("resultMessage").style.color = "red";
            }
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById("resultMessage").innerText = "Có lỗi xảy ra, vui lòng thử lại.";
            document.getElementById("resultMessage").style.color = "red";
        });
});</script>
</html>