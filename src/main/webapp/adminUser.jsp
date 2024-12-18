<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    <c:import url="adminSideBar.jsp"/>
    <div class="main-content">
        <div id="manage-user" class="content-wrapper active">
            <div class="header-admin">
                <div class="header-title">Quản lý người dùng</div>
            </div>

            <div class="content">
                <div class="content-header">
                    <c:set var="searchValue" value="${requestScope.searchValue}"/>
                    <form method="post" action="adminSearchUser" class="content-search">
                        <input
                                type="text"
                                name="search-user"
                                placeholder="Tìm kiếm theo tên, tài khoản hoặc email ..."
                                class="input-search"
                                value="${searchValue}"
                        />
                        <i
                                class="icon fa-solid fa-magnifying-glass"
                        ></i>
                    </form>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th class="s-cl">Chỉnh sửa</th>
                        <th class="s-cl">ID</th>
                        <th class="m-cl">Tên</th>
                        <th class="l-cl">Tài khoản</th>
                        <th class="l-cl">Email</th>
                        <th class="m-cl">Số điện thoại</th>
                        <th class="m-cl">Trạng thái tài khoản</th>
                        <th class="m-cl">Vai trò</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.userList}" var="o">
                        <tr>
                            <td>
                                <a class="link" target="_blank" href="adminViewUser?userId=${o.getId()}">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                            </td>
                            <td>${o.getId()}</td>
                            <td>${o.getFullName()}</td>
                            <td>${o.getUsername()}</td>
                            <td>${o.getEmail()}</td>
                            <td>${o.getPhone()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${o.getStatus() == 1}">
                                        <p>Đã xác thực</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Chưa xác thực</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${o.getRole() == 1}">
                                        <p>Quản trị viên</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Người dùng</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
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
