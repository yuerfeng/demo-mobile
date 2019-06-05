package com.xyz.demo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrudDao<T> {
    T get(@Param("id") Integer id);

    T getByEntity(T entity);

    List<T> findList(T entity);

    Integer insert(T entity);

    Integer update(T entity);

    Integer delete(T entity);
}