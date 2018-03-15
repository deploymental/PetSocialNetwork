package com.getjavajob.oldDao;

import com.getjavajob.common.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//@Repository(value = "phoneDao")
public class PhoneDAO {
    private static Properties prop;

    static {
        prop = new Properties();
        try {
            ClassLoader loader = PhoneDAO.class.getClassLoader();
            InputStream resourceStream = loader.getResourceAsStream("phoneSql.properties");
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int create(Phone ph) {
        int id = 0;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.createPhone"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ph.getAccount_id());
            ps.setString(2, ph.getNumber());
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


    public Phone read(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("phone.getPhoneById"))) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createPhoneFromResult(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.deletePhone"))) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Phone> getAll(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.getPhones"))) {
            ps.setInt(1, id);
            List<Phone> phones = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phones.add(createPhoneFromResult(rs));
                }
            }
            return phones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Phone createPhoneFromResult(ResultSet rs) throws SQLException {
        Phone ph = new Phone();
        ph.setId(rs.getInt("id"));
        ph.setAccount_id(rs.getInt("account_id"));
        ph.setNumber(rs.getString("number"));
        return ph;
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
                    phones.add(createPhoneFromResult(rs));
                }
            }
            return phones;
        }
    }

    private Phone createPhoneFromResult(ResultSet rs) throws SQLException {
        Phone ph = new Phone();
        ph.setId(rs.getInt("id"));
        ph.setAccount_id(rs.getInt("account_id"));
        ph.setNumber(rs.getString("number"));
        return ph;
    }*/
}