package com.example.mentalflow.Activity;

import com.example.mentalflow.Activity.Entity.PretestRes;
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
        try (Connection conn = DBOpenHelper.getConn();
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
        try (Connection conn = DBOpenHelper.getConn();
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

    // 插入新注册的用户数据
    public int reg_insert_userInfo(UserInfo userInfo) {

        String sql = "insert into user(id,phone,password,name,gender,age) values (?,?,?,?,?,?)";
        int new_id = 0;
        try (Connection conn = DBOpenHelper.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,userInfo.getId());
            stmt.setString(2, userInfo.getPhone());
            stmt.setString(3, userInfo.getPassword());
            stmt.setString(4,userInfo.getName());
            stmt.setString(5,userInfo.getGender());
            stmt.setInt(6,userInfo.getAge());
            stmt.executeUpdate();
            //获取自增产生的用户id
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                new_id = rs.getInt(1); //必须放在里面
            }
            System.out.println("新产生的id为"+new_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new_id;
    }

    // 插入新注册用户的初量表结果
    public void reg_insert_test0(int id, int[] res) {
        String sql = "insert into pretest values (?,?,?,?,?,?)";
        try (Connection conn = DBOpenHelper.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,id);
            for(int i=0;i<5;i++) {
                stmt.setInt(i+2,res[i]);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询账号密码是否匹配：如果匹配，返回用户类，否则返回false
    public PretestRes test_search_pretest(int id) {

        String sql = "select * from pretest where user_id = ?";
        try (Connection conn = DBOpenHelper.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    PretestRes pretestRes = new PretestRes();
                    pretestRes.setEmotion(rs.getInt(2));
                    pretestRes.setPsychology(rs.getInt(3));
                    pretestRes.setRelationship(rs.getInt(4));
                    pretestRes.setStudy(rs.getInt(5));
                    pretestRes.setAbility(rs.getInt(6));
                    return pretestRes;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
