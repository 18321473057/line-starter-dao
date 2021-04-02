package com.line.base.dao.linestarterdaobean.factory;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 14:29
 * @Description: 获取元数据的工厂
 */
public interface IMateBeanFactory {

    //根据表名获取表的元数据
    Object getTable(String name);

}
