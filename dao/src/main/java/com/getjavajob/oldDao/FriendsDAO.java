package com.getjavajob.oldDao;

import com.getjavajob.common.Account;
import com.getjavajob.common.Friend;
import com.getjavajob.common.enums.AccountRelationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//@Repository(value = "friendsDao")
public class FriendsDAO implements CrudDAO<Friend> {
    private static Properties prop;

    static {
        prop = new Properties();
        try {
            ClassLoader loader = PhoneDAO.class.getClassLoader();
            InputStream resourceStream = loader.getResourceAsStream("friendsSql.properties");
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void sendRequestToFriend(Account sender, Account recipient) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.create"))) {
            ps.setInt(1, sender.getId());
            System.out.println(sender.getId());
            System.out.println(recipient.getId());
            ps.setInt(2, recipient.getId());
            ps.setString(3, AccountRelationStatus.FOLLOWER.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!");


            e.printStackTrace();
        }
    }

    public void deleteRequest(Account sender, Account recipient) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.delete"))) {
            ps.setInt(1, sender.getId());
            ps.setInt(2, recipient.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acceptRequest(Account sender, Account recipient) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.accept"))) {
            ps.setString(1, AccountRelationStatus.FRIEND.toString());
            ps.setInt(2, sender.getId());
            ps.setInt(3, recipient.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Friend> getAllFriends(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.getFriends"))) {
            ps.setInt(1, id);
            ps.setInt(2, id);
            List<Friend> friends = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    friends.add(createFriendFromResult(rs));
                }
            }
            return friends;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Friend> getAllFollowers(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.getFollowers"))) {
            ps.setInt(1, id);
            List<Friend> friends = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    friends.add(createFriendFromResult(rs));
                }
            }
            return friends;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Friend> getAllRecipients(int id) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.getRecipients"))) {
            ps.setInt(1, id);
            List<Friend> friends = new ArrayList();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    friends.add(createFriendFromResult(rs));
                }
            }
            return friends;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountRelationStatus getRelation(int sender, int recipient) {
        Friend friend = new Friend();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(prop.getProperty("accountsrelations.getRelation"))) {
            ps.setInt(1, sender);
            ps.setInt(2, recipient);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    friend = createFriendFromResult(rs);
                }
                return friend.getArs();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Friend createFriendFromResult(ResultSet rs) {
        Friend friend = new Friend();
        try {
            friend.setSender(rs.getInt("sender_id"));
            friend.setRecipient(rs.getInt("recipient_id"));
            friend.setArs(AccountRelationStatus.valueOf(rs.getString("relation")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friend;
    }


    @Override
    public int create(Friend friend) throws SQLException {
        return 0;
    }

    @Override
    public Friend read(int id) {
        return null;
    }

    @Override
    public void update(Friend friend) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Friend> getAll() throws SQLException {
        return null;
    }
}
