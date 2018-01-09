package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.enums.Sex;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class AccountDAOTest {
    private static Account forTests;
    private AccountDAO msd = new AccountDAO();

    @BeforeClass
    public static void accInitialise() {
        forTests = new Account();
        forTests.setId(1);
        forTests.setName("Vasya");
        forTests.setMiddleName("Vasylevich");
        forTests.setSurName("Vasyacovich");
        forTests.setSex(Sex.valueOf("MALE"));
        forTests.setBirthDate(new Date(2134235));
        forTests.setHomeAddress("moscow pushkina");
        forTests.setWorkAddress("moscow colotushkina");
        forTests.setEmail("loh@pidr.ua");
        forTests.setIcq(666666666);
        forTests.setSkype("lolir");
        forTests.setAdditionalInfo("loliryu");
    }

    @Before
    public void createDB() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("TestProperties"));
            String url = props.getProperty("database.url");
            String user = props.getProperty("database.user");
            String password = props.getProperty("database.password");
            Connection connection = DriverManager.getConnection(url, user, password);
            try (Statement ps1 = connection.createStatement()) {
                ps1.executeUpdate("DROP TABLE IF EXISTS ACCOUNTS;");

                ps1.executeUpdate("CREATE TABLE ACCOUNTS (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `middleName` VARCHAR(45) NULL,\n" +
                        "  `surName` VARCHAR(45) NOT NULL,\n" +
                        "  `sex` VARCHAR(1) NOT NULL,\n" +
                        "  `birthDate` DATE NOT NULL,\n" +
                        "  `homeAddress` VARCHAR(45) NULL,\n" +
                        "  `workAddress` VARCHAR(45) NULL,\n" +
                        "  `email` VARCHAR(45) NOT NULL,\n" +
                        "  `icq` INT NULL,\n" +
                        "  `skype` VARCHAR(45) NULL,\n" +
                        "  `additionalInfo` VARCHAR(45) NULL,\n" +
                        "  PRIMARY KEY (`id`));");
                connection.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAndReadAccTest() throws SQLException {
        msd.create(forTests);
        Account ac = msd.read(1);
        assertEquals("", ac, forTests);
    }

    @Test
    public void updateAccTest() throws SQLException {
        msd.create(forTests);
        Account ac = msd.read(1);
        ac.setSkype("pokemon");
        msd.update(ac);
        assertEquals("", ac, msd.read(1));
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
    //public static void main(String[] args) throws SQLException {
        /*AccountDAO msd = new AccountDAO("TestProperties");
        Account ac = new Account();
        ac.setId(1);
        ac.setName("Vasya");
        ac.setMiddleName("Vasylevich");
        ac.setSurName("Vasyacovich");
        ac.setSex("M");
        ac.setBirthDate(new Date(2134235));
        ac.setHomeAddress("moscow pushkina");
        ac.setWorkAddress("moscow colotushkina");
        ac.setEmail("loh@pidr.ua");
        ac.setIcq(666666666);
        ac.setSkype("lolir");
        ac.setAdditionalInfo("loliryu");

        //msd.create(ac);
        System.out.println(msd.read(1));
        System.out.println(msd.getAll().size());
        ac.setName("Lesha");
        msd.update(ac);
        System.out.println(msd.read(1));
        msd.delete(1);
        System.out.println(msd.getAll().size());*/
}

