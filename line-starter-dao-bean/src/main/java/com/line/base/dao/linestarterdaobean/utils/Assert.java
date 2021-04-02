package com.line.base.dao.linestarterdaobean.utils;

import org.springframework.util.StringUtils;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 15:08
 * @Description:
 */
public class Assert {

    public static void notEmpty(String args, String msg) {
        if (StringUtils.isEmpty(args)) {
            throw new NullPointerException(msg);
        }
    }
}
