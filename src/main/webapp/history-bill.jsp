<%@ page import="bean.Item" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="vi_VN"/>
<fmt:setBundle basename="java.text.resources"/>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Lịch sử mua hàng</title>

    <!-- reset CSS -->


    <!-- <link rel="stylesheet" href="./assets/css/product.css"> -->

    <!-- embed fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/reset.css"/>
    <link rel="stylesheet" href="assets/css/profile.css"/>
    <link rel="stylesheet" href="assets/css/index.css"/>
    <link rel="stylesheet" href="assets/css/style.css"/>

    <link rel="stylesheet" href="assets/css/list.css">
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
    <link rel="stylesheet" href="./assets/css/style.css"/>

    <!-- OWL CAROUSEL CSS -->
    <link rel="stylesheet" href="./assets/css/owl.carousel.min.css"/>
    <link rel="stylesheet" href="./assets/css/owl.theme.default.min.css"/>
</head>
<body>

<!-- HEADER -->
<c:import url="header.jsp"/>


<div class="container-fluid">
    <!-- PROFILE -->
    <div class="profile-container">
        <%--            <form action="./profile" method="post" class="form">--%>
        <!-- .dLDnti -->
        <div data-view-id="breadcrumb_container" class="profile-return">
            <!-- .bXIPFu -->
            <div class="profile-return-content">
                <!-- .hfMLFx -->
                <div class="breadcrumb">
                    <a
                            class="breadcrumb-item br1"
                            data-view-id="breadcrumb_item"
                            data-view-index="0"
                            href="/"
                    >
                        <span>Trang chủ</span>
                    </a>
                    <span class="icon icon-next">
                            <i class="fa fa-angle-right"></i>
                        </span>
                    <a
                            href="#"
                            class="breadcrumb-item"
                            data-view-id="breadcrumb_item"
                            data-view-index="1"
                    >
                            <span title="Thông tin tài khoản"
                            >Lịch sử mua hàng</span
                            >
                    </a>
                </div>
            </div>
        </div>
        <jsp:include page="sidebar-profile.jsp"/>
        <div class="your__cart ms-2 p-2 account" style="width: 82%!important;">
            <table>
                <thead>
                <tr>
                    <th scope="col">#Mã đơn hàng</th>
                    <th scope="col">Ngày mua</th>
                    <th scope="col">Họ và tên</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Phương thức thanh toán</th>
                    <th scope="col">Trạng thái đơn hàng</th>
                    <th scope="col">Trạng thái bảo mật</th>
                    <th scope="col">Chi tiết</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listOrder}" var="order">
                    <tr>
                        <td data-label="#Mã đơn hàng">${order.id}</td>
                        <td data-label="Ngày mua">${order.createDate}</td>
                        <td data-label="Họ và tên">${order.fullName}</td>
                        <td data-label="Số điện thoại">${order.phone}</td>
                        <td data-label="Địa chỉ">${order.address}</td>
                        <td data-label="Phương thức thanh toán">${order.paymentMethod}</td>
                        <td data-label="Trạng thái đơn hàng">${order.status == 'IN_PROGRESS' ? 'Chờ xử lý' : (order.status == 'DONE' ? 'Đã nhận đơn' : 'Đang giao hàng')}</td>
                        <td data-label="Trạng thái bảo mật">${order.address}</td>
                        <td data-label="Chi tiết">
                            <div class="list-item">
                                <button id="billDetailBtn" type="button" class="button active account-password" data-order-id="${order.id}" style="
                                        height: 40px;
                                        margin: 0 6px;
                                        ">
                                    Chi tiết
                                </button>
                            </div>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <div class="main-content" id="billDetailsContainer">
                <div><h3 class="info_detailText">Thông tin chi tiết</h3></div>
                <div class="your__cart">
                    <table>
                        <thead>
                        <tr>
                            <th>Mã SP</th>
                            <th>Tên SP</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Tổng giá</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <br>
                </div>
            </div>
        </div>



    </div>
</div>
<!-- MAIN JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

<script>
    document.querySelectorAll("#billDetailBtn").forEach(button => {
        button.addEventListener("click", function() {
            const orderId = this.getAttribute("data-order-id");

            $.ajax({
                url: './getBillDetails',
                type: 'GET',
                data: {bill_detail_id: orderId},
                success: function(response) {
                    console.log("Response:", response);
                    const billDetailsContainer = document.getElementById("billDetailsContainer");
                    billDetailsContainer.style.display = "block";

                    // Clear existing table content
                    const tbody = billDetailsContainer.querySelector("tbody");
                    tbody.innerHTML = ""; // Xóa nội dung cũ

                    // Populate new rows with response data
                    response.forEach(item => {
                        const totalPrice = item.price * item.quantity; // Tính tổng giá cho sản phẩm
                        console.log("id" , item.product.id);
                        console.log("name" , item.product.name);
                        const rowHTML =
                            "<tr>" +
                            "<td>" + item.product.id + "</td>" +
                            "<td>" + item.product.name + "</td>" +
                            "<td>" + item.price.toLocaleString('vi-VN') + "₫</td>" +
                            "<td>" + item.quantity + "</td>" +
                            "<td>" + totalPrice.toLocaleString('vi-VN') + "₫</td>" +
                            "</tr>";

                        tbody.innerHTML += rowHTML;
                    });

                },
                error: function() {
                    alert('Đã có lỗi khi tải chi tiết hóa đơn.');
                }
            });
        });
    });
</script>


</html>