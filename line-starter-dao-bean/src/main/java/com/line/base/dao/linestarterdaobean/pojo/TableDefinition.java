package com.line.base.dao.linestarterdaobean.pojo;

import java.util.Set;

/**
 * @Author: yangcs
 * @Date: 2021/3/9 9:18
 * @Description: 表定义对象
 */
public class TableDefinition {

    private String tableName;
    private Set<ColumnDefinition> columns;

    public TableDefinition() {
    }

    public TableDefinition(String tableName, Set<ColumnDefinition> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<ColumnDefinition> getColumns() {
        return columns;
    }

    public void setColumns(Set<ColumnDefinition> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableDefinition{" +
                "tableName='" + tableName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
