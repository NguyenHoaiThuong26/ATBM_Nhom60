<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                <div class="header-title">Quản lý danh mục</div>
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
            <c:set var="success" value="${requestScope.success}"/>
            <c:set var="fail" value="${requestScope.fail}"/>
            <c:choose>
                <c:when test="${not empty success}">${success}</c:when>
                <c:when test="${ not empty fail}">${fail}</c:when>
            </c:choose>
            <div class="content">
                <div class="btn-grp">
                    <p>Danh Mục</p>
                    <div class="btn-item">
                        <button class="add-category add open-dialog-btn">
                            <i class="fa-solid fa-plus"></i>
                            Thêm danh mục
                        </button>
                    </div>
                </div>
                <form method="get" action="addCategory" class="dialog-category">
                    <div class="dialog-wrapper">
                        <input value="${productId}" hidden="hidden" name="productId">
                        <div class="dialog-title">Thêm danh mục</div>
                        <span class="close-btn-cate" onclick="closeDialog()">X</span>
                        <label for="input1">Tên danh mục</label>
                        <input type="text" id="input1" name="new-category">
                        <button type="submit">Thêm</button>
                    </div>
                </form>

                <table>
                    <thead>
                    <tr>
                        <th class="s-cl">Xóa</th>
                        <th class="s-cl">Chỉnh sửa</th>
                        <th class="m-cl">Danh mục ID</th>
                        <th class="l-cl">Tên</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.categoryList}" var="o">
                        <tr>
                            <td class="s-cl">
                                <a class="link" href="removeCategory?categoryId=${o.getId()}">
                                    <i class="fa-regular fa-square-minus"></i>
                                </a>
                            </td>
                            <td class="s-cl">
                                <a class="link" target="_blank" href="#1">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                            </td>
                            <td class="m-cl">${o.getId()}</td>
                            <td class="l-cl">${o.getName()}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    let categoryButton = document.querySelector('.add-category');
    let dialogCate = document.querySelector('.dialog-category');
    let closeBtnCate = dialogCate.querySelector('.close-btn-cate');

    categoryButton.addEventListener('click', function (event) {
        event.preventDefault();
        toggleDialogVisibility(dialogCate);
    });
    closeBtnCate.addEventListener('click', function () {
        toggleDialogVisibility(dialogCate);
    });

    function toggleDialogVisibility(dialog) {
        // Hiển thị hoặc ẩn dialog tùy thuộc vào trạng thái hiện tại
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
