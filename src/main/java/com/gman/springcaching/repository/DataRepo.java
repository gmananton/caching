package com.gman.springcaching.repository;

import java.util.Optional;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
public interface DataRepo<T, K> {

    public Optional<T> getByKey(K key);

    public void save(T entity);

    public void update(T entity);

}
