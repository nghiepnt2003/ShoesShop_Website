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
    <title>Manager Customer</title>
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
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Customer</b></h2>
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
                <th>ID</th>
                <th>Account Name</th>
                <th>Password</th>
                <th>Customer Name</th>
                <th>Phone</th>
                <th>Mail</th>
                <th>Delivery Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listCus}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.account.userName}</td>
                    <td>${o.account.password}</td>
                    <td>${o.name}</td>
                    <td>${o.phone}</td>
                    <td>${o.mail}</td>
                    <td>${o.deliveryAddress}</td>

                    <td>
                        <a href="customer?action=loadCustomer&cusid=${o.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="customer?action=deleteCustomer&cusid=${o.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
<%--        <div class="clearfix">--%>
<%--            <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>--%>
<%--            <ul class="pagination">--%>
<%--                <li class="page-item disabled"><a href="#">Previous</a></li>--%>
<%--                <li class="page-item"><a href="#" class="page-link">1</a></li>--%>
<%--                <li class="page-item"><a href="#" class="page-link">2</a></li>--%>
<%--                <li class="page-item active"><a href="#" class="page-link">3</a></li>--%>
<%--                <li class="page-item"><a href="#" class="page-link">4</a></li>--%>
<%--                <li class="page-item"><a href="#" class="page-link">5</a></li>--%>
<%--                <li class="page-item"><a href="#" class="page-link">Next</a></li>--%>
<%--            </ul>--%>
<%--        </div>--%>
    </div>
    <a href="home"><button type="button" class="btn btn-primary">Back to home</button> </a>
</div>
<!-- Edit Modal HTML -->
<%--<div id="addEmployeeModal" class="modal fade">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <form action="add" method="post">--%>
<%--                <div class="modal-header">--%>
<%--                    <h4 class="modal-title">Add Product</h4>--%>
<%--                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Name</label>--%>
<%--                        <input name="name" type="text" class="form-control" required>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Brand</label>--%>
<%--                        <input name="brand" type="text" class="form-control" required>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Image</label>--%>
<%--                        <input name="image" type="text" class="form-control" required>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Color</label>--%>
<%--                        <input name="color" type="text" class="form-control" required>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Size</label>--%>
<%--                        <input name="size" type="text" class="form-control" required>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Price</label>--%>
<%--                        <input name="price" type="text" class="form-control" required>--%>
<%--                    </div>--%>
<%--                    &lt;%&ndash;                            <div class="form-group">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                <label>Title</label>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                <textarea name="title" class="form-control" required></textarea>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Description</label>--%>
<%--                        <textarea name="description" class="form-control" required></textarea>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Category</label>--%>
<%--                        <select name="category" class="form-select" aria-label="Default select example">--%>
<%--                            <c:forEach items="${listCC}" var="o">--%>
<%--                                <option value="${o.id}">${o.name}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                    </div>--%>

<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">--%>
<%--                    <input type="submit" class="btn btn-success" value="Add">--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>