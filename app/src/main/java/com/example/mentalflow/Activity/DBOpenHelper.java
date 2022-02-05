package com.example.mentalflow.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// 数据库驱动
public class DBOpenHelper {
    private static String diver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://sh-cynosdbmysql-grp-8tahhnfe.sql.tencentcdb.com:29927/Mentalflow_all";
    private static String user = "root";//用户名
    private static String password = "12345678a|";//密码

    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(diver);
            conn = (Connection) DriverManager.getConnection(url,user,password);//获取连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
