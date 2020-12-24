<%@ page import="app.model.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Dns
  Date: 18.12.2020
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>Orders</h1>

<table>
    <tr>
        <td>ID</td>
        <td>Quantity</td>
    </tr>
    <%
        for (Order orders : (List<Order>) request.getAttribute("orders")) {
    %>
    <tr>
        <td>
            <%=String.format("<a href='/goods?id'>%03d</a>", orders.getId())%>
        </td>
        <td>
            <%=String.format("%d",orders.getQuantity())%>

        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
