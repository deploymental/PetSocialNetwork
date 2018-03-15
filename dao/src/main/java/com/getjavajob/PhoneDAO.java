package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.Phone;
import com.getjavajob.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "phoneDao")
public class PhoneDAO implements CrudDAO<Phone> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Phone phone) throws DaoException {
        return entityManager.merge(phone).getId();
    }

    @Override
    public Phone read(int id) throws DaoException {
        return getPhone("id", id);
    }

    public Phone read(Account ac) throws DaoException {
        return getPhone("id", ac.getId());
    }

    public Phone getPhone(String field, Object o) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        criteriaQuery.select(root).where(builder.equal(root.get(field), o));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void update(Phone phone) throws DaoException {
        entityManager.merge(phone);
    }

    @Override
    public void delete(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Phone> query = builder.createCriteriaDelete(Phone.class);
        Root<Phone> root = query.from(Phone.class);
        query.where(builder.lessThanOrEqualTo(root.get("id"), id));
        entityManager.createQuery(query).executeUpdate();
    }

    public void deleteAllFromAccount(int accountId) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Account> query = builder.createCriteriaDelete(Account.class);
        Root<Account> root = query.from(Account.class);
        query.where(builder.equal(root.get("account"), accountId));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public List<Phone> getAll() throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Phone> query = builder.createQuery(Phone.class);
        Root<Phone> root = query.from(Phone.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    public List<Phone> getAll(int accountId) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Phone> query = builder.createQuery(Phone.class);
        Root<Phone> root = query.from(Phone.class);
        query.select(root).where(builder.equal(root.get("account"), accountId));
        return entityManager.createQuery(query).getResultList();
    }
}