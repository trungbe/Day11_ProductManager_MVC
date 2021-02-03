package service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();

    T save(T t);

    T updateById(int id, T t);

    T findById(int id);

    void deleteById(int id);

    T findByName(String name);
}
