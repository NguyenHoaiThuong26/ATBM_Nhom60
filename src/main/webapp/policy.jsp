<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Our Project 49</title>

        <!-- reset CSS -->
        <link rel="stylesheet" href="assets/css/reset.css" />
        <link rel="stylesheet" href="assets/css/index.css" />
        <link rel="stylesheet" href="assets/css/policy.css" />

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
        <link rel="stylesheet" href="assets/css/style.css" />

        <!-- OWL CAROUSEL CSS -->
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="assets/css/owl.theme.default.min.css" />
    </head>
    <body>
        <!-- HEADER -->
        <c:import url="header.jsp"/>

        <section class="policy">
            <div class="policy-directory">
                <div class="policy-title">Chính sách</div>
                <ul class="policy-list">
                    <li class="policy-item return">Chính sách đổi trả</li>
                    <li class="policy-item confidentiality">
                        Chính sách bảo mật
                    </li>
                </ul>
            </div>
            <div class="policy-content policy-return active">
                <p class="title">Chính sách đổi trả</p>
                <div class="desc">
                    <p class="desc-title">1. Điều kiện đổi trả</p>
                    <div class="desc-list">
                        <p>
                            Quý Khách hàng cần kiểm tra tình trạng hàng hóa và
                            có thể đổi hàng/ trả lại hàng ngay tại thời điểm
                            giao/nhận hàng trong những trường hợp sau
                        </p>
                        <ul class="desc-item">
                            <li>
                                Hàng không đúng chủng loại, mẫu mã trong đơn
                                hàng đã đặt hoặc như trên website tại thời điểm
                                đặt hàng
                            </li>
                            <li>
                                Không đủ số lượng, không đủ bộ như trong đơn
                                hàng.
                            </li>
                            <li>
                                Tình trạng bên ngoài bị ảnh hưởng như rách bao
                                bì, bong tróc, bể vỡ…
                            </li>
                        </ul>
                        <p>
                            Khách hàng có trách nhiệm trình giấy tờ liên quan
                            chứng minh sự thiếu sót trên để hoàn thành việc hoàn
                            trả/đổi trả hàng hóa.
                        </p>
                    </div>

                    <p class="desc-title">
                        2. Quy định về thời gian thông báo và gửi sản phẩm đổi
                        trả
                    </p>
                    <div class="desc-list">
                        <p>
                            Quý Khách hàng cần kiểm tra tình trạng hàng hóa và
                            có thể đổi hàng/ trả lại hàng ngay tại thời điểm
                            giao/nhận hàng trong những trường hợp sau
                        </p>
                        <ul class="desc-item">
                            <li>
                                <b>Thời gian thông báo đổi trả:</b> trong vòng
                                48h kể từ khi nhận sản phẩm đối với trường hợp
                                sản phẩm thiếu phụ kiện, quà tặng hoặc bể vỡ.
                            </li>
                            <li>
                                <b>Thời gian gửi chuyển trả sản phẩm:</b> trong
                                vòng 14 ngày kể từ khi nhận sản phẩm.
                            </li>
                            <li>
                                <b>Địa điểm đổi trả sản phẩm:</b> Khách hàng có
                                thể mang hàng trực tiếp đến văn phòng/ cửa hàng
                                của chúng tôi hoặc chuyển qua đường bưu điện.
                            </li>
                        </ul>
                        <p>
                            Trong trường hợp Quý Khách hàng có ý kiến đóng
                            góp/khiếu nại liên quan đến chất lượng sản phẩm, Quý
                            Khách hàng vui lòng liên hệ đường dây chăm sóc khách
                            hàng của chúng tôi.
                        </p>
                    </div>
                </div>
            </div>
            <div class="policy-content policy-confidentiality">
                <p class="title">Chính sách bảo mật</p>
                <div class="desc">
                    <div class="desc-list">
                        <p>
                            Chính sách bảo mật này nhằm giúp Quý khách hiểu về
                            cách website thu thập và sử dụng thông tin cá nhân
                            của mình thông qua việc sử dụng trang web, bao gồm
                            mọi thông tin có thể cung cấp thông qua trang web
                            khi Quý khách đăng ký tài khoản, đăng ký nhận thông
                            tin liên lạc từ chúng tôi, hoặc khi Quý khách mua
                            sản phẩm, dịch vụ, yêu cầu thêm thông tin dịch vụ từ
                            chúng tôi.
                        </p>
                    </div>
                    <div class="desc-list">
                        <p>
                            Chúng tôi sử dụng thông tin cá nhân của Quý khách để
                            liên lạc khi cần thiết liên quan đến việc Quý khách
                            sử dụng website của chúng tôi, để trả lời các câu
                            hỏi hoặc gửi tài liệu và thông tin Quý khách yêu
                            cầu.
                        </p>
                    </div>
                    <div class="desc-list">
                        <p>
                            Trang web của chúng tôi coi trọng việc bảo mật thông
                            tin và sử dụng các biện pháp tốt nhất để bảo vệ
                            thông tin cũng như việc thanh toán của khách hàng.
                        </p>
                    </div>
                    <div class="desc-list">
                        <p>
                            Mọi thông tin giao dịch sẽ được bảo mật ngoại trừ
                            trong trường hợp cơ quan pháp luật yêu cầu.
                        </p>
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

        <!-- MAIN JS -->
        <script src="js/policy.js"></script>
    </body>
</html>
