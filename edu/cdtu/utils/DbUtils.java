package edu.cdtu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class DbUtils
{
    private java.sql.Connection dbConnection;

    //静态代码块加载数据库驱动
    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            //若报错，则提示报错解决方案
            JOptionPane.showMessageDialog(null,"数据库驱动加载失败，请将驱动包加载到项目下");
        }
    }
    //获取数据库连接
    public static Connection getconn()
    {
        try
        {
            Connection conn = null;// 定义数据库连接
            String url = "jdbc:mysql://127.0.0.1:3306/db_express_app";//项目对应的数据库URL
            String username = "root";// 数据库的用户名
            String password = "200649";// 数据库密码
            conn = DriverManager.getConnection(url, username, password); // 建立连接
            return conn;// 返回连接
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "数据库连接失败，请确认数据库用户名和密码是否正确。");
            return null;
        }
    }
    //测试
    public static void main(String[] arges)
    {
        System.out.println(getconn());
    }
}