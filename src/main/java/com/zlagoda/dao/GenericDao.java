package com.zlagoda.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    List<T> findAll();

    Optional<T> findById(K id);

    void create(T e);

    void update(T e);

    void delete(K id);
}
