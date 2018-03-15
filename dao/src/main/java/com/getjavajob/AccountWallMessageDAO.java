package com.getjavajob;

import com.getjavajob.common.Message;
import com.getjavajob.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "accountWallMessageDAO")
public class AccountWallMessageDAO implements CrudDAO<Message> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Message message) throws DaoException {
        return entityManager.merge(message).getId();
    }

    @Override
    public Message read(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void update(Message message) throws DaoException {
        entityManager.merge(message);
    }

    @Override
    public void delete(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Message> query = builder.createCriteriaDelete(Message.class);
        Root<Message> root = query.from(Message.class);
        query.where(builder.lessThanOrEqualTo(root.get("id"), id));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public List<Message> getAll() throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }


    public List<Message> getAll(int recipient) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root).where(builder.equal(root.get("recipient"), recipient), builder.equal(root.get("isgroup"), false));
        return entityManager.createQuery(query).getResultList();
    }
}