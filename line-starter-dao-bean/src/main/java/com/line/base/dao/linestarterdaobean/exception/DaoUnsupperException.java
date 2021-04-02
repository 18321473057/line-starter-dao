package com.line.base.dao.linestarterdaobean.exception;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 14:49
 * @Description: 本项目不支持的操作
 */
public class DaoUnsupperException extends UnsupportedOperationException {


    private String msg;


    public DaoUnsupperException(String msg) {
        this.msg = msg;
    }

    public DaoUnsupperException() {
    }
}
