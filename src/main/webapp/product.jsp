<%@ page import="java.util.List" %>
<%@ page import="bean.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Product" %>
<%@ page import="service.ImageService" %>
<%@ page import="bean.Brand" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    if(categories == null) categories = new ArrayList<>();

    List<Product> products = (List<Product>) request.getAttribute("products");String category = request.getParameter("category");
    String brand = request.getParameter("brands");
    String minPricePara = request.getParameter("minPrice");
    String maxPricePara = request.getParameter("maxPrice");
    String azorza = request.getParameter("AZorZA");
    String images = request.getParameter("images");
    if (products == null) products = new ArrayList<>();

    List<Brand> brands = (List<Brand>) request.getAttribute("brands");
    if (brands == null) brands = new ArrayList<>();

//
%>


<html lang="en">
<head>
    <jsp:useBean id="imageService" class="service.ImageService" scope="session"/>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sản phẩm</title>

    <!-- reset CSS -->
    <link rel="stylesheet" href="assets/css/reset.css" />

    <!-- embed fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

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
    <link rel="stylesheet" href="assets/css/index.css" />
    <link rel="stylesheet" href="assets/css/style.css" />
    <link rel="stylesheet" href="assets/css/product.css" />

    <!-- OWL CAROUSEL CSS -->
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css" />
    <link
            rel="stylesheet"
            href="assets/css/owl.theme.default.min.css"

    />
</head>
<body>
<!-- HEADER -->
<c:import url="header.jsp"/>
<div class="search-product">
    <%--            <form action="search?indexPage=1" method="post">--%>
    <%--                <input type="text" name="txtSearch" id="searchInput" placeholder="Tìm kiếm sản phẩm" />--%>
    <%--                <div id="searchResults" style="display: none;"></div>--%>
    <%--                <button class="search-btn" type="submit">--%>
    <%--                    <i class="fa-solid fa-magnifying-glass search-ic"></i>--%>
    <%--                </button>--%>
    <%--            </form>--%>
    <form action="search?indexPage=${1}" method="post">
        <input type="text" name="txtSearch" id="searchInput" placeholder="Tìm kiếm sản phẩm" />
        <!-- Thêm hidden input để giữ giá trị txtSearch khi chưa ấn submit -->
        <input type="hidden" name="hiddenSearch" value="${empty param.txtSearch ? '' : param.txtSearch}" />
        <div id="searchResults" style="display: none;"></div>
        <button class="search-btn" type="submit">
            <i class="fa-solid fa-magnifying-glass search-ic"></i>
        </button>
    </form>
</div>
<!-- PRODUCT -->

