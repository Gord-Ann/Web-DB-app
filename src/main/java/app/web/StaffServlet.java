package app.web;

import app.dao.StaffDao;
import app.model.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StaffServlet", urlPatterns = {"/staff","/staff/edit"})
public class StaffServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaffDao staffDao = (StaffDao) request.getServletContext().getAttribute("staffDao");
        if (request.getParameter("id") == null) {
            List<Staff> staff = staffDao.list();
            request.setAttribute("staff", staff);
        }

        boolean edit = request.getRequestURI().endsWith("edit");
        if (edit) {
            long id = Long.parseLong(request.getParameter("id"));
            Staff stf = staffDao.get(id);
            request.setAttribute("staff", stf);
        }
        String route = edit ? "/staffEdit.jsp" : "/staff.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaffDao staffDao = (StaffDao) request.getServletContext().getAttribute("staffDao");

        Staff staff;
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            staff = staffDao.get(id);
        } else {
           staff = new Staff();
        }
        if (staff == null) {
            response.sendError(404);
            return;
        }
        String name = request.getParameter("name");
        if (name != null) {
            staff.setName(name);
        }
        String rank = request.getParameter("rank");
        if (rank != null) {
            staff.setRank(Integer.parseInt(rank));
        }
        try {
            staffDao.save(staff);
        }   catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/staff");
    }
}