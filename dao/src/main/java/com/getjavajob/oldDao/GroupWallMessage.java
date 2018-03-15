package com.getjavajob.oldDao;

import com.getjavajob.AccountDAO;
import com.getjavajob.common.Group;
import com.getjavajob.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

//@Repository(value = "groupWallMessageDAO")
public class GroupWallMessage {

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            ClassLoader loader = PhoneDAO.class.getClassLoader();
            InputStream resourceStream = loader.getResourceAsStream("groupMessagesSql.properties");
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountDAO accountDAO;

    public int create(Message ac) {
        int id = 0;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("createMessage"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ac.getSender().getId());
            ps.setInt(2, ac.getRecipient());
            ps.setString(3, ac.getText());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating message failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ac.setId(id);
        return id;
    }

    public Message read(int id) {
        return jdbcTemplate.queryForObject(prop.getProperty("getMessageById"), new Object[]{id}, new RowMapper<Message>() {
            @Nullable
            @Override
            public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                return createMessageFromResult(resultSet);
            }
        });
    }

    /*public Account read(String email) {
        return jdbcTemplate.queryForObject(SELECT_BY_EMAIL, new Object[]{email}, new RowMapper<Account>() {
            @Nullable
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                return createEmployeeFromResult(resultSet);
            }
        });
    }
*/
    public void delete(Group group) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(prop.getProperty("deleteMessageByrecipientId"))) {
            prepareStatement.setInt(1, group.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(prop.getProperty("deleteMessageById"))) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getAll(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("group.getWallMessages"))) {
            ps.setInt(1, id);
            List<Message> phones = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phones.add(createMessageFromResult(rs));
                }
            }
            Collections.reverse(phones);
            return phones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Message createMessageFromResult(ResultSet rs) {
        Message ac = new Message();
        try {
            ac.setId(rs.getInt("id"));
            ac.setSender(accountDAO.read(rs.getInt("sender_id")));
            ac.setRecipient((rs.getInt("group_id")));
            ac.setDate(rs.getDate("date"));
            ac.setText(rs.getString("text"));
            //ac.setPrivate(rs.getBoolean("is_private"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ac;
    }
}

