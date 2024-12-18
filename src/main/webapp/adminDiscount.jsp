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
<c:import url="header.jsp"/>
<div class="container" >
    <c:import url="adminSideBar.jsp"/>
    <div class="main-content">
        <div id="manage-product" class="content-wrapper">
            <div class="header-admin">
                <div class="header-title">Quản lý mã giảm giá</div>
            </div>

            <div class="content-header">
                <c:set var="searchValue" value="${requestScope.searchValue}"/>
                <form method="post" action="adminSearchProduct" class="content-search" accept-charset="UTF-8">
                    <input
                            type="text"
                            name="search-product"
                            placeholder="Tìm kiếm theo tên, danh mục, thương hiệu"
                            class="input-search"
                            value="${searchValue}"
                    />
                    <i
                            class="icon fa-solid fa-magnifying-glass"
                    ></i>
                </form>
            </div>
            <div class="content">
                <div class="btn-grp">
                    <p>Mã Giảm Giá</p>
                    <div class="btn-item">
                        <button class="add open-dialog-btn" onclick="openDialogDiscount()">
                            <i class="fa-solid fa-plus"></i>
                            Thêm mã giảm giá
                        </button>
                    </div>
                </div>
                <form method="post" action="addDiscount" class="dialog">
                    <div class="dialog-wrapper">
                        <div class="dialog-title">Thêm mã giảm giá</div>
                        <span class="close-btn">X</span>
                        <label>Phần trăm giảm </label>
                        <input type="number" name="percentage-discount" placeholder="0-99" required>
                        <br>
                        <label>Ngày bắt đầu</label>
                        <input type="datetime-local" name="start-date" required>
                        <br>
                        <label>Ngày kết thúc</label>
                        <input type="datetime-local" name="end-date" required>
                        <br>
                        <button type="submit">Thêm</button>
                    </div>
                </form>
                <table>
                    <thead>
                    <tr>
                        <th class="s-cl">Xóa</th>
                        <th class="s-cl">Chỉnh sửa</th>
                        <th class="m-cl">Mã Giảm Giá</th>
                        <th class="l-cl">Giảm giá (%)</th>
                        <th class="m-cl">Ngày bắt đầu</th>
                        <th class="m-cl">Ngày kết thúc</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.discountList}" var="o">
                        <c:set var="amount" value="${o.getAmount() * 100}"/>
                        <c:set var="roundedPrice" value="${Math.round(amount)}"/>

                        <tr>
                            <td class="s-cl">
                                <a class="link" href="removeDiscount?discountId=${o.getId()}">
                                    <i class="fa-regular fa-square-minus"></i>
                                </a>
                            </td>
                            <td class="s-cl">
                                <a class="link" target="_blank" href="adminViewProduct?productId=${o.getId()}">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                            </td>
                            <td class="m-cl">${o.getId()}</td>
                            <td class="m-cl">
                                <fmt:formatNumber var="formattedPrice" value="${roundedPrice}" pattern="###,###,###"/>
                                    ${formattedPrice}&nbsp;%
                            </td>
                            <td class="m-cl">${o.getDiscountStartDate(o.getId())}</td>
                            <td class="m-cl">${o.getDiscountEndDate(o.getId())}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    let addDiscountButton = document.querySelector('.add');
    let dialogDiscount = document.querySelector('.dialog');
    let closeBtnDiscount = dialogDiscount.querySelector('.close-btn');

    addDiscountButton.addEventListener('click', function (event) {
        event.preventDefault();
        toggleDialogVisibility(dialogDiscount);
    });

    closeBtnDiscount.addEventListener('click', function () {
        toggleDialogVisibility(dialogDiscount);
    });

    function toggleDialogVisibility(dialog) {
        if (dialog.style.display === 'none' || dialog.style.display === '') {
            dialog.style.display = 'block';
        } else {
            dialog.style.display = 'none';
        }
    }

</script>
<script src="./js/adminJS/dialogForm.js"></script>
</body>
</html>
