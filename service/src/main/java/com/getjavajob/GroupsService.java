package com.getjavajob;

import com.getjavajob.common.Group;
import com.getjavajob.exceptions.DaoException;
import exception.ServiceException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("groupService")
public class GroupsService {
    private GroupDAO groupsDao;

    @Autowired
    public GroupsService(GroupDAO groupsDao) {
        this.groupsDao = groupsDao;
    }

    public int create(@NonNull Group group) {
        try {
            return groupsDao.create(group);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void update(@NonNull Group group) {
        try {
            groupsDao.update(group);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deleteAll(int id) {
        try {
            groupsDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Group> getAll() {
        List<Group> list;
        try {
            list = groupsDao.getAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Group getGroup(int id) {
        try {
            return groupsDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}

