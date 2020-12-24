
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Staff</title>
</head>
<body>
<header>
    <a href="homepage">Homepage</a>
<nav>
    <a class="current" href="staff">Staff</a><br>
    <a href="goods">Goods</a>
    <a href="customers">Customers</a>
</nav>
</header>
    <div class="container">
        <c:forEach items="${requestScope.staff}" var="staff">
            <div class="card">
            <a href="staff?id=${staff.id}">${staff.name} </a><div>Rank: ${staff.rank} </div>
            <a href="staff/edit?id=${staff.id}" class="edit">edit</a>
            </div><br/>
        </c:forEach>
    </div>
</body>
</html>
