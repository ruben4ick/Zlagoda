package com.zlagoda.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>
 *            object that dao work with
 * @param <K>
 *            object key
 */
public interface GenericDao<T, K> {

    List<T> getAll();

    Optional<T> getById(K id);

    void create(T e);

    void update(T e);

    void delete(K id);
}
