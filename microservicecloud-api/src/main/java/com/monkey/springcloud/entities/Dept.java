package com.monkey.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 公共的实体
 * @author: monkey
 * @date: 2018/9/17 11:04
 */
@NoArgsConstructor //无参构造函数
//@AllArgsConstructor //全参构造函数
@Data //生成getter和setter
@Accessors(chain = true) //链式编程，setter返回的是实体，可以一直setter下去
public class Dept implements Serializable {

    private Long 	deptno; // 主键
    private String 	dname; // 部门名称
    private String 	db_source;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

    public Dept(String dname)
    {
        super();
        this.dname = dname;
    }

}
