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

    <link rel="stylesheet" href="./assets/css/adminEditProduct.css"/>
    <link rel="stylesheet" href="./assets/css/style.css"/>
</head>
<body>
<div class="container">
    <div>
        <i class="fa-solid fa-arrow-left"></i>
        <a class="back-cta" href="adminProductIndex">Trang Quản Lý</a>
    </div>
    <div class="title">Chỉnh sửa sản phẩm</div>
    <c:set var="product" value="${sessionScope.product}"/>
    <c:set var="productId" value="${sessionScope.productId}"/>
    <div class="product-wraper">
        <form method="get" action="changeInforProduct" class="left">
            <p class="product-header">Thông tin sản phẩm</p>
            <div class="product product-name">
                <label for="product-name" class="product-title">Tên sản phẩm:</label>
                <input value="${productId}" hidden="hidden" name="productId">
                <input id="product-name" name="product-name" class="product-sub" value="${product.getName()}"></input>
            </div>
            <div class="product product-discount">
                <label for="selectOptionDiscount" class="product-title">Giảm giá: </label>
                <select id="selectOptionDiscount" class="custom-select" name="product-discount">
                    <c:forEach var="discount" items="${sessionScope.discountList}">
                        <c:set var="roundedPrice" value="${Math.round(discount.getDiscountAmount(discount.getId()))}"/>
                        <c:choose>
                            <c:when test="${product.getDiscountId() == discount.getId()}">
                                <option class="product-sub" selected="selected"
                                        value="${product.getDiscountId()}">${roundedPrice}&nbsp;%
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option class="product-sub"
                                        value="${discount.getId()}">${roundedPrice}&nbsp;%
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="product product-category">
                <label class="product-title">Danh mục: </label>
                <select id="selectOption" class="custom-select" name="product-category">
                    <c:forEach var="category" items="${sessionScope.categories}">
                        <c:choose>
                            <c:when test="${product.getCategoryId() == category.getId()}">
                                <option class="product-sub" selected="selected"
                                        value="${product.getCategoryName(product.getCategoryId())}">${product.getCategoryName(product.getCategoryId())}</option>
                            </c:when>
                            <c:otherwise>
                                <option class="product-sub" value="${category.getName()}">${category.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="product product-brand">
                <label class="product-title">Thương hiệu: </label>
                <select id="selectBrand" class="custom-select" name="product-brand">
                    <c:forEach var="brand" items="${sessionScope.brands}">
                        <c:choose>
                            <c:when test="${product.getBrandId() == brand.getId()}">
                                <option class="product-sub" selected="selected"
                                        value="${product.getBrandName(product.getBrandId())}">${product.getBrandName(product.getBrandId())}</option>
                            </c:when>
                            <c:otherwise>
                                <option class="product-sub" value="${brand.getName()}">${brand.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="product product-supplier">
                <label class="product-title">Nhà Cung Cấp</label>
                <select id="selectSupplier" class="custom-select" name="product-supplier">
                    <c:forEach var="supplier" items="${sessionScope.suppliers}">
                        <c:choose>
                            <c:when test="${product.getSupplierId() == supplier.getId()}">
                                <option class="product-sub" selected="selectedvalue="
                                        value="${product.getSupplierName(product.getSupplierId())}">${product.getSupplierName(product.getSupplierId())}</option>
                            </c:when>
                            <c:otherwise>
                                <option class="product-sub" value="${supplier.getName()}">${supplier.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="product product-quantity">
                <label for="product-quantity" class="product-title">Số lượng: </label>
                <input id="product-quantity" name="product-quantity" class="product-sub"
                       value="${product.getQuantity()}"></input>
            </div>
            <div class="product product-price">
                <label for="product-price" class="product-title">Giá: </label>
                <c:set var="price" value="${product.getTotalPrice()}"/>
                <c:set var="roundedPrice" value="${Math.round(price)}"/>
                <fmt:formatNumber var="formattedPrice" value="${roundedPrice}" pattern="###,###,###"/>
                <input id="product-price" name="product-price" class="product-sub"
                       value="${formattedPrice}"></input>
            </div>
            <div class="product product-desc">
                <label for="product-desc" class="product-title">Thông tin sản phẩm: </label>
                <textarea id="product-desc" name="product-desc" class="desc-area" id=""
                          class="product-sub">${product.getDescription()}</textarea>
            </div>
            <button type="submit" class="btn btn-submit">
                Cập nhập thông tin
            </button>
        </form>
        <div class="right">
            <p class="product-header">Hình ảnh sản phẩm</p>
            <div class="list-img">
                <c:forEach var="o" items="${product.imageProducts(product.getId())}">
                    <div class="item">
                            <i class="fa-regular fa-circle-xmark delete-ic"></i>
                        <img class="img-product" src="${o.getLink()}" alt="">
                    </div>
                    <form class="confirm-form" style="display: none;">
                        <p class="form-title">Xác nhận xóa hình ảnh</p>
                        <input type="hidden" name="imgId" value="${o.getId()}">
                        <div class="btn-grp">
                            <button type="button" class="cancel-btn">Hủy</button>
                            <button type="button" class="confirm-btn">
                                <a href="adminRemoveImg?productId=${product.getId()}&imgId=${o.getId()}">Xác nhận</a>
                            </button>
                        </div>
                    </form>
                </c:forEach>
            </div>
            <div id="imageForm" style="display: flex;">
                <form id="addImageForm" action="addProductImage" method="GET">
                    <!-- Chỉ định action và method của form -->
                    <p class="form-title">Thêm hình ảnh</p>
                    <p class="important">* Lưu ý: có thể thêm nhiều link 1 lần,</p>
                    <p class="important">và link phải ngăn cách nhau bằng khoảng trắng</p>
                    <input type="text" placeholder="Nhập URL" name="link-img" id="linkImg">
                    <button type="button" id="submitBtn"> <!-- Loại button vẫn là "button" -->
                        Thêm hình ảnh
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>

    document.addEventListener("DOMContentLoaded", function () {
        const submitBtn = document.getElementById("submitBtn");
        const addImageForm = document.getElementById("addImageForm");

        submitBtn.addEventListener("click", function () {
            const linkImgInput = document.getElementById("linkImg");
            const imageUrl = linkImgInput.value;

            const productId = "${product.getId()}";
            const addProductImageUrl = "addProductImage?productId=" + productId + "&imageUrl=" + encodeURIComponent(imageUrl);


            window.location.href = addProductImageUrl;
        });
    });

    document.addEventListener("DOMContentLoaded", function () {
        const deleteIcons = document.querySelectorAll(".delete-ic");

        deleteIcons.forEach(function (icon) {
            icon.addEventListener("click", function () {
                const confirmForm = icon.parentElement.nextElementSibling; // Lấy ra form xác nhận (element tiếp theo của biểu tượng xóa)
                confirmForm.style.display = "flex"; // Hiển thị form xác nhận
            });
        });

        // Xử lý sự kiện khi nhấn nút Hủy
        const cancelBtns = document.querySelectorAll(".cancel-btn");
        cancelBtns.forEach(function (btn) {
            btn.addEventListener("click", function (event) {
                event.preventDefault();
                const confirmForm = btn.parentElement.parentElement; // Lấy ra form chứa nút Hủy
                confirmForm.style.display = "none"; // Ẩn form xác nhận
            });
        });
    });

</script>
</body>
</html>

