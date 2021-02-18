<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="product/style.css">
</head>
<body>
<h1>Product List</h1>
<button><a href="/products?action=create">Create new product</a></button>

<form action="products?action=search">
    <input name="action" value="search" hidden>
    <input type="text" name="findName" placeholder="enter name">
    <input type="submit" value="Search">
</form>

<button><a href="/products?action=sort&name">Sort by name</a></button>
<%--<button><a href="/products?action=search&name=${product.getName()}">Search</a></button>--%>

<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Producer</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getProducer()}</td>
            <td><a href="/products?action=edit&id=${product.getId()}">Edit</a></td>
            <td><a href="/products?action=delete&id=${product.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
