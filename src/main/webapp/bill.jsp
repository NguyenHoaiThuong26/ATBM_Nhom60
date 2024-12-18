<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="vi_VN"/>
<fmt:setBundle basename="java.text.resources"/>
<html>
<head>
    <title>Hóa Đơn của bạn</title>
    <style>
        .height {
            min-height: 200px;
        }

        .icon {
            font-size: 47px;
            color: #5CB85C;
        }

        .iconbig {
            font-size: 77px;
            color: #5CB85C;
        }

        .table > tbody > tr > .emptyrow {
            border-top: none;
        }

        .table > thead > tr > .emptyrow {
            border-bottom: none;
        }

        .table > tbody > tr > .highrow {
            border-top: 3px solid;
        }
    </style>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Chi tiết sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css"/>
    <link rel="stylesheet" href="assets/css/index.css"/>
    <link rel="stylesheet" href="assets/css/bill.css"/>
    <link rel="stylesheet" href="assets/css/bill2.css"/>
    <!-- <link rel="stylesheet" href="./assets/css/product.css"> -->

    <!-- embed fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

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
    <link rel="stylesheet" href="assets/css/style.css"/>
    <link rel="stylesheet" href="assets/css/productDetail.css"/>

    <!-- OWL CAROUSEL CSS -->
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css"/>
    <link
            rel="stylesheet"
            href="assets/css/owl.theme.default.min.css"
    />
    <link rel="stylesheet" href="assets/css/checkout.css">
</head>
<body>
<%--HEADER--%>
<c:import url="header.jsp"/>
<div class="clear"></div>
<section class="shop checkout section">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-12">
                <div class="checkout-form">
                    <h2>Điền thông tin vào</h2>
                    <p>Vui lòng điền đầy đủ thông tin</p>
                    <!-- Form -->
                    <form class="form" method="post" action="bill">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-12">
                                <div class="form-group">
                                    <label>Họ tên<span>*</span></label>
                                    <input type="text" name="name" value="${sessionScope.auth.fullName}" placeholder=""
                                           required="required">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-12">
                                <div class="form-group">
                                    <label>Số điện thoại<span>*</span></label>
                                    <input type="number" value="${sessionScope.auth.phone}"  name="phone" placeholder=""
                                           required="required"/>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <label>Địa chỉ<span>*</span></label>
                                    <input type="text" name="address" placeholder=""
                                           required="required"/>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!--/ End Form -->
                </div>
            </div>
            <div class="col-lg-4 col-12">
                <div class="order-details">
                    <!-- Order Widget -->
                    <div class="single-widget">
                        <h2>Tổng tiền</h2>
                        <div class="content">
                            <ul>
                                <li>Tổng hóa đơn<span><fmt:formatNumber value="${total}" type="currency"
                                                                        currencyCode="VND"/></span></li>
                                <li>(+) Shipping<span>Free</span></li>
                                <li class="last">Thành tiền<span><fmt:formatNumber value="${total}" type="currency"
                                                                                   currencyCode="VND"/></span></li>
                            </ul>
                        </div>
                    </div>
                    <!--/ End Order Widget -->
                    <!-- Order Widget -->
                    <div class="single-widget">
                        <h2>Hình thức thanh toán</h2>
                        <div class="content">
                            <div class="checkbox">
                                <label for="COD" class="check-out-cod checked"><input name="news" id="COD" type="checkbox" checked>
                                    Thanh toán khi nhận hàng</label>
                                <label for="BANK" class="check-out-bank"><input name="news" id="BANK" type="checkbox">
                                    Chuyển khoản</label>
                            </div>
                        </div>
                    </div>
                    <!--/ End Order Widget -->
                    <!-- Button Widget -->
                    <div class="single-widget get-button">
                        <div class="content">
                            <div class="button">
                                <button type="submit" id="continue-checkout">Tiếp tục</button>
                            </div>
                        </div>
                    </div>
                    <!--/ End Button Widget -->
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        // Sự kiện khi click vào input[type="checkbox"]
        $('input[type="checkbox"]').change(function () {
            if ($(this).is(':checked')) {
                $(this).parent("label").addClass("checked");
            } else {
                $(this).parent("label").removeClass("checked");
            }
        });

        // Sự kiện khi click vào label
        $('.check-out-cod').click(function () {
            $('#COD').prop('checked', true);
            $('#BANK').prop('checked', false);
            $(this).addClass("checked");
            $('.check-out-bank').removeClass("checked");
        });

        $('.check-out-bank').click(function () {
            $('#BANK').prop('checked', true);
            $('#COD').prop('checked', false);
            $(this).addClass("checked");
            $('.check-out-cod').removeClass("checked");
        });


        $('#continue-checkout').click(function () {
            if ($('#COD').is(':checked') || $('#BANK').is(':checked')) {
                const name = $('input[name="name"]').val();
                const phone = $('input[name="phone"]').val();
                const address = $('input[name="address"]').val();
                const payment = $('#COD').is(':checked') ? 'COD' : 'BANK';

                if(name == '' || phone == '' || address == ''){
                    alert('Vui lòng điền đầy đủ thông tin');
                    return;
                }

                const data = {
                    name: name,
                    phone: phone,
                    address: address,
                    payment: payment
                };

                $.ajax({
                    url: '<%= request.getContextPath()%>/bill',
                    type: 'POST',
                    data: data,
                    success: function (response) {
                        alert('Thêm vào giỏ hàng thành công');
                        // direct to cart page
                        window.location.href = '<%= request.getContextPath()%>/thank-you';
                    },
                    error: function (error) {
                    }
                });

                console.log(data);
            } else {
                alert("Vui lòng chọn hình thức thanh toán");
            }
        });
    });
</script>
</body>
</html>