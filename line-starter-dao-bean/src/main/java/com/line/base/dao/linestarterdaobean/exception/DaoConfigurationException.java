package com.line.base.dao.linestarterdaobean.exception;

/**
 * @Author: yangcs
 * @Date: 2021/3/6 14:49
 * @Description: dao层配置失败异常
 */
public class DaoConfigurationException extends RuntimeException {

    private String msg;

    public DaoConfigurationException() {

    }

    public DaoConfigurationException(String msg) {
        this.msg = msg;
    }


}
