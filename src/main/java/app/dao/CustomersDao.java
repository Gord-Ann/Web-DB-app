package app.dao;

import app.model.Customers;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class CustomersDao extends AbstractCaching<Customers> {
    public static final String SELECT_BY_ID = "SELECT * FROM customers WHERE id_customers = ?";
    public static final String SELECT_ALL = "SELECT * FROM customers ORDER BY name, username";
    public static final String INSERT = "INSERT INTO customers (name, username, address) VALUES (?,?,?)";
    public static final String UPDATE = "UPDATE customers SET name = ?, username = ?,customers = ? where id_customers = ?";
    public static final String DELETE = "DELETE FROM customers WHERE id_customers = ?";

    public CustomersDao(DataSource dataSource) {
        super(dataSource, SELECT_ALL, SELECT_BY_ID, INSERT, UPDATE, DELETE);
    }

    public Customers readObject(ResultSet rs) throws SQLException {
        Customers s = new Customers();
        s.setId(rs.getLong("id_customers"));
        s.setName(rs.getString("name"));
        s.setUsername(rs.getString("username"));
        s.setAddress(rs.getString("address"));
        return s;
    }
    @Override
    protected int writeObject(Customers obj, PreparedStatement ps, int idx) throws SQLException {
        ps.setString(idx++, obj.getName());
        ps.setString(idx++, obj.getUsername());

        return idx;
    }

    public void saveCustomers(long orderId, List<Customers> customers) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM customers WHERE id_orders = ?");
            ps.setLong(1, orderId);
            ps.executeUpdate();

            ps.close();

            ps = connection.prepareStatement("INSERT INTO customers (id_customers, id_orders) VALUES (?,?)");
            for (Customers customer : customers) {
                ps.setLong(1, orderId);
                ps.setLong(2, customer.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

