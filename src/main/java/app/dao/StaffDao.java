package app.dao;

import app.model.Staff;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class StaffDao implements Dao<Staff> {

    private final DataSource dataSource;

    public StaffDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Staff get(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM stuff WHERE id_stuff = ?")
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Staff obj = new Staff();
                obj.setId(rs.getLong("id_stuff"));
                obj.setName(rs.getString("name"));
                obj.setRank(rs.getInt("rank"));

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Staff> list() {
        List<Staff> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stuff ORDER BY name");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Staff obj = new Staff();
                obj.setId(rs.getLong("id_stuff"));
                obj.setName(rs.getString("name"));
                obj.setRank(rs.getInt("rank"));

                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public long save(Staff staff) throws SQLException {
        if (staff.getId() == 0) {
            return insert(staff);
        } else {
            return update(staff);
        }
    }

    private long insert(Staff obj) throws SQLException {
        long id = -1;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO stuff (name) VALUES(?)",
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


    private long update(Staff obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE stuff SET name = ? WHERE id_stuff= ?")
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM stuff WHERE id_stuff = ?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
