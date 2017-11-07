package com.canary.biz.acl.repo;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 基础mapper定义,可以自己进行重新定义</p>
 * <p>Company: 静之殇工作室</p>
 */
public interface BaseMapper<T, ID extends Serializable> {

    int insert(T t);

    int insertBatch(List<T> t);

    int deleteBatchById(List<ID> ids);

    int deleteById(@Param("id") ID id);

    int deleteByUUID(String uuid);

    int update(T t);

    T find(Map<String, Object> parameter);

    T findById(@Param("id") ID id);

    T findByUUID(@Param("uuid") String uuid);

    T findByName(@Param("name") String name);

    List<T> queryListAll(Map<String, Object> parameter);

    List<T> queryListByPage(Map<String, Object> parameter);

    int count(Map<String, Object> parameter);

}
