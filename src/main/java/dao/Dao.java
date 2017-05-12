package dao;


import exceptions.DataBaseException;
import model.Item;

import java.sql.Connection;
import java.util.List;

public interface Dao <T extends Item> {
    List<T> getAll() throws DataBaseException;
    T getById(long id) throws DataBaseException;
    void deleteById(long id) throws DataBaseException;
    void insert(T item) throws DataBaseException;
    void update(long id, T item) throws DataBaseException;
}
