package com.line.base.dao.linestarterdaobean.mandatory;

import com.line.base.dao.linestarterdaobean.factory.MysqlMateBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author: yangcs
 * @Date: 2021/3/8 15:19
 * @Description:
 */

@Configuration
@ConditionalOnProperty(prefix = "line.dao.base", name = "enable")
public class DaoDatasourceConfiguration {

    @Bean
    public DatasourceAware datasourceAware() {
        return new DatasourceAware();
    }

    @Bean
    @ConditionalOnBean(DatasourceAware.class)
    @Primary
    public MysqlMateBeanFactory mysqlMateBeanFactory(){
       return new MysqlMateBeanFactory(datasourceAware());
    }

}
