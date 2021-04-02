package com.line.base.dao.linestarterdaobean.utils;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 15:48
 * @Description:
 */
public class DSNameUtils {
    private static final StringBuffer preBuffer = new StringBuffer("DSNAME");

    public String getName(String name) {
        return preBuffer.append(name).toString();
    }
}
