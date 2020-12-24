package app.web;

import app.dao.CustomersDao;
import app.model.Customers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomersServlet", urlPatterns = {"/customers","/customers/edit"})
public class CustomersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomersDao customersDao = (CustomersDao) this.getServletContext().getAttribute("customersDao");

        if (request.getParameter("id") == null) {
            List<Customers> customers = customersDao.list();
            request.setAttribute("customers", customers);

        }
        boolean edit = request.getRequestURI().endsWith("edit");
        if (edit) {
            long id = Long.parseLong(request.getParameter("id"));
            Customers cm = customersDao.get(id);
            request.setAttribute("customers", cm);
        }
        String route = edit ? "/customersEdit.jsp" : "/customers.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomersDao customersDao = (CustomersDao) this.getServletContext().getAttribute("customersDao");
        Customers customers;
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            customers = customersDao.get(id);
        } else {
            customers = new Customers();
        }
        if (customers == null) {
            response.sendError(404);
            return;
        }

        String name = request.getParameter("name");
        if (name != null) {
            customers.setName(name);
        }
        String username = request.getParameter("username");
        if (name != null) {
            customers.setUsername(username);
        }
        String address = request.getParameter("address");
        if (address != null) {
            customers.setAddress(address);
        }

        try {
            customersDao.save(customers);
        }   catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/customers");
    }
}