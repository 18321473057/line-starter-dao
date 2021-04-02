package com.line.base.linestarterdaodatasource.datasource;

import com.line.base.linestarterdaodatasource.dynamic.ContextDatasourceTypeHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 动态数据源提供者
 * Spring boot提供了AbstractRoutingDataSource 根据用户定义的规则选择当前的数据源，
 * 这样我们可以在执行sql之前，设置使用的数据源。实现可动态路由的数据源，
 * 在每次数据库查询操作前执行。它的抽象方法 determineCurrentLookupKey() 决定使用哪个数据源。
 * <p>
 * <p>
 * private Map<Object, DataSource> resolvedDataSources; key:lookupKey ,value :DataSource
 * <p>
 * <p>
 * 1 定义DynamicDataSource类继承抽象类AbstractRoutingDataSource，并实现了determineCurrentLookupKey()方法。
 * 把配置的多个数据源会放在AbstractRoutingDataSource的 targetDataSources和defaultTargetDataSource中，
 * 2 然后通过afterPropertiesSet()方法将数据源分别进行复制到resolvedDataSources和resolvedDefaultDataSource中。
 * 3 调用AbstractRoutingDataSource的getConnection()的方法的时候，
 * 先调用determineTargetDataSource()方法返回DataSource在进行getConnection()。
 */
public class DynamicDatasource extends AbstractRoutingDataSource {

    //对外提供一个可以 访问的数据源列表
    private static final Map<String, DataSource> pubDatasources = new HashMap();

    /**
     * 从Holder中获取当前操作的数据源类型，交由spring去获取相应的数据源
     */
    protected Object determineCurrentLookupKey() {
        return ContextDatasourceTypeHolder.getDatasourceType();
    }

    //常量不可更改,重新赋值
    public void setPubDatasources(Map<String, DataSource> datasourceMap) {
        datasourceMap.entrySet().forEach(e -> {
            pubDatasources.put(e.getKey(), e.getValue());
        });
    }

    public static Map<String, DataSource> getPubDatasources() {
        return pubDatasources;
    }
}
