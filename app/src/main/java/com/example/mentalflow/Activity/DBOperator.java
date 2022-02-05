package com.example.mentalflow.Activity;

import com.example.mentalflow.Activity.Entity.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// 操作数据库
public class DBOperator {

    // 查询账号(手机号）是否存在
    public boolean login_search_phone(String phone) {
        String sql = "select * from user where phone = ?";
        System.out.println(phone);
        try (
            Connection conn = DBOpenHelper.getConn();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1,phone);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        System.out.println("true");
                        return true;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; //账号不存在
    }

    // 查询账号密码是否匹配：如果匹配，返回用户类，否则返回false
    public Object login_search_psw(String phone, String password) {
        String sql = "select * from user where phone = ? and password = ?";
        System.out.println(phone);
        try (
                Connection conn = DBOpenHelper.getConn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,phone);
            stmt.setString(2,password);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(rs.getInt(1));
                    userInfo.setPhone(rs.getString(2));
                    userInfo.setPassword(rs.getString(3));
                    userInfo.setName(rs.getString(4));
                    userInfo.setGender(rs.getString(5));
                    userInfo.setAge(rs.getInt(6));
                    userInfo.setIntro(rs.getString(7));
                    return userInfo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; //账号密码不匹配
    }

    // 查询测试列表
    public ArrayList<String> search_testList() {

        ArrayList<String> testList = new ArrayList<String>();
        Connection conn = null;
        conn = (Connection) DBOpenHelper.getConn();
        String sql = "select * from test";
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                testList.add(rs.getString(2));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    // 查询问题列表
    public ArrayList<String> search_queList(int test_id) {

        ArrayList<String> queList = new ArrayList<String>();
        Connection conn = null;
        conn = (Connection) DBOpenHelper.getConn();
        String sql = "select * from test";
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                queList.add(rs.getString(2));
            }
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queList;
    }

    // 查询选项列表
    public ArrayList<String> search_optList(int test_id,int que_id) {

        ArrayList<String> optList = new ArrayList<String>();

        return optList;
    }
}
