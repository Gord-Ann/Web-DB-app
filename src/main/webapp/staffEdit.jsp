<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/db_lab4_war/style.css">
    <title>Edit staff</title>
</head>
<body>
<header>
    <a href="/db_lab4_war/staff">back to staff list</a>
    <nav> editing staff</nav>
</header>
<div class="container">
<form method="post">
    <input type="hidden" value="${staff.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${staff.name}" /><br><br>
    <label>Rank</label><br>
    <input name="rank" value="${staff.rank}" type="number"/><br><br>
    <input type="submit" value="Send" />
</form>
</div>
</body>
</html>
