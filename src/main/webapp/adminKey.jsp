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
                        <th>Xóa</th>
                        <th>Chỉnh sửa</th>
                        <th>Key ID</th>
                        <th>User ID</th>
                        <th>Public Key</th>
                        <th>Bắt đầu hiệu lực</th>
                        <th>Hết hiệu lực</th>
                        <th>Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.keyList}" var="o">

                        <tr>
                            <td class="s-cl">
                                <a class="link" href="removeKey?keyId=${o.getId()}">
                                    <i class="fa-regular fa-square-minus"></i>
                                </a>
                            </td>
                            <td>
                                <a class="link" target="_blank" href="adminViewKey?keyId=${o.getId()}">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                            </td>
                            <td>${o.getId()}</td>
                            <td>${o.getIdUser()}</td>
                            <td class="public-key-cell" style="word-wrap: break-word;">${o.getPublicKey()}</td>
                            <td>${o.getStartTime()}</td>
                            <td>${o.getExpiredTime()}</td>
                            <td>${o.isValid() == 'true' ? 'Đang hoạt động' : 'Vô hiệu hóa'}</td>
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
