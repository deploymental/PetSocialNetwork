package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "accountDao")
public class AccountDAO implements CrudDAO<Account> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int create(Account ac) throws DaoException {
        return entityManager.merge(ac).getId();
    }

    @Override
    public Account read(int id) throws DaoException {
        return entityManager.find(Account.class, id);
    }

    public Account read(String email) throws DaoException {
        return getAccount("email", email);
    }

    public Account getAccount(String field, Object o) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root).where(builder.equal(root.get(field), o));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void update(Account ac) throws DaoException {
        entityManager.merge(ac);
    }

    @Override
    public void delete(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Account> query = builder.createCriteriaDelete(Account.class);
        Root<Account> root = query.from(Account.class);
        query.where(builder.lessThanOrEqualTo(root.get("id"), id));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public List<Account> getAll() throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }
}