<%--
  Created by IntelliJ IDEA.
  User: NITRO
  Date: 19.11.2024
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/style/style1.css">
    <link rel="stylesheet" href="/style/btn_style.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<nav>
    <div class="text-box" id="home_btn">
        <a href="/" class="btn btn-white ">Home</a>
    </div>
</nav>

<div class="wrapper">
    <h1>Register</h1>
    <%if (request.getAttribute("msg") != null) {%>
    <p style="color: red"><%=request.getAttribute("msg")%>
    </p>
    <%}%>
    <form action="/registration" method="post">
        <div class="input-box">
            <input type="text" placeholder="name" name="name">
            <i class='bx bx-user'></i>
        </div>
        <div class="input-box">
            <input type="text" placeholder="surname" name="surname">
            <i class='bx bx-user'></i>
        </div>
        <div class="input-box">
            <input type="email" placeholder="email" name="email">
            <i class='bx bx-envelope'></i>
        </div>
        <div class="input-box">
            <input type="password" name="password" placeholder="password" required>
            <i class='bx bx-lock-alt'></i>
        </div>
        <div class="input-box">
            <input type="file" name="img" id="file_input">
            <i class='bx bx-file'></i>
        </div>

        <button class="my_btn">Register</button>
    </form>

</div>
</body>
</html>
