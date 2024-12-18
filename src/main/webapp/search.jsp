<%--
  Created by IntelliJ IDEA.
  User: PhatNT
  Date: 21-Jan-24
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header__search">
    <div class="search-product">
        <form action="search" method="post">
            <input type="text" name="txtSearch" id="searchInput" placeholder="Tìm kiếm sản phẩm" />
            <div id="searchResults" style="display: none;"></div>
            <button class="search-btn" type="submit">
                <i class="fa-solid fa-magnifying-glass search-ic"></i>
            </button>
        </form>
    </div>
</header>
