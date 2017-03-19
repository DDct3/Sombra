<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Future
  Date: 15/02/2017
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sombra</title>
    <link rel="stylesheet" href="../res/css/main.css">
    <link rel="stylesheet" href="../res/font-awesome-4.7.0/css/font-awesome.css">
</head>
<body>
<sec:authorize access="isAuthenticated()"><p class="authentication">Login: <sec:authentication property="name"/></p></sec:authorize>
<header>
    <h1>SombraShop</h1>
    <form action="/find">
        <input type="search" placeholder="Search" name="find">
        <button class="form">Search</button>
    </form>


    <div class="inup">
        <sec:authorize access="isAnonymous()"><a href="/loginpage">Log in</a></sec:authorize>
        <sec:authorize access="isAnonymous()"><a href="/registration">Sing up</a></sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')"><a href="/basket"><i class="fa fa-shopping-bag" aria-hidden="true"></i>
            Basket</a></sec:authorize>
        <sec:authorize access="isAuthenticated()"><a href="/logout">Log out</a></sec:authorize>
    </div>
</header>
<div class="menu">

    <nav>
        <a href="/Telephone" class="item">Telephone</a>
        <a href="/Tablet" class="item">Tablet</a>
        <a href="/Accessories" class="item">Accessories</a>
        <a href="/Other" class="item">Other</a>

        <sec:authorize access="hasRole('ROLE_ADMIN')"><form action="/upgradeCommodity" class="select">
            <select name="select">
                <option>Add new commodity</option>
                <option>Edit commodity</option>
                <option>Delete commodity</option>
            </select>
            <button type="submit">Go!</button>
        </form></sec:authorize>
    </nav>

    <figure class="figure">
        <div>
            <p class="label_first">Name</p>
            <p class="label">Description</p>
            <p class="label">Price</p>
        </div>
        <c:forEach var="q" items="${category}">
            <form action="/basket" method="post">
                <a href="/commodity/${q.id}">
                    <div class="commodity">
                        <div class="id">№${q.id}</div>
                        <input name="name" type="hidden"  value="${q.name}">
                        <div class="img"><img src="/imageDisplay?id=${q.id}" name="id" alt=""></div>
                        <div class="name">${q.name}</div>
                        <input name="price" type="hidden" value="${q.price}">
                        <div class="description">${q.description}</div>
                        <div class="price">${q.price}$</div>
                        <sec:authorize access="hasRole('ROLE_USER')"><button type="submit" class="button">Push</button></sec:authorize>
                    </div>
                </a>
            </form>
        </c:forEach>
    </figure>
</div>
</body>
</html>