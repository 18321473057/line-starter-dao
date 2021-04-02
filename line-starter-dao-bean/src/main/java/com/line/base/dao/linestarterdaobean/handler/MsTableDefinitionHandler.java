package com.line.base.dao.linestarterdaobean.handler;

import com.line.base.dao.linestarterdaobean.pojo.ColumnDefinition;
import com.line.base.dao.linestarterdaobean.pojo.TableDefinition;
import com.line.base.dao.linestarterdaobean.pojo.TableDefinitionAware;
import com.line.base.dao.linestarterdaobean.sql.DdlSqlConstants;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @Author: yangcs
 * @Date: 2021/3/9 9:18
 * @Description: mysql表定义处理者
 */
public class MsTableDefinitionHandler {

    private static ApplicationContext applicationContext;

    public List<String> getAllTables(DataSource ds, String key) throws SQLException {
        List<String> tableNames = new ArrayList<>();
        Statement statement = ds.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(DdlSqlConstants.ALL_TABLENAME);
        while (rs.next()) {
            tableNames.add(rs.getString("TABLE_NAME"));
        }
        return tableNames;
    }

    public TableDefinitionAware createTableDF(DataSource ds, String key) throws SQLException {
        //获取所有表明
        List<String> allTables = getAllTables(ds, key);
        //表定义对象
        List<TableDefinition> tableDefinitions = new ArrayList<>();
        for (String tableName : allTables) {
            System.out.println(tableName);
            //解析表结构
            tableDefinitions.add(parsetableDf(ds, tableName));
        }
//TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
        //拿到了表结构  没有获取字段长度.......觉得没啥用呀 这个组件;不想写了

        //////////////////////////////////////
        for (TableDefinition tableDefinition : tableDefinitions) {
            System.out.println(tableDefinition);
        }
        ////////////////////////////////////////


        return new TableDefinitionAware(tableDefinitions);
    }

    private TableDefinition parsetableDf(DataSource ds, String tableName) {
        Set<ColumnDefinition> columndf = new TreeSet<>();
        Set<String> columns = getTableColumns(ds, tableName);
        for (String column : columns) {
            columndf.add(new ColumnDefinition(column));
        }
        return new TableDefinition(tableName, columndf);
    }


    /**
     * 获取表列列表
     *
     * @param tableName
     * @return
     */
    private Set<String> getTableColumns(DataSource ds, String tableName) {
        try {
            Connection connection = ds.getConnection();
            Set<String> result = new HashSet<>();
            ResultSet rs = connection.getMetaData().getColumns(null, null, tableName, "%");

            while (rs.next()) {
                result.add(rs.getString("COLUMN_NAME"));
            }
            return result;
        } catch (SQLException e) {

        }
        return Collections.EMPTY_SET;
    }

}
