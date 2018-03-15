package com.getjavajob;


import com.getjavajob.common.Group;
import com.getjavajob.exceptions.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "groupDao")
public class GroupDAO implements CrudDAO<Group> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Group group) throws DaoException {
        return entityManager.merge(group).getId();
    }

    @Override
    public Group read(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void update(Group group) throws DaoException {
        entityManager.merge(group);
    }

    @Override
    public void delete(int id) throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Group> query = builder.createCriteriaDelete(Group.class);
        Root<Group> root = query.from(Group.class);
        query.where(builder.lessThanOrEqualTo(root.get("id"), id));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public List<Group> getAll() throws DaoException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> query = builder.createQuery(Group.class);
        Root<Group> root = query.from(Group.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }
}