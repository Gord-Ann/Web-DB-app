package app.web;

import app.dao.CustomersDao;
import app.dao.GoodsDao;
import app.dao.OrdersDao;
import app.dao.StaffDao;
import org.postgresql.ds.PGPoolingDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class DbInitListener implements ServletContextListener {

    public DbInitListener() {
    }
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Initializing data source connection");

        PGPoolingDataSource poolingDataSource = new PGPoolingDataSource();

        poolingDataSource.setServerName("localhost");
        poolingDataSource.setDatabaseName("Store");
        poolingDataSource.setUser("postgres");
        poolingDataSource.setPassword("GordTree");
        poolingDataSource.setMaxConnections(8);
        poolingDataSource.setInitialConnections(1);

        GoodsDao goodsDao = new GoodsDao(poolingDataSource);
        CustomersDao customersDao = new CustomersDao(poolingDataSource);
        StaffDao staffDao = new StaffDao(poolingDataSource);
        OrdersDao ordersDao = new OrdersDao(poolingDataSource);

        sce.getServletContext().setAttribute("goodsDao", goodsDao);
        sce.getServletContext().setAttribute("customersDao", customersDao);
        sce.getServletContext().setAttribute("staffDao", staffDao);
        sce.getServletContext().setAttribute("ordersDao", ordersDao);

        sce.getServletContext().setAttribute("datasource", poolingDataSource);
        sce.getServletContext().log("Initialized all DAOs");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Closing connections pool");
        PGPoolingDataSource poolingDataSource = (PGPoolingDataSource) sce.getServletContext().getAttribute("datasource");
        poolingDataSource.close();
    }
}
