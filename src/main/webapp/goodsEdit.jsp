
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/db_lab4_war/style.css">
    <title>Goods editing</title>
</head>
<body>
<header>
<a href="/db_lab4_war/goods">back to goods list</a>
<nav>editing goods</nav>
</header>
<div class="container">
<form method="post">
    <input type="hidden" value="${goods.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${goods.name}" /><br><br>
    <label>Price</label><br>
    <input name="price" value="${goods.price}" type="number"/><br><br>
    <label>Stock balance</label><br>
    <input name="stockBalance" value="${goods.stockBalance}" type="number"/><br><br>
    <input type="submit" value="Send" />
</form>
</div>
</body>
</html>
