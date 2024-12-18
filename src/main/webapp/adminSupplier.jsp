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
                <div class="header-title">Quản lý nhà cung cấp</div>
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
                    <p>Nhà Cung Cấp</p>
                    <div class="btn-item">
                        <button class="add open-dialog-btn">
                            <i class="fa-solid fa-plus"></i>
                            Thêm nhà cung cấp
                        </button>
                    </div>
                </div>
                <form method="get" action="addSupplier" class="dialog">
                    <input value="${productId}" hidden="hidden" name="productId">
                    <div class="dialog-wrapper">
                        <div class="dialog-title">Thêm nhà cung cấp</div>
                        <span class="close-btn">X</span>
                        <label>Tên</label>
                        <input type="text" name="new-supplier-name" required>
                        <label>Email</label>
                        <input type="email" name="new-supplier-email" placeholder="Eg: abc@gmail.com" required pattern="/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/">
                        <label>Phone</label>
                        <input type="tel" name="new-supplier-phone" placeholder="Eg: 0941457078" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" maxlength="12"  title="Số điện thoại gồm từ 10-12 số" required>
                        <button type="submit">Thêm</button>
                    </div>
                </form>
                <table>
                    <thead>
                    <tr>
                        <th class="s-cl">Xóa</th>
                        <th class="s-cl">Chỉnh sửa</th>
                        <th class="m-cl">Mã nhà cung cấp</th>
                        <th class="l-cl">Tên nhà cung cấp</th>
                        <th class="m-cl">Email</th>
                        <th class="m-cl">Phone</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.supplierList}" var="o">
                        <tr>
                            <td class="s-cl">
                                <a class="link" target="_blank" href="removeSupplier?supplierId=${o.getId()}">
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
                            <td class="m-cl">${o.getEmail()}</td>
                            <td class="m-cl">${o.getPhone()}</td>
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
