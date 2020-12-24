
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/db_lab4_war/style.css">
    <title>Customers editing</title>
</head>
<body>
<header>
    <a href="/db_lab4_war/customers">back to customers list</a>
    <nav>editing customers</nav>
</header>
<div class="container">
<form method="post">
    <input type="hidden" value="${customers.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${customers.name}" /><br><br>
    <label>Username</label><br>
    <input name="username" value="${customers.username}" /><br><br>
    <label>Address</label><br>
    <input name="username" value="${customers.address}" /><br><br>
    <input type="submit" value="Send" />
</form>
</div>
</body>
</html>
