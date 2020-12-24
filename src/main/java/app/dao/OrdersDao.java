package app.dao;

import app.model.Order;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrdersDao extends AbstractController<Order> {
    private GoodsDao goodsDao;
    private CustomersDao customersDao;

    public OrdersDao(DataSource dataSource) {
        super(dataSource, "SELECT * FROM orders ORDER BY id_orders", "SELECT * FROM orders WHERE id_orders=?",
                "INSERT INTO orders (id_goods, quanity_of_goods, id_customer) VALUES(?,?,?)",
                "UPDATE orders id_goods=?, quanity_of_goods=?, id_customer=?,  WHERE id_orders = ?",
                "DELETE FROM orders WHERE id_order = ?");
    }

        @Override
        public Order readObject(ResultSet rs) throws SQLException {
            Order f = new Order();
            f.setId(rs.getLong("id_orders"));
            f.setQuantity(rs.getInt("quanity_of_goods"));
            return f;
        }

        @Override
        public long save(Order obj) throws SQLException {
            long id = super.save(obj);
            customersDao.saveCustomers(id, obj.getCustomers());
            return id;
        }

        @Override
        protected int writeObject(Order obj, PreparedStatement ps, int idx) throws SQLException {

            ps.setInt(idx++, obj.getQuantity());
            return idx;
        }


        @Override
        public void delete(long id) {
            super.delete(id);
        }

    public List<Order> list() {
        List<Order> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders ORDER BY quanity_of_goods");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Order obj = new Order();
                obj.setId(rs.getLong("id_orders"));
                obj.setQuantity(rs.getInt("quanity_of_goods"));

                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }
    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }
    public CustomersDao getCustomersDao() {
        return customersDao;
    }
    public void setCustomersDao(CustomersDao customersDao) {
        this.customersDao = customersDao;
    }
}

