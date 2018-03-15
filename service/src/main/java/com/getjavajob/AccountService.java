package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.exceptions.DaoException;
import exception.ServiceException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "accountService")
public class AccountService {
    private AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public int createAccount(@NonNull Account account) {
        try {
            return accountDAO.create(account);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void editAccount(@NonNull Account account) {
        try {
            accountDAO.update(account);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public int updateAccount(@NonNull Account account) {
        try {
            accountDAO.update(account);
            return account.getId();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    } // TODO: 2/20/2018 ?????

    public void removeAccount(@NonNull Account account) {
        try {
            accountDAO.delete(account.getId());
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Account> getAllAccounts() {
        try {
            return accountDAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Account getAccount(int id) {
        try {
            return accountDAO.read(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Account getAccount(@NonNull String email) {
        try {
            return accountDAO.read(email);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public byte[] getAvatar(int id) {
        try {
            return accountDAO.read(id).getImage();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    /*public List<Phone> deleteAll() throws SQLException {
        List<Phone> list = phoneDAO.getAll();
        return list;
    }

    public List<Phone> getAll(int id) throws SQLException {
        List<Phone> list = phoneDAO.getAll(id);
        return list;
    }*/
}