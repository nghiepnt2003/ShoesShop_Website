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
    <title>Manager Product</title>
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
                    <h2>Manage <b>Product</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                        <span>Add New Product</span></a>
                    <%--                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						--%>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Image</th>
                <th>Price</th>
                <th>Brand</th>
                <th>Color</th>
                <th>Size</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.productName}</td>
                    <td>
                        <img src="${o.productImage}">
                    </td>
                    <td>${o.productCost} $</td>
                    <td>${o.brand}</td>
                    <td>${o.color}</td>
                    <td>${o.size}</td>
                    <td>
                        <a href="product?action=loadproduct&pid=${o.id}" class="edit" data-toggle="modal"><i class="material-icons"
                                                                                              data-toggle="tooltip"
                                                                                              title="Edit">&#xE254;</i></a>
                        <a href="product?action=delete&pid=${o.id}" class="delete" data-toggle="modal"><i
                                class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="clearfix">
            <div class="hint-text">Showing <b>${recordsPerPage}</b> out of <b>${totalProducts}</b> entries</div>
            <ul class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${currentPage == i? "active":""}">
                        <a href="manageproduct?page=${i}" onclick="selectPage(this)">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <a href="home">
        <button type="button" class="btn btn-primary">Back to home</button>
    </a>
</div>
<!-- Edit Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="product?action=add" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add Product</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input name="name" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Brand</label>
                        <input name="brand" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Image</label>
                        <input name="image" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Color</label>
                        <input name="color" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Size</label>
                        <input name="size" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input name="price" type="text" class="form-control" required>
                    </div>
                    <%--                            <div class="form-group">--%>
                    <%--                                <label>Title</label>--%>
                    <%--                                <textarea name="title" class="form-control" required></textarea>--%>
                    <%--                            </div>--%>
                    <div class="form-group">
                        <label>Description</label>
                        <textarea name="description" class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Category</label>
                        <select name="category" class="form-select" aria-label="Default select example">
                            <c:forEach items="${listCC}" var="o">
                                <option value="${o.id}">${o.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>


<script src="js/manager.js" type="text/javascript"></script>
<script>
    function selectPage(element) {
        // Loại bỏ lớp 'active' từ tất cả các liên kết trang
        const pages = document.querySelectorAll("#pagination a");
        pages.forEach(page => page.classList.remove("active"));

        // Thêm lớp 'selected' cho trang được chọn
        element.classList.add("active");
    }
</script>
</body>
</html>