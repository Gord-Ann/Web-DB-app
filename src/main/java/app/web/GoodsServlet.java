package app.web;

import app.dao.GoodsDao;
import app.model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


    @WebServlet(name = "GoodsServlet", urlPatterns = {"/goods","/goods/edit","/goods/delete"})
    public class GoodsServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            GoodsDao goodsDao = (GoodsDao) request.getServletContext().getAttribute("goodsDao");

            if (request.getParameter("id") == null) {
                List<Goods> goods = goodsDao.list();
                request.setAttribute("goods", goods);
            }

            boolean edit = request.getRequestURI().endsWith("edit");
            boolean delete = request.getRequestURI().endsWith("delete");
            if (edit) {
                long id = Long.parseLong(request.getParameter("id"));
                Goods gd = goodsDao.get(id);
                request.setAttribute("goods", gd);
            }
            if(delete){
                try {
                    long id = Long.parseLong(request.getParameter("id"));
                    goodsDao.delete(id);
                    response.sendRedirect( "/goods");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            String route = edit ? "/goodsEdit.jsp" : "/goods.jsp";
            this.getServletContext().getRequestDispatcher(route).forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            GoodsDao goodsDao = (GoodsDao) request.getServletContext().getAttribute("goodsDao");
            Goods goods;
            if (request.getParameter("id") != null) {
                long id = Long.parseLong(request.getParameter("id"));
                goods = goodsDao.get(id);
            } else {
                goods = new Goods();
            }
            if (goods == null) {
                response.sendError(404);
                return;
            }

            String name = request.getParameter("name");
            if (name != null) {
                goods.setName(name);
            }
            String stockBalance = request.getParameter("stockBalance");
            if (stockBalance != null) {
               goods.setStockBalance(Integer.parseInt(stockBalance));
            }
            String price = request.getParameter("price");
            if (price != null) {
                goods.setStockBalance(Integer.parseInt(price));
            }

            try {
                goodsDao.save(goods);
            }   catch(Exception ex) {
                log("Failed to save", ex);
                response.sendError(500, "Failed to save " + ex.getMessage());
            }
            response.sendRedirect( request.getContextPath() + "/goods");
        }

    }
