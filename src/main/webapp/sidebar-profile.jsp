<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="profile-function">
    <!-- .bhbbJ -->
    <!-- .jIFHQL -->
    <div class="profile-function-list-container">
        <ul class="profile-function-list">
            <!--.fAkTRM -->
            <li>
                <a class="is-active" href="profile.jsp">
                    <i class="fa fa-user"></i>
                    <span>Thông tin tài khoản</span>
                </a>
            </li>
            <li>
                <a class="" href="/customer/notification">
                    <i class="fa fa-bell"></i>
                    <span>Thông báo của tôi</span>
                </a>
            </li>
            <li>
                <a class="" href="<%= request.getContextPath()%>/history-bill">
                    <i class="fa fa-list-alt"></i>
                    <span>Quản lý đơn hàng</span>
                </a>
            </li>
            <li>
                <a class="" href="/return-tracking/history">
                    <i class="fa fa-box"></i>
                    <span>Quản lý đổi trả</span>
                </a>
            </li>
            <li>
                <a class="" href="/customer/address">
                    <i class="fa fa-map-marker-alt"></i>
                    <span>Sổ địa chỉ</span>
                </a>
            </li>
            <li>
                <a class="" href="/customer/paymentcard">
                    <i class="fa fa-money-check"></i>
                    <span>Thông tin thanh toán </span>
                </a>
            </li>
            <li>
                <a class="" href="/review-hub">
                    <i class="fa fa-thumbs-up"></i>
                    <span>Đánh giá sản phẩm</span>
                </a>
            </li>
        </ul>
    </div>
</div>