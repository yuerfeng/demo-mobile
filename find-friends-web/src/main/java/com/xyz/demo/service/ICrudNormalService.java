package com.xyz.demo.service;

import com.github.pagehelper.PageInfo;
import com.xyz.demo.dao.CrudDao;
import com.xyz.demo.pojo.DataEntity;
import com.xyz.demo.vo.PageParam;

public interface ICrudNormalService<T extends DataEntity,D extends CrudDao<T>> {
    Boolean save(T entity);

    PageInfo<T> findList(T entity, PageParam pageParam);

    Boolean delete(T entity);

    T get(Integer id);

    T getByEntity(T entity);
}
