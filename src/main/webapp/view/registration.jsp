<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sombra</title>
    <link rel="stylesheet" href="../res/css/registration.css">
</head>
<body>
<header>
    <a href="/home" class="header"><button class="form">Home</button></a>
    <a href="/login" class="header"><button class="form">Log in</button></a>
</header>
<div class="menu">

    <h2>Regestration</h2>

    <form action="/registration" class="figure" method="post">
        <label for="login" class="label">Login :</label>
        <input type="text" placeholder="name" id="login" class="margin" required name="login">
        <br>
        <label for="password" class="label_password">Password :</label>
        <input type="password" placeholder="password" id="password" class="margin" required name="password">
        <br>
        <label for="email" class="label">Email :</label>
        <input type="email" required class="margin" id="email" placeholder="email" name="email">
        <br>
        <label for="phoneNumber" class="label_phone">Phone number :</label>
        <input type="text" placeholder="0XX-XXX-XX-XX" id="phoneNumber" required class="margin" name="phone">
        <br>
        <input type="submit" value="Registration">
    </form>
</div>
</body>
</html>
