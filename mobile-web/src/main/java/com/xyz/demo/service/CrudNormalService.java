package com.xyz.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyz.demo.dao.CrudDao;
import com.xyz.demo.pojo.DataEntity;
import com.xyz.demo.vo.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class CrudNormalService<T extends DataEntity,D extends CrudDao<T>> {
    @Autowired
    public void setDao(D dao) {
        this.dao = dao;
    }

    protected D dao;

    public Boolean save(T entity){
        if(entity.getId() == null){
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            dao.insert(entity);
        }else{
            entity.setUpdateTime(new Date());
            dao.update(entity);
        }
        return true;
    }

    public PageInfo<T> findList(T entity, PageParam pageParam){
        if(pageParam == null){
            PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
        }
        List<T> list = dao.findList(entity);
        return new PageInfo<>(list);
    }

    public Boolean delete(T entity){
        dao.delete(entity);
        return true;
    }

    public T get(Integer id){
        return dao.get(id);
    }

    public T getByEntity(T entity){
        return dao.getByEntity(entity);
    }
}
