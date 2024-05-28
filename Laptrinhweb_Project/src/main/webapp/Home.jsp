<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
<%--    <script>window.location.href="product"</script>--%>
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="Left.jsp"></jsp:include>

        <div class="col-sm-9">
            <div class="row">
                <c:forEach items="${listP}" var="o">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="card">
                            <img class="card-img-top" src="${o.productImage}" alt="Card image cap">
                            <div class="card-body">
                                <h4 class="card-title show_txt"><a href="product?action=detail&pid=${o.id}" title="View Product">${o.productName}</a></h4>
                                <p class="card-text show_txt">${o.description}</p>
                                <p class="card-text show_txt"> Brand : ${o.brand}</p>
                                <p class="card-text show_txt"> Color : ${o.color}</p>
                                <p class="card-text show_txt"> Size  : ${o.size}</p>

                                <c:if test="${!sessionScope.account.isAdmin()}">
                                    <div class="row">
                                        <div class="col">
                                            <a href="order?action=addnow&productID=${o.id}&totalPrice=${o.productCost+5}" class="btn btn-danger btn-block">${o.productCost} $</a>
                                        </div>
                                        <div class="col">
                                            <a href="cart?action=add&productID=${o.id}" class="btn btn-success btn-block">Add to cart</a>
                                        </div>
                                    </div>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>


</body>
</html>

