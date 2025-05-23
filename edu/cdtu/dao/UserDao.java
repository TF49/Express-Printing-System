package edu.cdtu.dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.cdtu.utils.DbUtils;
import edu.cdtu.utils.UserSaveTool;

public class UserDao {
    /**
     * 用户登录功能
     *
     * @param username 登录界面输入的用户名
     * @param password 登录界面输入的密码
     * @return 登录后返回的结果boolean
     */

    public static boolean userLogin(String username, String password) {
        //1.获取数据库连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;

        try {
            //2. 编写SQL语句---通过用户名去查询用户，查询结果是获取到数据库中该用户的密码
            ps = conn.prepareStatement("select password from tb_user where username=?");
            //3.处理SQL语句的参数（即处理？的值）---处理username的值
            ps.setString(1, username);
            //4. 执行SQL
            ResultSet rs = ps.executeQuery();
            //5.处理结果--若查询有结果，则处理结果，若没有，提示用户不存在，并返回false
            if (rs.next() && rs.getRow() > 0) {
                // 查询后的密码
                String resPassword = rs.getString(1);
                //查询后的密码与用户输入的密码相同，返回true，否则提示密码错误，并返回faLse
                if (resPassword.equals(password)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户不存在");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库异常");
            return false;
        } finally {
            try {
                //6、关流
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkUserName(String username) {
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select username from tb_user where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getRow() > 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库异常");
            return false;
        } finally {
            try {
                //关流
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean addUser(String username, String password) {
        //1.获取数据库连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;

        try {
            //2.数据库语句
            ps = conn.prepareStatement("insert into tb_user (username,password) values(?,?)");
            //3. 数据库语句的参数？
            ps.setString(1, username);
            ps.setString(2, password);

            //4. 执行SQL
            int i = ps.executeUpdate();
            //5.处理结果
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "添加成功");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "添加失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库异常");
            return false;
        } finally {
            try {
                //6、关流
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改个人密码功能
     *
     * @param password 新密码
     * @return 是否修改成功
     */
    public static boolean updateUserPass(String password) {
        // 1. 获取数据库连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;

        try {
            // 2. 数据库语句
            ps = conn.prepareStatement("update tb_user set password = ? where username = ?");

            // 3. 数据库语句的参数
            ps.setString(1, password); // 设置新密码
            ps.setString(2, UserSaveTool.getcurrentLoginUsername()); // 设置当前登录用户名

            // 4. 执行 SQL
            int i = ps.executeUpdate();

            // 5. 处理结果
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "修改成功");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "修改失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库异常");
            return false;
        } finally {
            try {
                // 6. 关流
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


