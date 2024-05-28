

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${listCC}" var="o">
                <li class="list-group-item text-white ${tag == o.id ? "active":""}"><a href="category?cid=${o.id}">${o.name}</a></li>
            </c:forEach>

        </ul>
    </div>
    <%--            Last Product đặt đây            --%>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Last product</div>
        <div class="card-body">
            <img class="img-fluid" src="${productLast.productImage}" />
            <h5 class="card-title">${productLast.productName}</h5>
            <p class="card-text">${productLast.description}</p>
            <p class="bloc_left_price">${productLast.productCost} $</p>
        </div>
    </div>
</div>