<%--
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manager OrderDetail</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    <style>
        img {
            width: 200px;
            height: 120px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>OrderDetail</b></h2>
                </div>
                <%--                <div class="col-sm-6">--%>
                <%--                    <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>--%>
                <%--                    &lt;%&ndash;                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						&ndash;%&gt;--%>
                <%--                </div>--%>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Product Name</th>
                <th>Product Image</th>
                <th>Price</th>
                <th>Brand</th>
                <th>Color</th>
                <th>Size</th>
                <th>Quantity</th>
                <th>totalPrice</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOrderDetails}" var="o">
                <tr>
                    <td>${o.productName}</td>
                    <td>
                        <img src="${o.productImage}">
                    </td>
                    <td>${o.productCost} $</td>
                    <td>${o.brand}</td>
                    <td>${o.color}</td>
                    <td>${o.size}</td>
                    <td>${o.quantity}</td>
                    <td>${o.productCost}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="container " style="display: flex">
    <a href="home">
        <button type="button" class="btn btn-primary">Back to home</button>
    </a>
    <c:if test="${sessionScope.account.isAdmin()}">
        <c:if test="${order.status == 'Processing'}">
            <a style="margin-left: auto;width: 300px" href="order?action=solveOrder&orderID=${order.id}" class="btn btn-success btn-flex">Solve Order</a>
        </c:if>
        <c:if test="${order.status == 'Processed'}">
            <a style="margin-left: auto;width: 300px;cursor: default;opacity: 0.5" href="#" class="btn btn-success btn-flex">Solve Order</a>
        </c:if>
    </c:if>

</div>



<script>


</script>

<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>