<section class="product-wrapper">
    <div class="hot-product">
        <img
                class="product-img"
                src="assets/img/product/sp1.jpg"
                alt=""
        />
        <div class="cover"></div>
    </div>
    <section class="product">
        <div class="product-side-bar">
            <div class="product-directory">
                <div class="directory-title">Danh mục</div>
                <div class="directory__list">
                    <ul class="directory__item">
                        <%  if (categories != null) {
                            for (Category c : categories) { %>
                        <li class="directory__gerne">
                            <a href="products?category=<%= c.getName() %>&txtSearch=${param.txtSearch}" class="gerne-link">
                                <%--@declare id="brands"--%><label for="brands"><%= c.getName() %></label>
                            </a>
                        </li>
                        <%  }
                        } %>
                    </ul>
                </div>
            </div>
        </div>
        <div class="product-list__wrapper">
            <div class="box_order">
                <label>Tìm theo</label>
                <div class="box_order_item">
                    <label class="sort_pricess"
                    >Giá <i class="fa fa-caret-down caret"></i
                    ></label>
                    <div id="sort_pricess" class="box_s hidden">
                        <div class="box_ss">
                            <ul>
                                <li>
                                    <a href="products?minPrice=0&&maxPrice=3000000">
                                        <label>Dưới 3 Triệu</label>
                                    </a>
                                </li>
                                <li>
                                    <a href="products?minPrice=3000000&&maxPrice=5000000">
                                        <label>3 - 5 triệu</label>
                                    </a>
                                </li>
                                <li>
                                    <a href="products?minPrice=5000000&&maxPrice=10000000">
                                        <label>5 - 10 triệu</label>
                                    </a>
                                </li>
                                <li>
                                    <a href="products?minPrice=10000000&&maxPrice=20000000">
                                        <label>10 - 20 triệu</label>
                                    </a>

                                </li>
                                <li>
                                    <a href="products?minPrice=20000000&&maxPrice=40000000">
                                        <label>20 - 40 triệu</label>
                                    </a>
                                </li>
                                <li>
                                    <a href="products?minPrice=40000000&&maxPrice=100000000">
                                        <label>40 - 100 triệu</label>
                                    </a>
                                </li>
                                <li>
                                    <a href="products?minPrice=100000000&&maxPrice=300000000">
                                        <label>100 - 300 triệu</label>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="box_order_item">
                    <a href="products?discounts=discount">
                        <label class="sort_feature">Khuyến mãi</label>

                    </a>
                </div>

                <div class="box_order_item">
                    <label class="sort_manufacturer"
                    >Thương hiệu
                        <i class="fa fa-caret-down caret"></i>
                    </label>
                    <div id="sort_manufacturer" class="box_s hidden">
                        <div class="box_ss">
                            <ul>
                                <%  if (brands != null) {
                                    for (Brand b : brands) { %>
                                <li class="directory__gerne">
                                    <a href="products?brands=<%= b.getName() %>&txtSearch=${param.txtSearch}" class="gerne-link">
                                        <%--@declare id="brands"--%><label for="brands"><%= b.getName() %></label>
                                    </a>
                                </li>
                                <%  }
                                } %>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="box_order_item pull-right">
                    <label label class="sort_order"
                    >Sắp xếp theo
                        <i class="fa fa-caret-down caret"></i>
                    </label>
                    <div
                            id="sort_order"
                            class="box_s box_orderss hidden"
                    >
                        <div class="box_ss">
                            <ul>
                                <li>
                                    <a href="products?AZorZA=ASC">
                                        <label>Giá thấp đến cao</label>
                                    </a>
                                </li>
                                <li>
                                    <a href="products?AZorZA=DESC">
                                        <label>Giá cao xuống thấp</label>
                                    </a>
                                </li>

                                <li>
                                    <a href="products?AZorZA=ASC">
                                        <%--@declare id="azorza"--%><label>Tên A-Z</label>
                                    </a>
                                </li>

                                <li>
                                    <a href="products?AZorZA=DESC">
                                        <label>Tên Z-A</label>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list">
                <c:choose>
                    <c:when test="${not empty products}">
                        <!-- Hiển thị danh sách sản phẩm tìm kiếm -->
                        <c:forEach var="item" items="${products}">
                            <div class="item">
                                <a href="productdetails?selectedProductId=${item.id}" class="img" onclick="redirectToProductDetail('${item.id}')">
                                    <img src="${imageService.getImageByProductId(item.id).get(0).link}"/>
                                </a>
                                <div class="item_content">
                                    <a href="" class="title">${item.name}</a>
                                    <div class="desc">${item.description}</div>
                                    <div class="price">
                                        <fmt:formatNumber type="currency" value="${item.totalPrice}" currencySymbol="" currencyCode="VND" var="formattedCurrency" />
                                            ${formattedCurrency}
                                    </div>
                                    <form action="/cart" method="get">
                                        <input type="hidden" name="productId" value="${item.id}">
                                        <c:url var="link" value="cart">
                                            <c:param name="action" value="buy"></c:param>
                                            <c:param name="id" value="${item.id}"></c:param>
                                        </c:url>
                                        <a href="productdetails?selectedProductId=${item.id}">
                                            <!-- Change the text to "Xem chi tiết" -->
                                            <div class="add">Xem chi tiết</div>
                                        </a>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty param.hiddenSearch}">
                                <!-- Hiển thị dữ liệu tìm kiếm sau khi submit -->
                                <c:set var="searchText" value="${param.hiddenSearch}" />
                                <c:set var="searchResults" value="${yourSearchMethod(searchText)}" />
                                <c:forEach var="item" items="${searchResults}">
                                    <div class="item">
                                        <a href="productdetails?selectedProductId=${item.id}" class="img" onclick="redirectToProductDetail('${item.id}')">
                                            <img src="${imageService.getImageByProductId(item.id).get(0).link}"/>
                                        </a>
                                        <div class="item_content">
                                            <a href="" class="title">${item.name}</a>
                                            <div class="desc">${item.description}</div>
                                            <div class="price">
                                                <fmt:formatNumber type="currency" value="${item.totalPrice}" currencySymbol="" currencyCode="VND" var="formattedCurrency" />
                                                    ${formattedCurrency}
                                            </div>
                                            <form action="/cart" method="get">
                                                <input type="hidden" name="productId" value="${item.id}">
                                                <c:url var="link" value="cart">
                                                    <c:param name="action" value="buy"></c:param>
                                                    <c:param name="id" value="${item.id}"></c:param>
                                                </c:url>
                                                <a href="${link}">
                                                    <div class="add">Thêm vào giỏ hàng</div>
                                                </a>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <!-- Hiển thị dữ liệu trước khi submit -->
                                <c:forEach var="item" items="${requestScope.products}">
                                    <div class="item">
                                        <a href="productdetails?selectedProductId=${item.id}" class="img" onclick="redirectToProductDetail('${item.id}')">
                                            <img src="${imageService.getImageByProductId(item.id).get(0).link}"/>
                                        </a>
                                        <div class="item_content">
                                            <a href="" class="title">${item.name}</a>
                                            <div class="desc">${item.description}</div>
                                            <div class="price">
                                                <fmt:formatNumber type="currency" value="${item.totalPrice}" currencySymbol="" currencyCode="VND" var="formattedCurrency" />
                                                    ${formattedCurrency}
                                            </div>
                                            <form action="/cart" method="get">
                                                <input type="hidden" name="productId" value="${item.id}">
                                                <c:url var="link" value="cart">
                                                    <c:param name="action" value="buy"></c:param>
                                                    <c:param name="id" value="${item.id}"></c:param>
                                                </c:url>
                                                <a href="${link}">
                                                    <div class="add">Thêm vào giỏ hàng</div>
                                                </a>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
            <%--                    Phân trang--%>
            <div class="listPage">
                <c:forEach begin="1" end="${endPage}" var="i">\ư
                    <lí><a href="search?index=${i}&&txtSearch=${txtSearch}>${i}</a></lí>
                </c:forEach>
                    </div>
                </div>
            </section>
        </section>
        <!-- FEEDBACK -->
        <section class="feedback">
                    <h2>Phản hồi của khách hàng.</h2>
                    <div class="feedback__content">
                        <div class="owl-carousel owl-theme">
                            <div class="item">
                                <p>
                                    <i class="fa fa-quote-left"></i> Lorem ipsum dolor
                                    sit amet consectetur adipisicing elit. Nihil facilis
                                    aspernatur temporibus magni culpa beatae repellat
                                    delectus accusantium explicabo veniam?
                                    <i class="fa fa-quote-right"></i>
                                </p>
                                <p class="feedback__name">GIANG</p>
                            </div>
                            <div class="item">
                                <p>
                                    <i class="fa fa-quote-left"></i> Lorem ipsum dolor
                                    sit amet consectetur adipisicing elit. Nihil facilis
                                    aspernatur temporibus magni culpa beatae repellat
                                    delectus accusantium explicabo veniam?
                                    <i class="fa fa-quote-right"></i>
                                </p>
                                <p class="feedback__name">DAI</p>
                            </div>
                            <div class="item">
                                <p>
                                    <i class="fa fa-quote-left"></i> Lorem ipsum dolor
                                    sit amet consectetur adipisicing elit. Nihil facilis
                                    aspernatur temporibus magni culpa beatae repellat
                                    delectus accusantium explicabo veniam?
                                    <i class="fa fa-quote-right"></i>
                                </p>
                                <p class="feedback__name">THUONG</p>
                            </div>
                        </div>
                    </div>
    </section>
    <!-- CONTACT -->
    <section class="contact">
        <h2>Liên Hệ</h2>
        <div class="contact__form">
            <form action="">
                <input type="text" placeholder="Tên" />
                <input type="text" placeholder="Họ" />
                <input type="email" placeholder="Email" />
                <input type="tel" placeholder="SĐT" />
                <textarea
                        name=""
                        id=""
                        cols="30"
                        rows="10"
                        placeholder="Message"
                ></textarea>
                <button type="submit">Send</button>
            </form>
        </div>
        <div class="contect__map"></div>
        <div class="contact__info">
            <div class="contact__items">
                <i class="fa fa-home"></i>
                <p>Lorem ipsum dolor sit amet.</p>
                <p>Lorem ipsum dolor sit amet.</p>
            </div>
            <div class="contact__items">
                <i class="fa fa-phone"></i>
                <p>Lorem ipsum dolor sit amet.</p>
                <p>Lorem ipsum dolor sit amet.</p>
            </div>
            <div class="contact__items">
                <i class="fa fa-envelope"></i>
                <p>Lorem ipsum dolor sit amet.</p>
                <p>Lorem ipsum dolor sit amet.</p>
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
                        <li>
                            <a href="#"
                            >Chương trình khách hàng thân thiết</a
                            >
                        </li>
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
                <p>Gọi mua hàng: <span>1800 6715 </span> (Miễn Phí)</p>
                <p>Khiếu nại, Bảo hành: <span>028710 88 333</span></p>
                <p>Thời gian phục vụ: 8h-22h</p>
                <p>Email: <a href="">info@gteam49.vn</a></p>
            </div>
            <div class="footer__items">
                <div class="about__us">
                    <h3>Mạng xã hội</h3>
                    <div class="about__us__icon">
                        <i
                                class="fa-brands fa-square-facebook"
                                style="color: #0080ff"
                        ></i>
                        <i
                                class="fab fa-youtube-square"
                                style="color: #ff0000"
                        ></i>
                        <i
                                class="fab fa-twitter-square"
                                style="color: #50a3f6"
                        ></i>
                    </div>
                </div>
                <div class="about__us">
                    <h3>Hệ thống Website</h3>
                    <nav>
                        <ul>
                            <li><a href="#">Team 49 Shop</a></li>
                            <li>
                                <a href="#">Team 49 Music University</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="footer__items">
                <h3>Công ty Cổ Phần TM-DV-SX Team 49</h3>
                <div class="desc">
                    <p>
                        GPKD số 0304757232 do sở Kế hoạch Đầu tư TPHCM cấp
                        ngày 01/01/2007
                    </p>
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
                        <img
                                src="https://vietthuong.vn/assets/frontend/images/thanhtoan.png"
                                alt="Phuong_Thuc_thanh_toan"
                        />
                    </a>
                </div>
                <div class="certification">
                    <h3>Chứng nhận</h3>
                    <a href="">
                        <img
                                src="https://vietthuong.vn/assets/frontend/images/cntb.png"
                                alt=""
                        />
                    </a>
                </div>
            </div>
        </div>
        <div class="footer__copyright">
            <p>
                &copy: 2023 Đồ án Lập Trình Web HK5. | Design by
                <span>Team 49</span>
            </p>
        </div>
    </footer>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- MAIN JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/product.js"></script>
    <script src="js/log.js"></script>
    <%--        <script src="js/paging.js"></script>--%>

    <!-- OWL CAROUSEL JS -->
    <script src="js/owl.carousel.min.js"></script>
    <script>
        $(".owl-carousel").owlCarousel({
            loop: true,
            nav: false,
            autoplay: true,
            autoplayTimeout: 3000,
            autoplayHoverPause: true,
            responsive: {
                0: {
                    items: 1,
                },
            },
        });
    </script>
</body>
</html>