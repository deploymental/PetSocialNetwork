package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.enums.Sex;
import com.getjavajob.exceptions.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-context.xml", "classpath:dao-context-override.xml"})
public class AccountDAOTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AccountDAO accountDao;
    private Account account;
    private List<Account> accounts;
    private Account friend;
    private List<Account> friends;
    private ResourceDatabasePopulator databasePopulator;

    @Before
    public void init() throws DaoException {
        Resource createTables = new ClassPathResource("queriesCreate.sql");
        databasePopulator = new ResourceDatabasePopulator(createTables);
        databasePopulator.execute(dataSource);

        account = new Account();
        account.setId(1);
        account.setName("Vasya");
        account.setMiddleName("Vasylevich");
        account.setSurName("Vasyacovich");
        account.setSex(Sex.valueOf("MALE"));
        account.setBirthDate(new Date(2134235));
        account.setHomeAddress("moscow pushkina");
        account.setWorkAddress("moscow colotushkina");
        account.setEmail("loh@pidr.ua");
        account.setIcq("666666666");
        account.setSkype("lolir");
        account.setAdditionalInfo("loliryu");
        accounts = new ArrayList<>();
        friends = new ArrayList<>();
        friends.add(friend);
        accountDao.create(account);
        account.setEmail("loh@pidr.ru");
        account.setId(2);
        friend = account;
        accountDao.create(friend);
        accounts.add(account);
        accounts.add(friend);
    }

    @After
    public void terminate() throws SQLException {
        Resource dropTables = new ClassPathResource("queriesDrop.sql");
        databasePopulator.setScripts(dropTables);
        databasePopulator.execute(dataSource);
    }

    @Test
    public void getByIdTest() throws DaoException {
        assertEquals(account, accountDao.read(1));
    }

    @Test
    public void getAllTest() throws DaoException {
        List<Account> accountsDao = accountDao.getAll();
        int i = 0;
        assertEquals(accounts.size(), accountsDao.size());
        for (Account account : accounts) {
            assertEquals(account, accountsDao.get(i));
            i++;
        }
    }

    @Test
    public void getByEmailTest() throws DaoException {
        assertEquals(account, accountDao.read("ivanov@mail.ru"));
    }

    @Transactional
    @Test
    public void deleteTest() throws DaoException {
        accountDao.delete(accountDao.read(account.getEmail()).getId());
        assertNull(accountDao.read(account.getId()));
    }

    @Transactional
    @Test
    public void updateTest() throws DaoException {
        account.setHomeAddress("New home address");
        accountDao.update(account);
        assertEquals(account, accountDao.read(1));
    }

    @Transactional
    @Test
    public void insertTest() throws ParseException, DaoException {
        Account newAccount = new Account();
        newAccount.setId(3);
        newAccount.setName("Vasya");
        newAccount.setMiddleName("Vasylevich");
        newAccount.setSurName("Vasyacovich");
        newAccount.setSex(Sex.valueOf("MALE"));
        newAccount.setBirthDate(new Date(2134235));
        newAccount.setHomeAddress("moscow pushkina");
        newAccount.setWorkAddress("moscow colotushkina");
        newAccount.setEmail("loh@pidr.com");
        newAccount.setIcq("666666666");
        newAccount.setSkype("lolir");
        newAccount.setAdditionalInfo("loliryu");
        newAccount = accountDao.read(accountDao.create(newAccount));
        assertEquals(newAccount, accountDao.read(3));
    }

    @Test
    public void getAvatarById() throws DaoException {
        byte[] array = new BigInteger("1111000011110001", 2).toByteArray();
        account.setImage(array);
        accountDao.update(account);
        assertArrayEquals(account.getImage(), accountDao.read(account.getEmail()).getImage());
    }
}
