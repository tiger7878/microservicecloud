package com.monkey.springcloud.dao;

import com.monkey.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: monkey
 * @date: 2018/9/17 11:23
 */
@Mapper //springboot+mybatis整合时需要标注此注解
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();

}
