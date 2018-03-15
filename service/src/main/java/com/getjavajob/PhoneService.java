package com.getjavajob;

import com.getjavajob.common.Phone;
import com.getjavajob.exceptions.DaoException;
import exception.ServiceException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service(value = "phoneService")
public class PhoneService {
    private PhoneDAO phoneDAO;

    @Autowired
    public PhoneService(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public void create(@NonNull Phone phone) throws SQLException {
        try {
            phoneDAO.create(phone);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deleteAll(int id) throws SQLException {
        try {
            phoneDAO.deleteAllFromAccount(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Phone> getAll(int id) throws SQLException {
        try {
            List<Phone> list = phoneDAO.getAll(id);
            return list;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}