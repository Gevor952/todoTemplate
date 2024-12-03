<%@ page import="am.itspace.todotemplate.model.ToDo" %><%--
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
    <form action="/editToDo" method="post">
        <%ToDo toDo = ((ToDo) request.getAttribute("toDo"));%>
        <input type="hidden" name="id" value="<%=toDo.getId()%>">
        <div class="input-box">
            <input type="text" placeholder="title" name="title" value="<%=toDo.getTitle()%>">
            <i class='bx bx-file-blank'></i>
        </div>

        <button class="my_btn">Register</button>
    </form>

</div>
</body>
</html>
