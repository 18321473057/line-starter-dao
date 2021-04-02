package com.line.base.linestarterdaodatasource.dynamic.configuration;

import com.line.base.linestarterdaodatasource.dynamic.DatasourceChangeAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yangcs
 * @Date: 2020/10/23 14:46
 * @Description: 动态数据源切换配置 ;
 * DatasourceChangeAspect 切面也必须加入spring;
 */
@Configuration
@ConditionalOnProperty(prefix = "line.dynamic.datasource", name = "enable",matchIfMissing = true)
public class DatasourceChangeConfiguration {

    /**
     * 声明多数据源切换切面
     */
    @Bean
    public DatasourceChangeAspect datasourceChangeAspect() {
        DatasourceChangeAspect changeAspect = new DatasourceChangeAspect();
        return changeAspect;
    }

}
