package com.getjavajob;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final String PROPERTIES_FILE = "db.properties";
    private static final String URL = "database.url";
    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";
    private static final String DRIVER = "database.driver";
    private static final int CONNECTIONS_QUANTITY = 1;
    private static ConnectionPool ourInstance = new ConnectionPool();
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static LinkedBlockingQueue<Connection> connections = new LinkedBlockingQueue<>();

    static {
        Properties prop = new Properties();
        try {
            ClassLoader loader = ConnectionPool.class.getClassLoader();
            InputStream resourceStream = loader.getResourceAsStream(PROPERTIES_FILE);
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                Class.forName(prop.getProperty(DRIVER)).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < CONNECTIONS_QUANTITY; i++) {
                Connection conn = DriverManager.getConnection(
                        prop.getProperty(URL),
                        prop.getProperty(USER),
                        prop.getProperty(PASSWORD));
                conn.setAutoCommit(false);
                connections.add(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    public static synchronized void returnConnection() throws SQLException {
        Connection c = tl.get();
        try (DAOCloseable ac = c::rollback) {
            c.commit();
        }
        connections.add(tl.get());
        tl.remove();
    }

    public synchronized Connection getConnection() {
        Connection conn = tl.get();
        if (conn == null) {
            try {
                conn = connections.take();
                Statement st = conn.createStatement();
                st.execute("select 1 from accounts");
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
        tl.set(conn);
        return conn;
    }
}