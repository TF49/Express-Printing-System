package edu.cdtu.page;

import edu.cdtu.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static java.awt.SystemColor.text;

public class AddUserPanel extends JPanel implements ActionListener {
    private JLabel titLeLabel,passwordLabel1,passwordLabel2;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JPasswordField password1,password2;
    private JButton btn_clear,btn_save;
    //构造方法
    public AddUserPanel()
    {
        //面板宽高、背景颜色、绝对布局
        setSize(1160,850);
        setBackground(Color.white);
        setLayout(null);

        //1.大标题
        titLeLabel = new JLabel("添加用户",JLabel.CENTER);
        titLeLabel.setFont(new Font("楷体",Font.BOLD,40));
        titLeLabel.setBounds(250,120,550,50);
        this.add(titLeLabel);

        //2.用户名
        usernameLabel = new JLabel("用户名:",JLabel.CENTER);
        usernameLabel.setFont(new Font("楷体",Font.BOLD,25));
        usernameLabel.setBounds(220,200,200,50);
        this.add(usernameLabel);

        usernameField =new JTextField();
        usernameField.setFont(new Font("楷体",Font.BOLD,25));
        usernameField.setBounds(400,200,280,50);
        this.add(usernameField);

        //3.密码
        passwordLabel1=new JLabel("密码:",JLabel.CENTER);
        passwordLabel1.setFont(new Font("楷体",Font.BOLD,25));
        passwordLabel1.setBounds(220,280,200,50);
        this.add(passwordLabel1);
        password1 =new JPasswordField();
        password1.setFont(new Font("楷体",Font.BOLD,25));
        password1.setBounds(400,280,280,50);
        password1.setEchoChar('*');//密码是示字符
        this.add(password1);

        //4、确认密码
        passwordLabel2 = new JLabel("确认密码:",JLabel.CENTER);
        passwordLabel2.setFont(new Font("楷体",Font.BOLD,25));
        passwordLabel2.setBounds(210,360,200,50);
        this.add(passwordLabel2);
        password2 =new JPasswordField();
        password2.setFont(new Font("楷体",Font.BOLD,25));
        password2.setBounds(400,360,280,50);
        password2.setEchoChar('*');//密码显示字符
        this.add(password2);

        //5.按钮
        btn_clear = new JButton("清空");
        btn_clear.setBackground(Color.orange);
        btn_clear.setFont(new Font("楷体",Font.BOLD,25));
        btn_clear.setBounds(400,440,120,50);
        btn_clear.addActionListener(this);
        this.add(btn_clear);

        btn_save = new JButton("提交");
        btn_save.setBackground(Color.green);
        btn_save.setFont(new Font("楷体",Font.BOLD,25));
        btn_save.setBounds(560,440,120,50);
        btn_save.addActionListener(this);
        this.add(btn_save);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn_clear)
        {
            //清空按钮
            clearAddUserInput();
        }
        else if (e.getSource() == btn_save)
        {
            //提交按钮
            //1.获取用户输入
            String username = usernameField.getText().trim();
            String pass1 = new String(password1.getPassword()).trim();
            String pass2 = new String(password2.getPassword()).trim();
            //2.验证用户输入
            if (username == null ||username.equals(""))
            {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                return;
            }
            else if(UserDao.checkUserName(username) == false)
            {
                JOptionPane.showMessageDialog(null,"用户名已存在");
                return;
            }
            else if(pass1 == null || pass1.equals("")|| pass2 == null || pass2.equals(""))
            {
                JOptionPane.showMessageDialog(null,"密码不能为空");
                return;
            }
            else if (!pass1.equals(pass2))
            {
                JOptionPane.showMessageDialog(null, "两次密码输入不同");
                return;
            }

            //3.实现添加用户
            if (UserDao.addUser(username,pass1))
            {
                //4.清空输入框
                clearAddUserInput();
            }
        }
    }
    //清空输入框
    private void clearAddUserInput()
    {
        usernameField.setText("");
        password1.setText("");
        password2.setText("");
    }
}
