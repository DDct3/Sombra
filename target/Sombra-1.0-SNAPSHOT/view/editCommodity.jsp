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
        <b>Edit commodity</b>
    </div>
</div>
<main>
    <div class="field">
        <br>
        <form action="/editCommodity" method="post" enctype="multipart/form-data">>
            <label for="id">Identification Data</label>
            <br>
            <input type="number" name="id" id="id">
            <br>
            <label for="select">Category</label>
            <br>
            <select name="select" id="select">
                <option>Telephone</option>
                <option>Tablet</option>
                <option>Accessories</option>
                <option>Other</option>
            </select>
            <br>
            <label for="name">Name</label>
            <br>
            <input type="text" required id="name" name="name">
            <br class="space">
            <label for="price">Price</label>
            <br>
            <input type="number" required id="price" name="price">
            <br class="space">
            <label for="description">Description</label>
            <br>
            <textarea name="description" id="description" cols="32" rows="15">
            </textarea>
            <br>
            <label for="img">Image</label>
            <br>
            <input style="margin: 0 0 0 25%" type="file" id="img" name="image">
            <br>
            <input type="submit" value="Edit" class="button">
        </form>
    </div>
</main>
<footer></footer>
</body>
</html>
