package edu.cdtu.page;

import edu.cdtu.dao.ExpressDao;
import edu.cdtu.entity.Express;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateExpressPanel extends JPanel implements ActionListener {
    // 当前快递单的 ID，默认从第一条开始
    private Integer currentId = 1;

    //快递单总数
    private Integer expressCount = 0;

    //快递背景图片
    private Image expressImg;
    //按钮: 重置+保存
    //private JButton btn_clear;
    //private JButton btn_save;
    private JButton btn_update;
    private JButton btn_last;
    private JButton btn_next;
    //输入框--寄件人信息: 寄件人姓名,手机号码,单位，地址,邮编
    private JTextField sendName;
    private JTextField sendPhone;
    private JTextField sendCompany;
    private JTextArea sendAddress;
    private JTextField sendPostcode;
    //输入框--收件人信息:收件人姓名,手机号码,单位，地址,邮编
    private JTextField receiveName;
    private JTextField receivePhone;
    private JTextField receiveCompany;
    private JTextArea receiveAdrress;
    private JTextField receivePostcode;

    //无参构造
    public UpdateExpressPanel()
    {
        setSize(1100, 800);//面板宽高
        setLayout(null);//绝对定位

        //1.快递单背景
        expressImg = new ImageIcon(getClass().getResource("/images/express.jpg")).getImage();

        //2.按钮
//        btn_clear = new JButton("清空");
//        btn_clear.setBackground(Color.orange);
//        btn_clear.setBounds(720,70,100,50);
//        btn_clear.setFont(new Font("楷体",Font.BOLD,25));
//        this.add(btn_clear);
//        btn_save = new JButton("保存");
//        btn_save.setBackground(Color.green);
//        btn_save.setBounds(850,70,100,50);
//        btn_save.setFont(new Font("楷体",Font.BOLD,25));
//        this.add(btn_save);

        btn_update = new JButton("修改");
        btn_update.setBackground(Color.green);
        btn_update.setBounds(858, 70, 100, 50);
        btn_update.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_update);

        btn_last = new JButton("上一条");
        btn_last.setBackground(new Color(221, 233, 255));
        btn_last.setBounds(380, 70, 150, 50);
        btn_last.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_last);

        btn_next = new JButton("下一条");
        btn_next.setBackground(new Color(221, 233, 255));
        btn_next.setBounds(545, 70, 150, 50);
        btn_next.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_next);

        //3.寄件人信息输入框
        sendName = new JTextField();
        sendName.setBounds(165, 178, 140, 38);
        sendName.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendName);

        sendPhone = new JTextField();
        sendPhone.setBounds(380, 178, 140, 38);
        sendPhone.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendPhone);

        sendCompany = new JTextField();
        sendCompany.setBounds(165, 220, 355, 38);
        sendCompany.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendCompany);

        sendAddress = new JTextArea(3, 1);
        sendAddress.setBorder(BorderFactory.createEtchedBorder());
        sendAddress.setLineWrap(true);//自动换行
        sendAddress.setBounds(140, 300, 355, 100);
        sendAddress.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendAddress);

        sendPostcode = new JTextField();
        sendPostcode.setBounds(410, 405, 110, 38);
        sendPostcode.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendPostcode);

        //4.收件人信息输入框
        receiveName = new JTextField();
        receiveName.setBounds(165, 490, 140, 38);
        receiveName.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receiveName);

        receivePhone = new JTextField();
        receivePhone.setBounds(380, 490, 140, 38);
        receivePhone.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receivePhone);

        receiveCompany = new JTextField();
        receiveCompany.setBounds(165, 532, 355, 38);
        receiveCompany.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receiveCompany);

        receiveAdrress = new JTextArea(3, 1);
        receiveAdrress.setBorder(BorderFactory.createEtchedBorder());//边框
        receiveAdrress.setLineWrap(true);//自动换行
        receiveAdrress.setBounds(140, 612, 355, 100);
        receiveAdrress.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receiveAdrress);

        receivePostcode = new JTextField();
        receivePostcode.setBounds(410, 715, 110, 38);
        receivePostcode.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receivePostcode);

        //默认进入修改快递单界面时，显示id为1的快递单信息
        Express defaultExpress = ExpressDao.selectExpressById("1");
        if (defaultExpress != null)
        {
            showCurrentExpress(defaultExpress);//将查询结果显示在界面上
            currentId = 1;//当前显示的快递单id为1
        }

        //为按钮添加事件监听
        btn_last.addActionListener(this);
        btn_next.addActionListener(this);
        btn_update.addActionListener(this);

        /*由于没有删除快递单的功能，且添加快递单非本界面，
        每次显示本界面都会调用构造方法，因此将查询快递单总数的功能写在构造方法内*/
        expressCount = ExpressDao.selectExpressCount();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //绘制背景图 参数:图片,绘制位道x,y,尺寸宽高,在哪个组件(添加快递面板)绘制
        g.drawImage(expressImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    //显示当前界面的快递信息
    public void showCurrentExpress(Express currentExpress)
    {
        sendName.setText(currentExpress.getSendName());
        sendPhone.setText(currentExpress.getSendPhone());
        sendCompany.setText(currentExpress.getSendCompany());
        sendAddress.setText(currentExpress.getSendAddress());
        sendPostcode.setText(currentExpress.getSendPostcode());

        receiveName.setText(currentExpress.getReceiveName());
        receivePhone.setText(currentExpress.getReceivePhone());
        receiveCompany.setText(currentExpress.getReceiveCompany());
        receiveAdrress.setText(currentExpress.getReceiveAddress());
        receivePostcode.setText(currentExpress.getReceivePostcode());
    }

    //判断哪个按钮触发了事件
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn_last)
        {
            //上一条按钮
            //若当前快递单已经是第一条，则提示”已经是第一个快递单了”
            if (currentId == 1)
            {
                JOptionPane.showMessageDialog(null,"已经是第一个快递单了");
            }
            else
            {
              //若不是第一条，则查询当d-1的快递单，即显示上一条
                currentId = currentId - 1;
                Express express = ExpressDao.selectExpressById(currentId.toString());
                //若查询出上一条快递单，则显示显示快递单信息
                if (express != null)
                {
                    showCurrentExpress(express);
                }
            }
        }
        else if (e.getSource() == btn_next)
        {
            //下一条按钮
            //若当前快递单已经是最后一条，点击下一条按钮时，提示“已经是最后一个快递单了”
            if (currentId == expressCount)
            {
                JOptionPane.showMessageDialog(null,"已经是最后一个快递单了");
            }
            else
            {
                //若不是最后一条，则显示当前id+1条快递单信息，即下一条信息
                currentId = currentId + 1;
                Express express = ExpressDao.selectExpressById(currentId.toString());
                //若下一条信息查询成功，则显示下一条信息
                if (express != null)
                {
                    showCurrentExpress(express);
                }
            }
        }
        else if (e.getSource() == btn_update)
        {
            //修改按钮
            //1.获取输入
            String send_name = sendName.getText().trim();
            String send_phone = sendPhone.getText().trim();
            String send_company = sendCompany.getText().trim();
            String send_address = sendAddress.getText().trim();
            String send_postcode = sendPostcode.getText().trim();

            String receive_name = receiveName.getText().trim();
            String receive_phone = receivePhone.getText().trim();
            String receive_company = receiveCompany.getText().trim();
            String receive_address = receiveAdrress.getText().trim();
            String receive_postcode = receivePostcode.getText().trim();
            //2.验证输入
            if (send_name == null || send_name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "寄件人名称必须填写");
                return;
            }
            else if (send_phone == null || send_phone.equals(""))
            {
                JOptionPane.showMessageDialog(null, "寄件人电话号码必须填写");
                return;
            }
            else if (!send_phone.matches("^1[3-9]\\d{9}$"))
            {
                JOptionPane.showMessageDialog(null, "寄件人电话号码必须以1[3-9]开头的11位数字");
                return;
            }
            else if (send_company == null || send_company.equals(""))
            {
                JOptionPane.showMessageDialog(null, "寄件人单位必须填写");
                return;
            }
            else if (send_address == null || send_address.equals(""))
            {
                JOptionPane.showMessageDialog(null, "寄件人地址必须填写");
                return;
            }
            else if (send_postcode == null || send_postcode.equals(""))
            {
                JOptionPane.showMessageDialog(null, "寄件人邮编必须填写");
                return;
            }
            else if (!send_postcode.matches("^\\d{6}$"))
            {
                JOptionPane.showMessageDialog(null, "寄件人邮编必须6位数字");
                return;
            }
            else if (receive_name == null || receive_name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "收件人姓名必须填写");
                return;
            }
            else if (receive_phone == null || receive_phone.equals(""))
            {
                JOptionPane.showMessageDialog(null, "收件人电话号码必须填写");
                return;
            }
            else if (!receive_phone.matches("^1[3-9]\\d{9}$"))
            {
                JOptionPane.showMessageDialog(null, "收件人电话号码必须以1[3-9]开头的11位数字");
                return;
            }
            else if (receive_company == null || receive_company.equals(""))
            {
                JOptionPane.showMessageDialog(null, "收件人单位必须填写");
                return;
            }
            else if (receive_address == null || receive_address.equals(""))
            {
                JOptionPane.showMessageDialog(null, "收件人地址必须填写");
                return;
            }
            else if (receive_postcode == null || receive_postcode.equals(""))
            {
                JOptionPane.showMessageDialog(null, "收件人邮编必须填写");
                return;
            }
            else if (!receive_postcode.matches("^\\d{6}$"))
            {
                JOptionPane.showMessageDialog(null, "收件人邮编必须6位数字");
                return;
            }

            //3.修改快递单
            Express express = new Express(currentId,send_name,send_phone,send_company,send_address,send_postcode,receive_name,receive_phone,receive_company,receive_address,receive_postcode);
            ExpressDao.updateExpressById(express);
        }
    }
}