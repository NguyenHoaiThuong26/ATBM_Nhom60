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

                    <!-- Form -->
                    <form class="form" method="post" action="bill">
                        <h2>Điền thông tin vào</h2>
                        <p>Vui lòng điền đầy đủ thông tin</p>
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

<%--                        <h2>Xác thực đơn hàng</h2>--%>
<%--                        <p>Vui lòng xác thực đơn hàng bằng cách nhập private key bên dưới.</p>--%>
<%--                        <div class = "row">--%>
<%--                            <div class="col-12">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label>Private key:<span>*</span></label>--%>
<%--                                    <input type="password" name="privateKey" placeholder=""--%>
<%--                                           required="required"/>--%>
<%--                                </div>--%>

<%--                                <div class="form-group">--%>
<%--                                    <label>Tải Private Key từ file:<span></span></label>--%>
<%--                                    <input type="file" name="privateKeyFile" accept=".txt"/>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>


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
<div id="privateKeyModal" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1000; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0px 4px 6px rgba(0,0,0,0.1);">
    <h3>Nhập Private Key</h3>
    <input type="text" id="privateKeyInput" name="privateKeyInput" placeholder="Private Key" style="width: 100%; padding: 10px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 4px;">
    <button id="submitPrivateKey" style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;">Xác nhận đặt hàng</button>
    <button id="closeModal" style="background-color: #f44336; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;">Đóng</button>
</div>
<div id="modalBackdrop" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 999;"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    // Modal trigger
    document.getElementById('continue-checkout').addEventListener('click', function () {
        // Show the modal when 'Continue Checkout' is clicked
        document.getElementById('privateKeyModal').style.display = 'block';
        document.getElementById('modalBackdrop').style.display = 'block';
    });

    // Close modal when 'Close' button is clicked
    document.getElementById('closeModal').addEventListener('click', function () {
        document.getElementById('privateKeyModal').style.display = 'none';
        document.getElementById('modalBackdrop').style.display = 'none';
    });

    // When 'Submit Private Key' button is clicked in modal
    document.getElementById('submitPrivateKey').addEventListener('click', function () {
        const privateKey = document.getElementById('privateKeyInput').value;

        if (privateKey) {
            // If private key is provided, close the modal
            document.getElementById('privateKeyModal').style.display = 'none';
            document.getElementById('modalBackdrop').style.display = 'none';

            // Trigger the AJAX request to submit the form data
            submitFormData(privateKey);
        } else {
            alert('Vui lòng nhập Private Key.');
        }
    });

    // Function to submit form data via AJAX
    function submitFormData(privateKey) {
        // Collect form data
        const name = $('input[name="name"]').val().trim();
        const phone = $('input[name="phone"]').val().trim();
        const address = $('input[name="address"]').val().trim();
        const payment = $('#COD').is(':checked') ? 'COD' : 'BANK';

        // Validate required fields
        if (name === '' || phone === '' || address === '') {
            alert('Vui lòng điền đầy đủ thông tin');
            return;
        }

        // Add private key to data
        const data = {
            name: name,
            phone: phone,
            address: address,
            payment: payment,
            privateKeyInput: privateKey  // Include private key
        };

        // Disable the button while processing
        $('#continue-checkout').prop('disabled', true).text('Đang xử lý...');

        // Send the data via AJAX
        $.ajax({
            url: '<%= request.getContextPath()%>/bill',  // Make sure this is the correct URL
            type: 'POST',
            data: data,
            success: function (response) {
                alert('Đơn hàng đã được xác nhận');
                // Redirect or show success message
            },
            error: function (error) {
                console.error('Lỗi khi gửi dữ liệu:', error);
                alert('Đã xảy ra lỗi trong quá trình đặt hàng. Vui lòng thử lại.');
            },
            complete: function () {
                // Re-enable the button after completion
                $('#continue-checkout').prop('disabled', false).text('Tiếp tục thanh toán');
            }
        });
    }

    // jQuery for checkbox style toggling
    $(document).ready(function () {
        $('input[type="checkbox"]').change(function () {
            if ($(this).is(':checked')) {
                $(this).parent("label").addClass("checked");
            } else {
                $(this).parent("label").removeClass("checked");
            }
        });

        // Handle payment method selection
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
    });
</script>

<</body>
</html>