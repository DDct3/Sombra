<%--
  Created by IntelliJ IDEA.
  User: future
  Date: 15.03.17
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../res/css/login.css">
</head>
<body>
<header>
    <a href="/" class="header"><button class="form">Home</button></a>
    <a href="/registration" class="header"><button class="form">Sing up</button></a>
</header>
<div class="header">
    <div class="log_in">
        <b>Log in</b>
    </div>
</div>
<main>
    <div class="field">
        <br>
        <form action="/login" method="post">
            <label for="Login">Email</label>
            <br>
            <input type="text" required id="Login" name="username">
            <br class="space">
            <label for="Password">Password</label>
            <br>
            <input type="password" required id="Password" name="password">
            <br class="space">
            <input type="submit" value="Log in" class="button">
        </form>
    </div>
</main>
<footer></footer>
</body>
</html>

