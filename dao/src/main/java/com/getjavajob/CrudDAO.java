package com.getjavajob;

import com.getjavajob.exceptions.DaoException;

import java.util.List;

public interface CrudDAO<T> {

    public int create(T t) throws DaoException;

    public T read(int id) throws DaoException;

    public void update(T t) throws DaoException;

    public void delete(int id) throws DaoException;

    public List<T> getAll() throws DaoException;
}