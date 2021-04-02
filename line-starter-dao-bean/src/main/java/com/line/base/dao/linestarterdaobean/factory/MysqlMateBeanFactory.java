package com.line.base.dao.linestarterdaobean.factory;


import com.line.base.dao.linestarterdaobean.mandatory.DatasourceAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 14:33
 * @Description: mysql 表元数据工厂 实现
 */
public class MysqlMateBeanFactory extends AbstrctMysqlMateBeanFactory {

    private DatasourceAware datasourceAware;

    public MysqlMateBeanFactory(DatasourceAware datasourceAware) {
        this.datasourceAware = datasourceAware;
    }

    //设置
    @Override
    protected DataSource getDatasourceAware() {
        return datasourceAware.getDataSource();
    }


}
