package com.monkey.springcloud.service;

import com.monkey.springcloud.entities.Dept;

import java.util.List;

/**
 * @author: monkey
 * @date: 2018/9/17 11:26
 */
public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();
}
