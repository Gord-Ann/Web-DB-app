
<%@ page import="java.util.List" %>
<%@ page import="app.model.Goods" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>Goods</title>
</head>
<body>
<header>
    <a href="homepage">Homepage</a>
    <nav>
        <a href="staff">Staff</a><br>
        <a class="current" href="goods">Goods</a>
        <a href="customers">Customers</a>
    </nav>
</header>
<div class="container">
    <table>
        <div>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Price</td>
                <td>Stock balance</td>
                <td></td>
                <td></td>
            </tr>
                <%
        for (Goods goods : (List<Goods>) request.getAttribute("goods")) {
    %>
            <tr>
                <td>
                    <%=String.format("<a href='/goods?id=%03d'>%03d</a>",
                            goods.getId(),goods.getId())%>
                </td>
                <td>
                    <%=String.format("%s",goods.getName())%>
                </td>
                <td>
                    <%=String.format("%d",goods.getPrice())%>
                </td>
                <td>
                    <%=String.format("%d",goods.getStockBalance())%>
                </td>
                <td>
                    <%=String.format("<a href='goods/edit?id=%d' class=\"edit\">edit</a>",goods.getId())%>
                </td>
                <td>
                    <%=String.format("<a href='goods/delete?id=%d' class=\"edit\">delete</a>",goods.getId())%>
                </td>
            </tr>
                <%
        }
    %>
    </table>
</div>
</body>
</html>
