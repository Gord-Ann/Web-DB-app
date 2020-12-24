<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="goods" class="java.util.ArrayList" scope="session"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Main</title>
</head>
<body>
<header>
    <div class="store">Store</div>
</header>
<div class="tab">
<a href="goods">Goods</a><br>
<a href="staff">Staff</a><br>
<a href="customers">Customers</a><br>
</div>
</body>
</html>