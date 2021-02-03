<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find</title>
    <link rel="stylesheet" type="text/css" href="product/style.css">
</head>
<body>
<%--<form>--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td>Name:</td>--%>
<%--            <td><input name="name" value="${product.getName()}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Price:</td>--%>
<%--            <td><input name="price" value="${product.getPrice()}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Description:</td>--%>
<%--            <td><input  name="description" value="${product.getDescription()}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Producer:</td>--%>
<%--            <td><input  name="producer" value="${product.getProducer()}"></td>--%>
<%--        </tr>--%>

<%--    </table>--%>
<%--</form>--%>

<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Producer</th>
    </tr>
    <c:forEach items="${findByName}" var="products">
        <tr>
            <td>${products.getName()}</td>
            <td>${products.getPrice()}</td>
            <td>${products.getDescription()}</td>
            <td>${products.getProducer()}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
