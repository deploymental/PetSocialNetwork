package com.getjavajob;

import com.getjavajob.common.Phone;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PhoneDAO implements CrudDAO<Phone> {
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

    @Override
    public int create(Phone ph) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        int id;
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.createPhone"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ph.getAccId());
            ps.setLong(2, ph.getNumber());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating phone failed, no ID obtained.");
                }
            }
        }
        return id;
    }

    @Override
    public Phone read(int id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("phone.getPhoneById"))) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createPhoneFromResult(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void update(Phone ph) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("phone.update"))) {
            ps.setInt(1, ph.getAccId());
            ps.setLong(2, ph.getNumber());
            ps.setInt(3, ph.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(prop.getProperty("account.deletePhone"))) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Phone> getAll() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(prop.getProperty("account.getAllPhones"))) {
            List<Phone> accounts = new ArrayList();
            while (resultSet.next()) {
                accounts.add(createPhoneFromResult(resultSet));
            }
            return accounts;
        }
    }


    public List<Phone> getAll(int id) throws SQLException {

        Connection connection = ConnectionPool.getInstance().getConnection();
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
        ph.setAccId(rs.getInt("account_id"));
        ph.setNumber(rs.getLong("number"));
        return ph;
    }
}