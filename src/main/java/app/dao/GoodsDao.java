package app.dao;

import app.model.Goods;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GoodsDao implements Dao<Goods> {

    private final DataSource dataSource;

    public GoodsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Goods get(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM goods WHERE id_goods = ?")
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Goods obj = new Goods();
                obj.setId(rs.getLong("id_goods"));
                obj.setName(rs.getString("name"));
                obj.setStockBalance(rs.getInt("stock_balance"));

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Goods> list() {
        List<Goods> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM goods ORDER BY name");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Goods obj = new Goods();
                obj.setId(rs.getLong("id_goods"));
                obj.setName(rs.getString("name"));
                obj.setStockBalance(rs.getInt("stock_balance"));
                obj.setPrice(rs.getInt("price"));

                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public long save(Goods goods) throws SQLException {
        if (goods.getId() == 0) {
            return insert(goods);
        } else {
            return update(goods);
        }
    }

    private long insert(Goods obj) throws SQLException {
        long id = -1;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO goods (name) VALUES(?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            preparedStatement.setString(1, obj.getName());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs != null && rs.next()) {
                id = rs.getLong(1);
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    private long update(Goods obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE goods SET name = ? WHERE id_goods = ?")
        ) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj.getId();
    }
    public void delete(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM goods WHERE id_goods = ?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
