package com.example.two.config;

public class DataSourceContextHolder {

//    private static final String DEFAULT_DATASOURCE = "PRIMARY_DATASOURCE";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setDataSource(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }
}
