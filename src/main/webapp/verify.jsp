<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Xác minh tài khoản</title>
    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css"/>

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

    <!-- Icon -->
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />

    <!-- styles -->
    <link rel="stylesheet" href="assets/css/style.css"/>
    <link rel="stylesheet" href="assets/css/log.css"/>
</head>
<body>
<!-- Verify Code -->
<div class="content">
    <c:set var="email" value="${sessionScope.email}"/>
    <c:set var="emailForgetPassword" value="${sessionScope.emailForgetPassword}"/>
    <c:set var="errorMessage" value="${requestScope.errorMessage}"/>
    <c:set var="verificationSuccess" value="${requestScope.verificationSuccess}"/>
    <c:set var="emailNotExist" value="${requestScope.emailNotExist}"/>

    <c:set var="verificationCode" value="${sessionScope.verificationCode}"/>
    <c:set var="verificationTime" value="${sessionScope.verificationTime}"/>

    <div class="form-wrapper">
        <a href="./signUp.jsp" class="back-cta">
            <i class="fa-solid fa-chevron-left"></i>
            Trở về trang đăng ký
        </a>
        <c:choose>
            <c:when test="${not empty verificationSuccess}">
                <!-- Hiển thị thông báo thành công -->
                <p class="success-message">${verificationSuccess}</p>
                <button class="submit sub-btn">
                    <a class="link" href="./logIn.jsp" class="back-to-login">Quay lại trang đăng nhập</a>
                </button>
            </c:when>
            <c:otherwise>
                <form action="./verify"
                      method="post"
                      class="form"
                      autocomplete="off"
                >
                    <p class="title">Xác Thực</p>
                    <div class="form-grp">
                        <p>Đã gửi mã xác minh đến 
                            <strong class="email__signup">
                                <c:choose>
                                    <c:when test="${not empty email}">
                                        ${email}
                                    </c:when>
                                    <c:otherwise>
                                        ${emailForgetPassword}
                                    </c:otherwise>
                                </c:choose>
                            </strong>
                        </p>
                        <label for="userEnteredCode">Nhập mã xác minh</label>
                        <div class="user-input">
                            <i class="fa-solid fa-circle-check ic"></i>
                            <input type="number" id="userEnteredCode" name="userEnteredCode" required
                                   placeholder="Nhập mã xác minh">
                        </div>
                        <div class="errorMessage">
                            <c:if test="${not empty errorMessage}">
                                <p class="text-danger">
                                    ${errorMessage}
                                </p>
                            </c:if>
                            <c:if test="${not empty emailNotExist}">
                                <p class="text-danger">
                                        ${emailNotExist}
                                </p>
                            </c:if>
                        </div>
                    </div>
                    <button type="submit" class="submit sub-btn">
                        XÁC MINH
                    </button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
