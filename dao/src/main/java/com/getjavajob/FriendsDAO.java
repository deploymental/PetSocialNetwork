package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.Friend;
import com.getjavajob.common.enums.AccountRelationStatus;
import com.getjavajob.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "friendsDao")
public class FriendsDAO implements CrudDAO<Friend> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Friend friend) throws DaoException {
        return entityManager.merge(friend).getId();
    }

    @Override
    public Friend read(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friend> criteriaQuery = builder.createQuery(Friend.class);
        Root<Friend> root = criteriaQuery.from(Friend.class);
        criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void update(Friend friend) throws DaoException {
        entityManager.merge(friend);
    }

    @Override
    public void delete(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Friend> query = builder.createCriteriaDelete(Friend.class);
        Root<Friend> root = query.from(Friend.class);
        query.where(builder.lessThanOrEqualTo(root.get("id"), id));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public List<Friend> getAll() throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friend> query = builder.createQuery(Friend.class);
        Root<Friend> root = query.from(Friend.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    public List<Friend> getAllFollowers(int accountId) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friend> query = builder.createQuery(Friend.class);
        Root<Friend> root = query.from(Friend.class);
        query.select(root).where(builder.equal(root.get("recipient"), accountId),
                builder.equal(root.get("relation"), AccountRelationStatus.FOLLOWER));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Friend> getAllFriends(int accountId) throws DaoException {
        List<Friend> friends = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friend> query = builder.createQuery(Friend.class);
        Root<Friend> root = query.from(Friend.class);
        query.select(root).where(builder.equal(root.get("sender"), accountId),
                builder.equal(root.get("relation"), AccountRelationStatus.FRIEND));
        friends.addAll(entityManager.createQuery(query).getResultList());
        query.select(root).where(builder.equal(root.get("recipient"), accountId),
                builder.equal(root.get("relation"), AccountRelationStatus.FRIEND));
        friends.addAll(entityManager.createQuery(query).getResultList());
        return friends; // TODO: 3/8/2018 wtf
    }


    public void deleteRequest(Account sender, Account recipient) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Friend> query = builder.createCriteriaDelete(Friend.class);
        Root<Friend> root = query.from(Friend.class);
        query.where(builder.equal(root.get("sender"), sender.getId()), builder.equal(root.get("recipient"), recipient.getId()));
        entityManager.createQuery(query).executeUpdate();
        query.where(builder.equal(root.get("recipient"), sender.getId()), builder.equal(root.get("sender"), recipient.getId()));
        entityManager.createQuery(query).executeUpdate();
    }

    public void acceptRequest(Account sender, Account recipient) throws DaoException {
        update(new Friend(sender, recipient, AccountRelationStatus.FRIEND));
    }

    public AccountRelationStatus getRelation(Account sender, Account recipient) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friend> criteriaQuery = builder.createQuery(Friend.class);
        Root<Friend> root = criteriaQuery.from(Friend.class);
        try {
            criteriaQuery.select(root).where(builder.equal(root.get("sender"), sender.getId()), builder.equal(root.get("recipient"), recipient.getId()));
            return entityManager.createQuery(criteriaQuery).getSingleResult().getRelation();
        } catch (NoResultException e) {
        }
        try {
            criteriaQuery.select(root).where(builder.equal(root.get("sender"), recipient.getId()), builder.equal(root.get("recipient"), sender.getId()));
            return entityManager.createQuery(criteriaQuery).getSingleResult().getRelation();
        } catch (NoResultException e) {
            return null;
        }
    }
}
