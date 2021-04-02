package com.line.base.dao.linestarterdaobean.factory;


import com.line.base.dao.linestarterdaobean.handler.MsTableDefinitionHandler;
import com.line.base.dao.linestarterdaobean.pojo.TableDefinition;
import com.line.base.dao.linestarterdaobean.pojo.TableDefinitionAware;
import com.line.base.dao.linestarterdaobean.utils.Assert;
import com.line.base.linestarterdaodatasource.datasource.DynamicDatasource;
import com.line.base.linestarterdaodatasource.dynamic.ContextDatasourceTypeHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 14:33
 * @Description:
 */
public abstract class AbstrctMysqlMateBeanFactory implements IMateBeanFactory, InitializingBean, ApplicationContextAware {

    private ConcurrentHashMap<String, ConcurrentHashMap<String, TableDefinition>> tableMap = new ConcurrentHashMap();

    private MsTableDefinitionHandler msTableDefinitionHandler = new MsTableDefinitionHandler();
    private ApplicationContext applicationContext;

    private DataSource datasource;
    Log logger = LogFactory.getLog(AbstrctMysqlMateBeanFactory.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AbstrctMysqlMateBeanFactory is init!!!!");
    }

    //初始化元数据工厂,定义初始化 算法骨架
    public void init() {
        //清空map
        flushTableMap();
        //获取连接
        this.datasource = getDatasourceAware();

        //获取原始表对象
        Map<String, TableDefinitionAware> tda = originTableDefinition();
        //注册到map中

    }

    private Map<String, TableDefinitionAware> originTableDefinition() {
        //必须是自己定义的数据源, 不然没法获取数据源列表
        if (!(datasource instanceof DynamicDatasource)) {
            logger.error("datasource is not  instanceof DynamicDatasource,cont supper!!!!");
            return Collections.emptyMap();
        }

        Map<String, DataSource> pubDatasources = ((DynamicDatasource) datasource).getPubDatasources();
        Map<String, TableDefinitionAware> tableDefintionMap = new HashMap<>(pubDatasources.size());
        try {
            for (Map.Entry<String, DataSource> dsEntry : pubDatasources.entrySet()) {
                ContextDatasourceTypeHolder.setDatasourceType(dsEntry.getKey());
                DataSource ds = applicationContext.getBean(DataSource.class);
                tableDefintionMap.put(dsEntry.getKey(), resolveTableDf(ds, dsEntry.getKey()));
            }
        } catch (Exception e) {
            throw new RuntimeException(">>>>>>>>解析表结构是 发生了异常");
        }
        logger.info(">>>>>>>> 连接所属的表解析完成!");
        return tableDefintionMap;
    }

    //获取表定义对象持有者
    protected TableDefinitionAware resolveTableDf(DataSource ds, String key) throws SQLException {
        return msTableDefinitionHandler.createTableDF(ds, key);
    }

    protected abstract DataSource getDatasourceAware();

    protected void flushTableMap() {
        tableMap.clear();
    }


    @Override
    public Object getTable(String name) {
        Assert.notEmpty(name, "获取表元数据对象失败,入参name为null");
        return tableMap.get(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
