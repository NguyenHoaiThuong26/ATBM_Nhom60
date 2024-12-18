<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Quên mật khẩu</title>

    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css"/>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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
<!-- Form -->

<div class="content">
    <!-- Forgot password -->
    <c:set var="emailNotExist" value="${requestScope.emailNotExist}"/>
    <div class="form-wrapper sign-up" id="forget-pw__form">
        <a href="./index.jsp" class="back-cta">
            <i class="fa-solid fa-chevron-left"></i>
            Trở về trang chủ
        </a>
        <form action="./forgetPassword" class="form" autocomplete="off">
            <p class="title">Quên mật khẩu</p>
            <c:if test="${emailNotExist != null}">
                <p class=" text-danger">
                        ${emailNotExist}
                </p>
            </c:if>
            <div class="form-grp">
                <label for="username__forget">Email</label>
                <div class="user-input">
                    <i class="fa-solid fa-user ic"></i>
                    <input
                            id="username__forget"
                            name="email__forget"
                            type="text"
                            placeholder="Nhập email ... "
                            required
                    />
                </div>
                <div class="errorMessage">

                </div>
            </div>

            <button type="submit" class="submit sub-btn" id="forget__pw">
                XÁC NHẬN
            </button>

            <div class="footer">
                <span>Quay trở lại đăng nhập</span>
                <button type="button" class="link login-in-pw" id="">
                    <a class="link" href="./logIn.jsp">Đăng Nhập</a>
                </button>
            </div>
        </form>
    </div>
</div>
<script src="js/validation.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        // validate cho form quên mật khẩu
        Validator({
            form: '#forget-pw__form',
            parentError: '.form-grp',
            errorSelector: '.errorMessage',
            rules: [
                Validator.isRequired('#username__forget'),
                Validator.isEmail('#username__forget')
            ]
        });
    });
</script>

</body>

</html>
