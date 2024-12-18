<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="bean.ShoppingCart" %>
<%@ page import="bean.Item" %>
<%@ page import="bean.Product" %>
<%@ page import="java.util.Map" %>
<%@ page import="dao.ImageDAO" %>
<%@ page import="service.ImageService" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="service.ColorService" %>
<% List<Item> shoppingCart = (List<Item>) request.getSession().getAttribute("cart");
    String announce = "";
%>
<% if (shoppingCart == null || shoppingCart.isEmpty()) {
    announce = "Giỏ hàng bạn hiện đang trống.!";%>
<%
    } else {
        announce = "Giỏ hàng bạn hiện đang có " + shoppingCart.size() + " Sản phẩm.!";
    }
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>
        Giỏ Hàng của bạn
    </title>

    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css"/>
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/cart.css">
    <!-- <link rel="stylesheet" href="./assets/css/product.css"> -->

    <!-- embed fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>

    <!-- FONT GOOGLE -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;700&display=swap" rel="stylesheet">

    <!-- FONT AWRSOME -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">


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
    <link rel="stylesheet" href="assets/css/about.css"/>
</head>
<body>
<!-- HEADER -->
<c:import url="header.jsp"/>
<!-- CART INFORMATION -->
<section class="cart__details">
    <div data-view-id="breadcrumb__container" class="cart__return">
        <div class="cart__return__content">
            <div class="breadcrumb">
                <a class="breadcrumb__item br1" data-view-id="breadcrumb__item" data-view-index="0" href="index.jsp">
                    <span>Trang chủ</span>
                </a>
                <span class="icon icon__next">
                        <i class="fa fa-angle-right"></i>
                    </span>
                <a href="#" class="breadcrumb__item" data-view-id="breadcrumb__item" data-view-index="1">
                    <span title="Giỏ Hàng">Giỏ Hàng Của Bạn</span>
                </a>
            </div>
        </div>
    </div>

    <section class="cart__content">
        <div class="left__content">

            <h3><%=announce%>
            </h3>
            <div class="main-content">
                <div><h3>Thông tin chi tiết</h3></div>
                <div class="your__cart">
                    <table>
                        <tr>
                            <th>Lựa chọn</th>
                            <th>Mã SP</th>
                            <th>Tên SP</th>
                            <th>Ảnh</th>
                            <th>Màu sắc</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Tổng giá</th>
                        </tr>
                        <%
                            double total = 0;
                            double priceOfItem = 0;
                            List<Item> cart = (List<Item>) session.getAttribute("cart");
                            if (cart != null) {
                                for (int i = 0; i < cart.size(); i++) {
                                    Product item = cart.get(i).getProduct();
                                    int quantity = cart.get(i).getQuantity();
                                    priceOfItem = item.getTotalPrice() * quantity;
                                    total += priceOfItem;
                        %>
                        <tr>
                            <td>
                                <a href="<%= request.getContextPath() %>/cart?action=remove&id=<%= item.getId() %>"
                                   onclick="return confirm('Bạn có chắc muốn xóa sản phẩm?')">
                                    <button>Xóa</button>
                                </a>
                            </td>
                            <td><%= item.getId() %>
                            </td>
                            <td><%= item.getName() %>
                            </td>
                            <td>
                                <img src="<%= ImageService.getInstance().getImageByProductId(item.getId()).get(0).getLink() %>"
                                     width="80">
                            </td>
                            <td><%= cart.get(i).getColorName() %>
                            </td>

                            <td id="price<%= item.getId() %>"><%= formatCurrency(item.getTotalPrice()) %>
                            </td>
                            <td>
                                <div class="_grid">
                                    <button class="_btn _column product-subtract" data-pid="<%= item.getId() %>">&minus;</button>
                                    <div class="_column product-qty<%= item.getId() %>"><%= quantity %>
                                    </div>
                                    <button class="_btn _column product-plus" data-pid="<%= item.getId() %>">&plus;</button>
                                </div>
                            </td>
                            <td id="totalPriceOfProduct<%= item.getId() %>"><%= formatCurrency(priceOfItem) %>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        <tr>
                            <td colspan="6" align="right">Tổng tiền</td>
                            <td class="total"><%= formatCurrency(total) %>
                            </td>
                        </tr>
                    </table>
                    <br>

                </div>
                <button><a href="<%= request.getContextPath()%>/products">Tiếp tục mua sắm</a></button>
            </div>
        </div>
    </section>
    <section class="cart__content">
        <div class="right__content">
            <div class="box">
                <div class="box__order">
                    <div class="box__order__title">
                        <h3>Thông tin đơn hàng</h3>
                    </div>
                    <div class="box__order__totalPrice">
                        <p>
                            Tổng tiền:
                            <span class="total__price"><%= formatCurrency(total) %></span>
                        </p>
                    </div>
                    <div class="box__order__text">
                        <p>
                            Phí vận chuyển sẽ được tính ở trang thanh toán.
                            Bạn cũng có thể nhập mã giảm giá ở trang thanh toán.
                        </p>
                    </div>
                    <div class="box__order__active">
                        <form action="<%= request.getContextPath()%>/bill" method="get">
                            <!-- Include hidden input fields for order details -->
                            <input type="hidden" name="total" value="<%= total %>">
                            <!-- Add other necessary hidden fields -->

                            <button type="submit" class="btn__payment">
                                Thanh toán
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <!-- FOOTER -->
    <footer>
        <div class="footer__main">
            <div class="footer__items">
                <h3>Thông tin của hàng</h3>
                <nav>
                    <ul>
                        <li><a href="#">Giới thiệu cửa hàng</a></li>
                        <li><a href="#">Hệ thống showrom, đại lý</a></li>
                        <li><a href="#">Liên hệ / Góp ý</a></li>
                        <li><a href="#">Mua trả góp</a></li>
                        <li><a href="#">Chương trình khách hàng thân thiết</a></li>
                        <li><a href="#">Điều khoản sử dụng Website</a></li>
                        <li><a href="#">Tuyển dụng</a></li>
                    </ul>
                </nav>
            </div>
            <div class="footer__items">
                <h3>Hướng dẫn chung</h3>
                <nav>
                    <ul>
                        <li><a href="#">Giao hàng - Đổi trả</a></li>
                        <li><a href="#">Hướng dẫn mua hàng</a></li>
                        <li><a href="#">Thanh toán và bảo mật</a></li>
                        <li><a href="#">Chính sách bảo hành</a></li>
                        <li><a href="#">Bảo trì đàn piano</a></li>
                        <li><a href="#">Kích hoạt bảo hành</a></li>
                    </ul>
                </nav>
            </div>
            <div class="footer__items">
                <h3>Hỗ trợ khách hàng</h3>
                <p>Gọi mua hàng: <span>1800 6715 </span> (Miễn Phí) </p>
                <p>Khiếu nại, Bảo hành: <span>028710 88 333</span></p>
                <p>Thời gian phục vụ: 8h-22h</p>
                <p>Email: <a href="">info@gteam49.vn</a></p>
            </div>
            <div class="footer__items">
                <div class="about__us">
                    <h3>Mạng xã hội</h3>
                    <div class="about__us__icon">
                        <i class="fa-brands fa-square-facebook" style="color: #0080ff;"></i>
                        <i class="fab fa-youtube-square" style="color: #ff0000;"></i>
                        <i class="fab fa-twitter-square" style="color: #50a3f6;"></i>
                    </div>
                </div>
                <div class="about__us">
                    <h3>Hệ thống Website</h3>
                    <nav>
                        <ul>
                            <li><a href="#">Team 49 Shop</a></li>
                            <li><a href="#">Team 49 Music University</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="footer__items">
                <h3>Công ty Cổ Phần TM-DV-SX Team 49</h3>
                <div class="desc">
                    <p>GPKD số 0304757232 do sở Kế hoạch Đầu tư TPHCM cấp ngày 01/01/2007 </p>
                    <p>Địa chỉ: 386 CMT8, P.10, Q.3, TPHCM</p>
                    <p>Điện thoại: <span>1800 6715</span></p>
                    <p>Hotline: <span>028710 88 333</span></p>
                    <p>Email: <a href="#">info@team49.vn</a></p>
                </div>
            </div>
            <div class="footer__items">
                <div class="payment">
                    <h3>cách thức thanh toán</h3>
                    <a href="#">
                        <img src="https://vietthuong.vn/assets/frontend/images/thanhtoan.png"
                             alt="Phuong_Thuc_thanh_toan">
                    </a>
                </div>
                <div class="certification">
                    <h3>Chứng nhận</h3>
                    <a href="">
                        <img src="https://vietthuong.vn/assets/frontend/images/cntb.png" alt="">
                    </a>
                </div>
            </div>
        </div>
        <div class="footer__copyright">
            <p>&copy: 2023 Đồ án Lập Trình Web HK5. | Design by <span>Team 49</span></p>
        </div>
    </footer>
</section>
<!-- MAIN JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/product.js"></script>
<script src="js/paging.js"></script>

<%!
    private String formatCurrency(double totalPrice) {
        Locale localeVN = new Locale("vi", "VN");
        int price = (int) totalPrice;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        return currencyFormatter.format(price);
    }
%>
<script>
    $(document).ready(function () {
        // Xử lý khi nút tăng được nhấn
        $('.product-plus').on('click', function () {
            var productId = $(this).data('pid');
            updateQuantity(productId, 1);
        });

        // Xử lý khi nút giảm được nhấn
        $('.product-subtract').on('click', function () {
            var productId = $(this).data('pid');
            updateQuantity(productId, -1);
        });

        function updateQuantity(productId, amount) {
            var currentQuantity = parseInt($('.product-qty' + productId).text());
            var newQuantity = currentQuantity + amount;

            // Gửi yêu cầu Ajax để cập nhật số lượng
            $.ajax({
                url: '<%= request.getContextPath()%>/cart?action=update',
                type: 'GET',
                data: {
                    productId: productId,
                    quantity: newQuantity
                },
                success: function (response) {
                    window.location.reload();
                },
                error: function (error) {
                    console.error('Error:', error);
                }
            });
        }
    });
</script>
</body>
</html>