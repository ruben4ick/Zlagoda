package com.zlagoda.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, K> {

    List<T> getAll();

    Optional<T> getById(K id);

    void create(T e);

    void update(T e);

    void delete(K id);

}
