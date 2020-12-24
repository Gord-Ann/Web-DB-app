
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Customers</title>
</head>
<body>
<header>
    <a href="homepage">Homepage</a>
    <nav>
        <a href="staff">Staff</a><br>
        <a href="goods">Goods</a>
        <a class="current" href="customers">Customers</a>
    </nav>
</header>
    <div class="container">
        <c:forEach items="${requestScope.customers}" var="customers">
            <div class="card">
            <a href="customers?id=${customers.id}">${customers.name} (${customers.username})</a><br>
                <div>Address: ${customers.address}</div>
            <a href="customers/edit?id=${customers.id}" class="edit">edit</a>
            </div><br/>
        </c:forEach>
    </div>
</body>
</html>
