<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">Shoes</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${sessionScope.account.isAdmin()}">
                    <li class="nav-item">
                        <a class="nav-link" href="managecustomer">Manager Customer </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manageproduct">Manager Product</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="order?action=manage">Manager Order</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.account.isAdmin() == false}">
                    <li class="nav-item">
                        <a class="nav-link" href="profile?action=loadProfile">My profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="order?action=myOrder">My Order</a>
                    </li>
                </c:if>


                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Hello ${sessionScope.account.userName}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                </c:if>

            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <c:if test="${!sessionScope.account.isAdmin()}">
                    <a class="btn btn-success btn-sm ml-3" href="Cart.jsp">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <c:if test="${sessionScope.account != null}">
                            <span class="badge badge-light">
                                    ${cart.countCartLine()}
                            </span>
                        </c:if>
                    </a>
                </c:if>
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">SHOES STORE</h1>
        <p class="lead text-muted mb-0">Chinh Phục Đỉnh Cao Thời Trang: Những Mẫu Sneaker Luxury Hàng Đầu</p>
    </div>
</section>
<!--end of menu-->
