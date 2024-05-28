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
    <title>Manager Order</title>
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
                    <h2>Manage <b>Order</b></h2>
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
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Phone</th>
                <th>Mail</th>
                <th>Delivery Address</th>
                <th>Order Date</th>
                <th>totalPrice</th>
                <th>Status</th>
                <%--                <th>Actions</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOrder}" var="o">
                <tr onclick="window.location='order?action=orderdetails&orderid=${o.id}'">
                    <td>${o.customer.id}</td>
                    <td>${o.customer.name}</td>
                    <td>${o.customer.phone}</td>
                    <td>${o.customer.mail}</td>
                    <td>${o.customer.deliveryAddress}</td>
                    <td>${o.dateOrder}</td>
                    <td>${o.totalPrice} $</td>
                    <td>${o.status}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>

    </div>
    <a href="home">
        <button type="button" class="btn btn-primary">Back to home</button>
    </a>
</div>


<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>