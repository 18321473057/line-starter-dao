package com.line.base.dao.linestarterdaobean.pojo;

/**
 * @Author: yangcs
 * @Date: 2021/3/9 9:18
 * @Description: 字段定义对象
 */
public class ColumnDefinition implements Comparable<ColumnDefinition> {

    private String name;
    private String length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public ColumnDefinition(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ColumnDefinition{" +
                "name='" + name + '\'' +
                ", length='" + length + '\'' +
                '}';
    }


    @Override
    public int compareTo(ColumnDefinition o) {
        if (this.name.equals(o.getName())) {
            return 0;
        }
        return this.name.hashCode() > o.getName().hashCode() ? -1 : 1;
    }
}
