<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: future
  Date: 07.03.17
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<p>${commodity.name}</p>--%>
<%--</body>--%>
<%--</html>--%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product</title>
    <link rel="stylesheet" href="../res/css/commodity.css">
</head>
<body>
<header>
    <div class="name">${commodity.name}</div>
</header>
<main>
    <div class="picture"><img src="/imageDisplay?id=${commodity.id}"/></div>
    <div class="description">
        ${commodity.description}
        <div class="price">PRICE: ${commodity.price}</div>
        <form action="/addBasket" method="post">
            <input name="name" type="hidden"  value="${commodity.name}">
            <input name="price" type="hidden" value="${commodity.price}">
            <sec:authorize access="hasRole('ROLE_USER')"><button type="submit" class="add_cart">Add to basket</button></sec:authorize>
        </form>
    </div>
</main>
</body>
</html>