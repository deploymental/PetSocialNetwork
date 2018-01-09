package com.getjavajob;

import com.getjavajob.common.Account;

import java.sql.SQLException;
import java.util.List;

import static com.getjavajob.ConnectionPool.returnConnection;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public int createAccount(Account account) throws SQLException {
        int id = accountDAO.create(account);
        returnConnection();
        return id;
    }

    public void editAccount(Account account) throws SQLException {
        accountDAO.update(account);
        returnConnection();
    }

    public void removeAccount(Account account) throws SQLException {
        accountDAO.delete(account.getId());
        returnConnection();
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> list = accountDAO.getAll();
        returnConnection();
        return list;
    }

    public Account getAccount(int id) throws SQLException {
        Account ac = accountDAO.read(id);
        returnConnection();
        return ac;
    }

    public Account getAccount(String email) throws SQLException {
        Account ac = accountDAO.read(email);
        returnConnection();
        return ac;
    }
}