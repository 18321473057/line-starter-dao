package com.line.base.dao.linestarterdaobean.mandatory;

import com.line.base.dao.linestarterdaobean.exception.DaoConfigurationException;
import com.line.base.dao.linestarterdaobean.exception.DaoUnsupperException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * @Author: yangcs
 * @Date: 2021/3/8 14:56
 * @Description: 数据源持有对象
 */
public class DatasourceAware implements ApplicationContextAware, InitializingBean {

    private DataSource dataSource;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dataSource = applicationContext.getBean(DataSource.class);
        if (dataSource == null) {
            throw new DaoConfigurationException("spring容器中没有配置Datasource!!!");
        } else if (!(dataSource instanceof AbstractRoutingDataSource)) {
            throw new DaoUnsupperException("目前只持支 AbstractRoutingDataSource 数据源");
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
