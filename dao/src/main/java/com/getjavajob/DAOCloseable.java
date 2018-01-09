package com.getjavajob;

import java.sql.SQLException;

public interface DAOCloseable extends AutoCloseable {
    @Override
    public void close() throws SQLException;
}