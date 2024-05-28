<%--
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>EditCustomer</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    <style>
        img{
            width: 200px;
            height: 120px;
        }
    </style>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Edit <b>Customer</b></h2>
                </div>
                <div class="col-sm-6">
                </div>
            </div>
        </div>
    </div>
    <div id="editEmployeeModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="customer?action=editCustomer" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Customer</h4>
                        <a href="managecustomer">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </a>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>ID</label>
                            <input value="${detail.id}" name="id" type="text" class="form-control" readonly required>
                        </div>
                        <div class="form-group">
                            <label>Account name</label>
                            <input value="${detail.account.userName}" name="username" type="text" class="form-control" readonly required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input value="${detail.account.password}" name="password" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Customer name</label>
                            <input value="${detail.name}" name="name" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input value="${detail.phone}" name="phone" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Mail</label>
                            <input value="${detail.mail}" name="mail" type="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Delivery Address</label>
                            <input value="${detail.deliveryAddress}" name="deliveryAddress" type="text" class="form-control" required>
                        </div>
                        <%--                                <div class="form-group">--%>
                        <%--                                    <label>Title</label>--%>
                        <%--                                    <textarea name="title" class="form-control" required>${detail.title}</textarea>--%>
                        <%--                                </div>--%>

<%--                        <div class="form-group">--%>
<%--                            <label>Category</label>--%>
<%--                            <select name="category" class="form-select" aria-label="Default select example">--%>
<%--                                <c:forEach items="${listCC}" var="o">--%>
<%--                                    <option ${detail.category.id == o.id ? 'selected' : ''} value="${o.id}">${o.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>

                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-success" value="Edit">
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>


<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>