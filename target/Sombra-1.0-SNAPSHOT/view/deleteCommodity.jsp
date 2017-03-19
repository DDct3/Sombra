<%--
  Created by IntelliJ IDEA.
  User: Pavlo
  Date: 04/03/2017
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add new commodity</title>
    <link rel="stylesheet" href="../res/css/upgradeCommodity.css">
</head>
<body>
<header>
    <a href="/" class="header"><button class="form">Home</button></a>
    <a href="/registration" class="header"><button class="form">Sing up</button></a>
</header>
<div class="header">
    <div class="log_in">
        <b>Remove commodity</b>
    </div>
</div>
<main>
    <div class="field">
        <br>
        <form action="/deleteCommodity" method="post">
            <label for="id">Identification Data</label>
            <br>
            <input type="number" name="id" id="id">
            <br>
            <input type="submit" value="Edit" class="button">
        </form>
    </div>
</main>
<footer></footer>
</body>
</html>
