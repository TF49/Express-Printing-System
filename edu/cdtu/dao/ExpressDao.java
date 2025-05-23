package edu.cdtu.dao;

import edu.cdtu.entity.Express;
import edu.cdtu.utils.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpressDao {
    /**
     * 添加快递功能
     * @param express 整个快递的信息
     * @return boolean类型true：添加成功;false：添加失败
     */
    public static boolean addExpress(Express express) {
        //1、获取连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;
        try {
            //2、编写SQL语句
            ps = conn.prepareStatement("insert into tb_express(sendName,sendPhone,sendCompany,sendAddress,sendPostcode,receiveName,receivePhone,receiveCompany,receiveAddress,receivePostcode)" + "values(?,?,?,?,?,?,?,?,?,?)");
            //3、处理SQL语句的参数
            ps.setString(1, express.getSendName());
            ps.setString(2, express.getSendPhone());
            ps.setString(3, express.getSendCompany());
            ps.setString(4, express.getSendAddress());
            ps.setString(5, express.getSendPostcode());
            ps.setString(6, express.getReceiveName());
            ps.setString(7, express.getReceivePhone());
            ps.setString(8, express.getReceiveCompany());
            ps.setString(9, express.getReceiveAddress());
            ps.setString(10, express.getReceivePostcode());

            //4、执行SQL
            int i = ps.executeUpdate();

            //5、判断结果
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "添加成功。");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "添加失败。");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错。");
            e.printStackTrace();
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
     * 根据快递id查询快递信息
     * @param id 快递id
     * @return Express 查询到的快递信息
     */

    public static Express selectExpressById(String id) {
        //1、获取连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //2、编写SQL语句
            ps = conn.prepareStatement("select * from tb_express where id = ?");
            int idInt = Integer.parseInt(id);
            //3、处理SQL语句的参数
            ps.setString(1, id);

            //4、执行SQL
            rs = ps.executeQuery();
            //5、判断结果
            if (rs.next() && rs.getRow() > 0) {
                Express express = new Express(Integer.parseInt(rs.getString(1)),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9),
                        rs.getString(10),rs.getString(11));
                return express;
            } else {
                JOptionPane.showMessageDialog(null, "还没有快递单");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错。");
            e.printStackTrace();
            return null;
        } finally {
            try {
                //6、关流
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询出快递单的总数
     * @return Integer  总数
     */

    public static Integer selectExpressCount() {
        //1、获取连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //2、编写SQL语句
            ps = conn.prepareStatement("select count(id) from tb_express");
            //3、处理SQL语句的参数
            //4、执行SQL
            rs = ps.executeQuery();
            //5、判断结果
            if (rs.next() && rs.getRow() > 0) {
                Integer count = Integer.parseInt(rs.getString(1));
                return count;
            } else {
                JOptionPane.showMessageDialog(null, "还没有快递单");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"数据库出错。");
            e.printStackTrace();
            return null;
        } finally {
            try {
                //6、关流
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改当前显示的快递单信息
     * @param express 被修改后的快递单信息
     * @return boolean  true:修改成功 false：修改失败
     */

    public static boolean updateExpressById(Express express) {
        //1、获取连接
        Connection conn = DbUtils.getconn();
        PreparedStatement ps = null;
        try {
            //2、编写SQL语句
            ps = conn.prepareStatement("update tb_express set sendName=?,sendPhone=?,"+"sendCompany=?,sendAddress=?, sendPostcode=?,receiveName=?,receivePhone=?,"+"receiveCompany=?,receiveAddress=?,receivePostcode=? where id = ?");
            //3、处理SQL语句的参数
            ps.setString(1, express.getSendName()); // 为sql中的参数赋值
            ps.setString(2, express.getSendPhone());
            ps.setString(3, express.getSendCompany());
            ps.setString(4, express.getSendAddress());
            ps.setString(5, express.getSendPostcode());
            ps.setString(6, express.getReceiveName());
            ps.setString(7, express.getReceivePhone());
            ps.setString(8, express.getReceiveCompany());
            ps.setString(9, express.getReceiveAddress());
            ps.setString(10,express.getReceivePostcode());
            ps.setString(11,String.valueOf(express.getId()));
            //4、执行SQL
            int i = ps.executeUpdate();
            //5、判断结果
            if (i > 0) {
                JOptionPane.showMessageDialog(null,"修改成功");
                return true;
            } else {
                JOptionPane.showMessageDialog(null,"修改失败");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错。");
            e.printStackTrace();
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
}