package com.getjavajob;

import com.getjavajob.common.Group;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class GroupsDAOTest {
    private static Group forTests;
    private GroupDAO msd = new GroupDAO();

    @BeforeClass
    public static void accInitialise() {
        forTests = new Group();
        forTests.setId(1);
        forTests.setName("Group");
        forTests.setDescription("G");
    }

    @Before
    public void createDB() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("database.url");
            String user = props.getProperty("database.user");
            String password = props.getProperty("database.password");
            Connection connection = DriverManager.getConnection(url, user, password);
            try (Statement ps1 = connection.createStatement()) {
                ResultSet rs;
                ps1.executeUpdate("DROP TABLE IF EXISTS GROUPS;");
                ps1.executeUpdate("CREATE TABLE `Groups` (\n" +
                        "  `id` INT NOT NULL,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `description` VARCHAR(45) NULL,\n" +
                        "  PRIMARY KEY (`id`))");
                connection.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAndReadAccTest() throws SQLException {
        msd.create(forTests);
        Group gp = msd.read(1);
        assertEquals("", gp, forTests);
    }

    @Test
    public void updateAccTest() throws SQLException {
        msd.create(forTests);
        Group gp = msd.read(1);
        gp.setDescription("pokemon");
        msd.update(gp);
        assertEquals("", gp, msd.read(1));
    }

    @Test
    public void deleteAccTest() throws SQLException {
        msd.create(forTests);
        msd.delete(1);
        assertEquals("", msd.read(1), null);
    }

    @Test
    public void getAllAccTest() throws SQLException {
        msd.create(forTests);
        assertEquals("", msd.getAll().get(0), forTests);
    }
}