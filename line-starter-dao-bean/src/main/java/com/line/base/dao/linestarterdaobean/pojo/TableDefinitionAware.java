package com.line.base.dao.linestarterdaobean.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangcs
 * @Date: 2021/3/9 9:18
 * @Description: 表定义对象
 */


public class TableDefinitionAware {
    public List<TableDefinition> tableDefinitions = new ArrayList<>();


    public List<TableDefinition> getTableDefinitions() {
        return tableDefinitions;
    }

    public void setTableDefinitions(List<TableDefinition> tableDefinitions) {
        this.tableDefinitions = tableDefinitions;
    }


    public TableDefinitionAware(List<TableDefinition> tableDefinitions) {
        this.tableDefinitions = tableDefinitions;
    }

    public TableDefinitionAware() {
    }
}
