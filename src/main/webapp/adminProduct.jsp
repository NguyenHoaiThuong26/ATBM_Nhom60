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
<div class="container">
    <c:import url="adminSideBar.jsp"/>
    <div class="main-content">
        <div id="manage-product" class="content-wrapper">
            <div class="header-admin">
                <div class="header-title">Quản lý sản phẩm</div>
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
                    <p>Sản phẩm</p>
                    <div class="btn-item">
                        <button class="add open-dialog-btn" onclick="openDialog()">
                            <i class="fa-solid fa-plus"></i>
                            Thêm sản phẩm
                        </button>
                    </div>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th class="s-cl">Xóa</th>
                        <th class="s-cl">Chỉnh sửa</th>
                        <th class="m-cl">Product ID</th>
                        <th class="l-cl">Tên</th>
                        <th class="m-cl">Giá</th>
                        <th class="m-cl">Danh mục</th>
                        <th class="m-cl">Thương hiệu</th>
                        <th class="s-cl">Số lượng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.productList}" var="o">
                        <c:set var="price" value="${o.getTotalPrice()}"/>
                        <c:set var="roundedPrice" value="${Math.round(price)}"/>

                        <tr>
                            <td class="s-cl">
                                <a class="link" href="removeProduct?productId=${o.getId()}">
                                    <i class="fa-regular fa-square-minus"></i>
                                </a>
                            </td>

                            <td class="s-cl">
                                <a class="link" target="_blank" href="adminViewProduct?productId=${o.getId()}">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                            </td>
                            <td class="m-cl">${o.getId()}</td>
                            <td class="l-cl">${o.getName()}</td>
                            <td class="m-cl">
                                <fmt:formatNumber var="formattedPrice" value="${roundedPrice}" pattern="###,###,###"/>
                                    ${formattedPrice}&nbsp;₫
                            </td>
                            <td class="m-cl">${o.getCategoryName(o.getCategoryId())}</td>
                            <td class="m-cl">${o.getBrandName(o.getBrandId())}</td>
                            <td class="s-cl">${o.getQuantity()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="overlay" onclick="closeDialog()"></div>
        <form method="get" action="addProduct" class="dialog-container">

            <label for="productName">Tên Sản Phẩm:</label>
            <input type="text" id="productName" name="productName" autofocus="autofocus">

            <label for="discount">Giảm Giá:</label>
            <select id="discount" name="discount">
                <c:forEach var="discount" items="${sessionScope.discountList}">
                    <c:choose>
                        <c:when test="${discount.getId() == 0}">
                            <option value="0">Không giảm giá</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${discount.getId()}">${discount.getAmount() * 100}%</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

            <label for="category">Danh Mục:</label>
            <select id="category" name="category">
                <c:forEach var="category" items="${sessionScope.categoryList}">
                    <option value="${category.getId()}">${category.getName()}</option>
                </c:forEach>
            </select>

            <label for="brand">Thương Hiệu:</label>
            <select id="brand" name="brand">
                <c:forEach var="brand" items="${sessionScope.brandList}">
                    <option value="${brand.getId()}">${brand.getName()}</option>
                </c:forEach>
            </select>

            <label for="supplier">Nhà Cung Cấp:</label>
            <select id="supplier" name="supplier">
                <c:forEach var="supplier" items="${sessionScope.supplierList}">
                    <option value="${supplier.getId()}">${supplier.getName()}</option>
                </c:forEach>
            </select>

            <label for="quantity">Số Lượng:</label>
            <div class="quantity-input">
                <button type="button" class="change-quantity" onclick="decrementQuantity()">-</button>
                <input type="number" id="quantity" name="quantity" value="1" min="1">
                <button type="button" class="change-quantity" onclick="incrementQuantity()">+</button>
            </div>

            <label for="price">Giá Sản Phẩm:</label>
            <input type="number" id="price" name="price">
            <label for="description">Mô Tả Sản Phẩm: </label>
            <textarea id="description" name="description"></textarea>
            <div class="button-container">
                <button type="submit">Thêm Sản Phẩm</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.ckeditor.com/4.16.2/full-all/ckeditor.js"></script>

<script src="https://cdn.ckeditor.com/4.16.2/full-all/adapters/ckfinder/ckfinder.js"></script>

<script>
    function decrementQuantity() {
        let quantityInput = document.getElementById('quantity');
        if (quantityInput.value > 1) {
            quantityInput.value = parseInt(quantityInput.value) - 1;
        }
    }

    function incrementQuantity() {
        let quantityInput = document.getElementById('quantity');
        quantityInput.value = parseInt(quantityInput.value) + 1;
    }
</script>
<script>
    // Hàm mở dialog
    function openDialog() {
        var dialog = document.querySelector('.dialog-container');
        var overlay = document.querySelector('.overlay');
        dialog.style.display = 'block';
        overlay.style.display = 'block';
    }

    // Hàm đóng dialog
    function closeDialog() {
        var dialog = document.querySelector('.dialog-container');
        var overlay = document.querySelector('.overlay');
        dialog.style.display = 'none';
        overlay.style.display = 'none';
    }
</script>
<script src="./js/adminJS/dialogForm.js"></script>
</body>
</html>
