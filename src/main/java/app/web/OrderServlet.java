package app.web;


import app.dao.CustomersDao;
import app.dao.OrdersDao;
import app.model.Customers;
import app.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = {"/orders"})
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdersDao ordersDao = (OrdersDao) request.getServletContext().getAttribute("ordersDao");
        CustomersDao customersDao = (CustomersDao) request.getServletContext().getAttribute("customersDao");

        if (request.getParameter("id") == null) {
            List<Order> orders = ordersDao.list();
            request.setAttribute("orders", orders);
            this.getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);

        } else {
            response.sendRedirect("customers?id=" + request.getParameter("id"));
        }


    }
}