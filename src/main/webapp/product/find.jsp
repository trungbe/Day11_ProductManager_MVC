<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find</title>
    <link rel="stylesheet" type="text/css" href="product/style.css">
</head>
<body>
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
