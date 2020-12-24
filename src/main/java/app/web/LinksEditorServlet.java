package app.web;

import app.dao.GoodsDao;
import app.dao.StaffDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LinksEditorServlet", urlPatterns = {"/staffed"})
public class LinksEditorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        if (action.endsWith("/staff")) {
            StaffDao staffDao = (StaffDao) this.getServletContext().getAttribute("staffDao");
            response.sendRedirect("/staff/edit?id=" + request.getParameter("id"));
        }
        if (action.endsWith("/goods")) {
            GoodsDao goodsDao = (GoodsDao) this.getServletContext().getAttribute("goodsDao");
            response.sendRedirect("/goods/edit?id=" + request.getParameter("id"));
        }
    }
}