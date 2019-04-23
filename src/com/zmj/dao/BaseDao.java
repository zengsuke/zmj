package com.zmj.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    private static final String url = "jdbc:mysql://localhost:3306/dvdproject";//链接数据库的url
    private static Connection connection = null;//连接

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");//数据库类型
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }
    }

    public static Connection getConnection() {//连接
        try {
            connection = DriverManager.getConnection(url, "root", "root");//连接用户和密码
            return connection;
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {//关闭所有
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }


    public static boolean excuteUpdate(String sql, List<Object> params) {//增删改
        int res = 0;
        Connection conn = null;//连接
        PreparedStatement pstmt = null;//准备工作
        ResultSet rs = null;//返回值
        try {
            conn = getConnection();//开始连接
            pstmt = conn.prepareStatement(sql);//准备sql语句
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));//获取前端输入的数据
                }
            }
            res = pstmt.executeUpdate();//根据sql语句进行增删改

        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return res > 0;//res大于0则成功
    }

    public static <T>List<T> executeAll(String sql,Class<T> cls){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<T> data=new ArrayList<T>();
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            while (rs.next()) {
                T m = cls.newInstance();
                for (int i = 0; i < rsd.getColumnCount(); i++) {
                    String col_name = rsd.getColumnName(i + 1);
                    Object value = rs.getObject(col_name);
                    Field field = cls.getDeclaredField(col_name);
                    field.setAccessible(true);
                    field.set(m, value);
                }
                data.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }finally {
            closeAll(rs, pstmt, conn);
        }
        return data;
    }

    public static <T> List<T> executeQuery(String sql,Class<T> cls ,List<Object>params) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> data = new ArrayList<T>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            while (rs.next()) {
                T m = cls.newInstance();
                for (int i = 0; i < rsd.getColumnCount(); i++) {
                    String col_name = rsd.getColumnName(i + 1);
                    Object value = rs.getObject(col_name);
                    Field field = cls.getDeclaredField(col_name);
                    field.setAccessible(true);
                    field.set(m, value);
                }
                data.add(m);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return data;
    }
}
