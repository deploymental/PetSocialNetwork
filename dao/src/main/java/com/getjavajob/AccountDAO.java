package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.enums.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements CrudDAO<Account> {
    private static final String WHERE_ID = " WHERE id=?";
    private static final String WHERE_EMAIL = " WHERE email=?";
    private static final String SELECT_ALL = "SELECT * FROM accounts";
    private static final String SELECT_BY_ID = SELECT_ALL + WHERE_ID;
    private static final String SELECT_BY_EMAIL = SELECT_ALL + WHERE_EMAIL;
    private static final String DELETE_BY_ID = "DELETE FROM accounts" + WHERE_ID;
    private static final String UPDATE = "UPDATE accounts SET ";
    private static final String INSERT = "INSERT INTO accounts"
            + "(name, middlename, surname, sex, birthdate, homeaddress, workaddress, email, icq, skype, additionalinfo, password, image, role) VALUES"
            + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public int create(Account ac) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        int id;
        try (PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, ac.getName());
            ps.setString(2, ac.getMiddleName());
            ps.setString(3, ac.getSurName());
            ps.setString(4, ac.getSex().toString());
            ps.setDate(5, ac.getBirthDate());
            ps.setString(6, ac.getHomeAddress());
            ps.setString(7, ac.getWorkAddress());
            ps.setString(8, ac.getEmail());
            ps.setInt(9, ac.getIcq());
            ps.setString(10, ac.getSkype());
            ps.setString(11, ac.getAdditionalInfo());
            ps.setString(12, ac.getPassword());
            ps.setBytes(13, ac.getImage());
            ps.setString(14, "USER");
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        ac.setId(id);
        return id;
    }

    public Account read(int id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createEmployeeFromResult(rs);
                }
            }
        }
        return null;
    }

    public Account read(String email) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createEmployeeFromResult(rs);
                }
            }
        }
        return null;
    }

    public void update(Account ac) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (Statement ps1 = connection.createStatement()) {
            String update = UPDATE +
                    "name=" + "'" + ac.getName() + "'" + "," +
                    "middleName=" + "'" + ac.getMiddleName() + "'" + "," +
                    "surName=" + "'" + ac.getSurName() + "'" + "," +
                    "sex=" + "'" + ac.getSex() + "'" + "," +
                    "birthDate=" + "'" + ac.getBirthDate() + "'" + "," +
                    "homeAddress=" + "'" + ac.getHomeAddress() + "'" + "," +
                    "workAddress=" + "'" + ac.getWorkAddress() + "'" + "," +
                    "email=" + "'" + ac.getEmail() + "'" + "," +
                    "password=" + "'" + ac.getPassword() + "'" + "," +
                    "image=" + "'" + ac.getImage() + "'" + "," +
                    "icq=" + ac.getIcq() + "," +
                    "skype=" + "'" + ac.getSkype() + "'" + "," +
                    "additionalInfo=" + "'" + ac.getAdditionalInfo() + "'" +
                    " WHERE id=" + ac.getId() + ";";
            ps1.executeUpdate(update);
        }
    }

    public void delete(int id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        }
    }

    public List<Account> getAll() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Account> accounts = new ArrayList();
            while (resultSet.next()) {
                accounts.add(createEmployeeFromResult(resultSet));
            }
            return accounts;
        }
    }

    private Account createEmployeeFromResult(ResultSet rs) throws SQLException {
        Account ac = new Account();
        ac.setId(rs.getInt("id"));
        ac.setName(rs.getString("name"));
        ac.setMiddleName(rs.getString("middleName"));
        ac.setSurName(rs.getString("surName"));
        ac.setSex(Sex.valueOf(rs.getString("sex")));
        ac.setBirthDate(rs.getDate("birthDate"));
        ac.setHomeAddress(rs.getString("homeAddress"));
        ac.setWorkAddress(rs.getString("workAddress"));
        ac.setEmail(rs.getString("email"));
        ac.setPassword(rs.getString("password"));
        ac.setIcq(rs.getInt("icq"));
        ac.setSkype(rs.getString("skype"));
        ac.setAdditionalInfo(rs.getString("additionalInfo"));
        ac.setImage(rs.getBytes("image"));
        ac.setPhones(new PhoneDAO().getAll(ac.getId()));
        return ac;
    }
}