package com.getjavajob;

import com.getjavajob.common.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO implements CrudDAO<Group> {
    private static final String WHERE_ID = " WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM Groups";
    private static final String SELECT_BY_ID = SELECT_ALL + WHERE_ID;
    private static final String DELETE_BY_ID = "DELETE FROM Groups" + WHERE_ID;
    private static final String UPDATE = "UPDATE Groups SET ";
    private static final String INSERT = "INSERT INTO Groups" + "(id, name, description) VALUES" + "(?,?,?)";


    @Override
    public int create(Group gp) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        int id;
        try (PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, 1);
            ps.setString(2, gp.getName());
            ps.setString(3, gp.getDescription());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return id;
    }

    @Override
    public Group read(int id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createEmployeeFromResult(rs);
                }
            }
            return null;
        }
    }

    @Override
    public void update(Group gp) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (Statement ps1 = connection.createStatement()) {
            String update = UPDATE +
                    "name=" + "'" + gp.getName() + "'" + "," +
                    "description=" + "'" + gp.getDescription() + "'" +
                    " WHERE id=" + gp.getId() + ";";
            ps1.executeUpdate(update);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        }
    }

    @Override
    public List<Group> getAll() throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                groups.add(createEmployeeFromResult(resultSet));
            }
            return groups;
        }
    }

    public Group createEmployeeFromResult(ResultSet rs) throws SQLException {
        Group gp = new Group();
        gp.setId(rs.getInt("id"));
        gp.setName(rs.getString("name"));
        gp.setDescription(rs.getString("description"));
        return gp;
    }
}