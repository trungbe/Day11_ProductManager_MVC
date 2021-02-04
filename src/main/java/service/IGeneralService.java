package service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();

    T create(T t);

    T findById(int id);

    T findByName(String name);

    boolean update(T t);

    boolean delete(int id);
}
