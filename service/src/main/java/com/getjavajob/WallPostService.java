package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.Group;
import com.getjavajob.common.Message;
import com.getjavajob.exceptions.DaoException;
import exception.ServiceException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("messageService")
public class WallPostService {
    private AccountWallMessageDAO accountWallMessageDAO;
    private GroupWallMessageDAO groupWallMessageDAO;
    private AccountService accountService;
    private GroupsService groupsService;

    @Autowired
    public WallPostService(AccountWallMessageDAO accountWallMessageDAO, GroupWallMessageDAO groupWallMessageDAO, AccountService accountService, GroupsService groupsService) {
        this.accountWallMessageDAO = accountWallMessageDAO;
        this.groupWallMessageDAO = groupWallMessageDAO;
        this.accountService = accountService;
        this.groupsService = groupsService;
    }

    public int addPostToAccountWall(int senderId, int recipientId, @NonNull String text) {
        try {
            return accountWallMessageDAO.create(new Message(accountService.getAccount(senderId), recipientId, text, false));
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deletePostFromAccountWall(int id) {
        try {
            accountWallMessageDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deleteAllPostsFromAcccountWall(@NonNull Account recipient) {
        try {
            accountWallMessageDAO.delete(recipient.getId());
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Message> getAllPostsFromAccountWall(@NonNull Account account) {
        try {
            return accountWallMessageDAO.getAll(account.getId());
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public Message addPostToGroupWall(int senderId, int groupId, @NonNull String text) {
        Message m = new Message(accountService.getAccount(senderId), groupId, text, true);
        try {
            groupWallMessageDAO.create(m);
            return m;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deletePostFromGroupWall(int id) {
        try {
            groupWallMessageDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deleteAllPostsFromGroupWall(@NonNull Group group) {
        try {
            groupWallMessageDAO.delete(group.getId());
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Message> getAllPostsFromGroupWall(@NonNull Group group) {
        try {
            return groupWallMessageDAO.getAll(group.getId());
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}