package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.Friend;
import com.getjavajob.common.enums.AccountRelationStatus;
import com.getjavajob.exceptions.DaoException;
import exception.ServiceException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "relationService")
public class FriendsRelationService {
    private FriendsDAO friendsDAO;
    private AccountService accountService;

    @Autowired
    public FriendsRelationService(@NonNull FriendsDAO friendsDAO, AccountService accountService) {
        this.friendsDAO = friendsDAO;
        this.accountService = accountService;
    }


    public void sendRequestToFriend(@NonNull Account sender, @NonNull Account recipient) {
        try {
            friendsDAO.update(new Friend(sender, recipient, AccountRelationStatus.FOLLOWER));
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void deleteRequest(@NonNull Account sender, @NonNull Account recipient) {
        try {
            friendsDAO.deleteRequest(sender, recipient);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void acceptFriend(@NonNull Account sender, @NonNull Account recipient) {
        try {
            friendsDAO.deleteRequest(sender, recipient);
            friendsDAO.create(new Friend(sender, recipient, AccountRelationStatus.FRIEND));
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Account> getFriends(@NonNull Account account) {
        List<Account> result = new ArrayList<>();
        try {
            for (Friend f : friendsDAO.getAllFriends(account.getId())) {
                if (f.getRecipient().getId() == account.getId()) {
                    result.add(accountService.getAccount(f.getSender().getId()));
                } else {
                    result.add(accountService.getAccount(f.getRecipient().getId()));
                }
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Account> getFollowers(@NonNull Account account) {
        List<Account> result = new ArrayList<>();
        try {
            for (Friend f : friendsDAO.getAllFollowers(account.getId())) {
                result.add(accountService.getAccount(f.getSender().getId()));
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    //@Transactional(propagation= Propagation.SUPPORTS)
    public int getCountFriends(@NonNull Account account) {
        return getFriends(account).size(); // TODO: 2/20/2018 Убрать эту ебанину
    }

    //@Transactional(propagation= Propagation.SUPPORTS)
    public int getCountFollowers(@NonNull Account account) {
        return getFollowers(account).size();
    }

    public List<Account> getRecipients(@NonNull Account account) {
        List<Account> result = new ArrayList<>();
        try {
            for (Friend f : friendsDAO.getAllFollowers(account.getId())) {
                result.add(accountService.getAccount(f.getRecipient().getId()));
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    //@Transactional(propagation= Propagation.SUPPORTS)
    public int getCountRecipients(@NonNull Account account) {
        return getRecipients(account).size();
    }

    public AccountRelationStatus getAccountRelation(Account sender, Account recipient) {
        if (sender.getId() == recipient.getId()) {
            return AccountRelationStatus.MY_ACCOUNT;
        }
        try {
            AccountRelationStatus relation = friendsDAO.getRelation(sender, recipient);
            if (relation == null) {
                return AccountRelationStatus.GUEST;
            }
            return relation;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
