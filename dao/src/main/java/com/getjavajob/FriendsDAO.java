package com.getjavajob;

import com.getjavajob.common.Friend;

import java.sql.SQLException;
import java.util.List;

public class FriendsDAO implements CrudDAO<Friend> {

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
