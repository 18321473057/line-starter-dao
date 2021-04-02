package com.line.base.linestarterdaodatasource.datasource.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.line.base.linestarterdaodatasource.datasource.properties.DatasourceProperties;
import com.line.base.linestarterdaodatasource.datasource.properties.DruidProperties;
import com.line.base.linestarterdaodatasource.datasource.DynamicDatasource;
import com.line.base.linestarterdaodatasource.dynamic.configuration.DatasourceChangeConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangcs
 * @Date: 2020/10/23 14:46
 * @Description: DatasourceChangeAspect 切面也必须加入spring
 */
@Configuration
@EnableConfigurationProperties(DatasourceProperties.class)
@ConditionalOnProperty(prefix = "line.dynamic.datasource", name = "enable",matchIfMissing = true)
public class DatasourceConfiguration {

    @Autowired
    private DatasourceProperties datasourceProperties;

    Log logger = LogFactory.getLog(DatasourceChangeConfiguration.class);

    /**
     * 创建动态切换的数据源
     */
    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        List<DruidProperties> datasourceListProperties = datasourceProperties.getDatasourceList();
        Map targetDataSources = new HashMap();
        Object defaultDataSource = null;
        for (DruidProperties druidProperties : datasourceListProperties) {
            String datasourceName = druidProperties.getName();
            DataSource druidDatasource = createDruidDatasource(druidProperties);
            targetDataSources.put(datasourceName, druidDatasource);
            if (datasourceProperties.getDefaultDatasourceName().equals(druidProperties.getName())) {
                defaultDataSource = druidDatasource;
            }
        }

        DynamicDatasource datasource = new DynamicDatasource();
        datasource.setTargetDataSources(targetDataSources);
        //DynamicDatasource实现AbstractRoutingDataSource ,
        // AbstractRoutingDataSource没有获取数据源列表的方式, 只能新增一个可以被获取的字段获取数据源列表;
        datasource.setPubDatasources(targetDataSources);
        datasource.setDefaultTargetDataSource(defaultDataSource == null ? datasourceProperties.getDefaultDatasourceName() : defaultDataSource);
        return datasource;
    }

    /**
     * 根据给定的配置创建数据源
     */
    private DataSource createDruidDatasource(DruidProperties druidProperties) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setName(druidProperties.getName());
        datasource.setUrl(druidProperties.getUrl());
        datasource.setUsername(druidProperties.getUsername());
        datasource.setPassword(druidProperties.getPassword());
        datasource.setDriverClassName(druidProperties.getDriverClassName());
        datasource.setInitialSize(druidProperties.getInitialSize());
        datasource.setMinIdle(druidProperties.getMinIdle());
        datasource.setMaxActive(druidProperties.getMaxActive());
        datasource.setMaxWait(druidProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidProperties.getValidationQuery());
        datasource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        datasource.setTestOnReturn(druidProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        try {
            datasource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }
}
