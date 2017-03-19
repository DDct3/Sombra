<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavlo
  Date: 05/03/2017
  Time: 02:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sombra</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="../res/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../res/css/custom.css"/>
</head>

<body>

<nav class="navbar">
    <div class="container">
        <a class="navbar-brand" href="/">Sombra Shop</a>
        <div class="navbar-right">
            <div class="container minicart"></div>
        </div>
    </div>
</nav>

<div class="container text-center">

    <div class="col-md-5 col-sm-12">
        <div class="bigcart"></div>
        <p>
            <b><a href="http://tutorialzine.com/2014/04/responsive-shopping-cart-layout-twitter-bootstrap-3/" title="Read the article!"></a></b>
        </p>
    </div>

    <div class="col-md-7 col-sm-12 text-left">
        <ul>
            <li class="row list-inline columnCaptions">
                <span>QTY</span>
                <span>ITEM</span>
                <span>Price</span>
            </li>
            <c:forEach var="q" items="${basket}">
            <li class="row">
                <span class="quantity">${q.id}</span>
                <span class="itemName">${q.name}</span>
                <span class="popbtn" style="padding-top: 0px;">
                        <form action="/remove" method="post">
                            <input type="text" value="${q.id}" name="id" hidden>
                            <button type="submit">Remove</button>
                        </form>

                        <form action="/write" method="post">
                            <input type="text" value="${q.id}" name="id" hidden>
                            <input type="text" value="${q.name}" name="name" hidden>
                            <input type="text" value="${q.price}" name="price" hidden>
                            <button type="submit">Buy</button>
                         </form>
                </span>
                <span class="price">${q.price}</span>
            </li>
            </c:forEach>
        </ul>
    </div>

</div>

<form action="/download" class="download" method="post">
    <button type="submit">Download check</button>
</form>

</body>
</html>

