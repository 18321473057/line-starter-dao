package com.line.base.dao.linestarterdaobean.sql;

/**
 * @Author: yangcs
 * @Date: 2021/3/11 13:56
 * @Description:
 */
public class DdlSqlConstants {

    //ๆๆ่กจๅ
    public static  final  String ALL_TABLENAME = "select TABLE_NAME,TABLE_COMMENT from information_schema.TABLES where TABLE_SCHEMA=(select database())";



}
