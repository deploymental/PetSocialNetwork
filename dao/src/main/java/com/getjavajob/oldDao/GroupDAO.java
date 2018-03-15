package com.getjavajob.oldDao;

import com.getjavajob.AccountDAO;
import com.getjavajob.common.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//@Repository(value = "groupDao")
public class GroupDAO {
    private static Properties prop;

    static {
        prop = new Properties();
        try {
            ClassLoader loader = PhoneDAO.class.getClassLoader();
            InputStream resourceStream = loader.getResourceAsStream("groupSql.properties");
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AccountDAO accountDAO;

    public int create(Group group) {
        int id = 0;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.createGroup"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, group.getCreator().getId());
            ps.setString(2, group.getName());
            ps.setString(3, group.getDescription());
            ps.setBytes(4, group.getImage());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating phone failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void update(Group group) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("group.update"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, group.getName());
            ps.setString(2, group.getDescription());
            ps.setBytes(3, group.getImage());
            ps.setInt(4, group.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group read(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("group.getGroupById"))) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createGroupFromResult(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("group.deleteGroup"))) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getAll() {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("group.getAllgroups"))) {
            List<Group> phones = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phones.add(createGroupFromResult(rs));
                }
            }
            return phones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Group createGroupFromResult(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setCreator(accountDAO.read(rs.getInt("creator_id")));
        group.setName(rs.getString("name"));
        group.setDescription(rs.getString("description"));
        group.setImage(rs.getBytes("image"));
        return group;
    }

    /*    @Autowired
    JdbcTemplate jdbcTemplate;



    public void create(Phone phone) throws SQLException {

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.createPhone"))) {
            ps.setInt(1, phone.getAccount_id());
            ps.setString(2, phone.getNumber());
        }
    }

    public void deleteAll(int id) throws SQLException {

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.deletePhones"))) {
            ps.setInt(1, id);
        }
    }


    public List<Phone> getAll(int id) throws SQLException {

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.getPhones"))) {
            ps.setInt(1, id);
            List<Phone> phones = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phones.add(createGroupFromResult(rs));
                }
            }
            return phones;
        }
    }

    private Phone createGroupFromResult(ResultSet rs) throws SQLException {
        Phone ph = new Phone();
        ph.setId(rs.getInt("id"));
        ph.setAccount_id(rs.getInt("account_id"));
        ph.setNumber(rs.getString("number"));
        return ph;
    }*/
}