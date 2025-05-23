package edu.cdtu.page;

import edu.cdtu.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener
{
    //项目Logo
    private ImageIcon logoIcon;
    //左侧主体面板，顶部主体面板,右侧主体面板,一级菜单上面板,一级菜单下面板,二级菜单面板
    private JPanel leftPanel,topPanel,rightPanel,pNorth,pSouth,subMenuContainer;
    //大标题，用户名，背景图片,汽车logo图片
    private JLabel titleLable,usernameLable,bgImageLable,carImageLable;
    //一级菜单按钮
    private JButton item1,item2,item3;
    //二级菜单按钮数组
    private JButton[] btn,ctn,dtn;
    //退出系统按钮
    private JButton exitButton;

    //   无参构造
    public MainFrame()
    {
        setTitle("快递打印系统");//设置窗体标题
        setSize(1400,1000);//设置窗体大小
        setLocationRelativeTo(null);//设置窗体在电脑屏幕中间显示
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭属性
        setResizable(false);//设置窗体不可通过拖动改变大小
        setLayout(null);//绝对布局，必须加上，不然主界面布局效果无法达到

        //1.实现项目(界面)logo
        logoIcon = new ImageIcon("First/EPS/src/images/logo.png");
        setIconImage(logoIcon.getImage());

        //2.上面板，左面板，右面板布局
        topPanel = new JPanel();//实例化顶部面板
        topPanel.setBackground(new Color(221,233,255));//设置面板背景颜色
        topPanel.setBounds(0,0,1400,120);//设置位置和宽高
        topPanel.setBorder(BorderFactory.createEtchedBorder());//边框
        topPanel.setLayout(null);//绝对布局
        this.add(topPanel);//将顶部面板topPanel添加到主界面窗体MainFrame中

        leftPanel = new JPanel();//实例化左侧面板
        leftPanel.setBounds(0,120,260,880);//设置位置和宽高
//        leftPanel.setBackground(Color.orange);//背景色，后期去掉，只是为了观蔡宽高是否合适
        this.add(leftPanel);//将左侧面板leftPanel添加到主界面窗体MainFrame中

        rightPanel = new JPanel();//实例化右侧面板
        rightPanel.setBounds(260, 120,1140,880);//设置位置和宽高
//        rightPanel.setBackground(Color.green);//背景色,后期去掉，只是为了观察宽高是否合适
        rightPanel.setLayout(null);//绝对布局
        this.add(rightPanel);//将右侧面板leftPanel添加到主界面窗体MainFrame中

        //5.实现二级菜单
        //5.1实例化二级菜单面板
        subMenuContainer = new JPanel();
        subMenuContainer.setLayout(new GridLayout(2,1));//网格布局，两行一列

        //3.实现顶部面板样式
        //3.1顶部面板左侧汽车图片
        carImageLable = new JLabel(new ImageIcon("First/EPS/src/images/car.png"));
        carImageLable.setBounds(0,0,100,120);
        topPanel.add(carImageLable);

        //3.2 顶部面板大标题
        titleLable = new JLabel("快递打印系统", JLabel.LEFT);
        titleLable.setFont(new Font("楷体", Font.BOLD, 40));
        titleLable.setBounds(100,0,550,120);
        topPanel.add(titleLable);

        //3.3顶部面板欢迎用户信息
        usernameLable = new JLabel("您好!" + UserSaveTool.getcurrentLoginUsername());
        usernameLable.setFont(new Font("楷体", Font.BOLD, 20));
        usernameLable.setBounds(1200,70,150,50);
        topPanel.add(usernameLable);

        //3.4 退出登录按钮
        exitButton = new JButton("退出");
        exitButton.setBounds(1250,10,100,40);
        exitButton.setBackground(new Color(135,176,243));
        exitButton.setFont(new Font("楷体", Font.BOLD,20));
        topPanel.add(exitButton);

        //4.实现左侧面板-级菜单
        //4.1.菜单上下面板，上面板为pNorth，下面板为pSouth
        pNorth = new JPanel();//菜单上面板
        pNorth.setLayout(new GridLayout(3,1,10,10));//网格布局,3行列,间距18

        pSouth = new JPanel();//菜单下面板

        leftPanel.add(pNorth, "North");//将上下面板添加到左侧面板中

        //5.5 将二级菜单subMenuContainer添加到左侧面板leftPanel中
        leftPanel.add(subMenuContainer,"center");

        leftPanel.add(pSouth, "south");

        //4.2 一级菜单按钮
        //实例化一级菜单
        item1 = new JButton("快递单管理");
        item2 = new JButton("打印管理");
        item3 = new JButton("用户管理");

        //4.3 一级菜单按钮样式
        item1.setPreferredSize(new Dimension(240,50));//按钮宽高
        item2.setPreferredSize(new Dimension(240,50));
        item3.setPreferredSize(new Dimension(240,50));
        item1.setBackground(new Color(221,233,255));//按钮背景颜色
        item2.setBackground(new Color(221,233,255));
        item3.setBackground(new Color(221,233,255));
        item1.setFont(new Font("楷体", Font.BOLD,25));//按钮字体
        item2.setFont(new Font("楷体", Font.BOLD,25));
        item3.setFont(new Font("楷体", Font.BOLD,25));

        //4.4 将一级菜单添加到左侧面板的上面板pNorth中
        pNorth.add(item1);
        pNorth.add(item2);
        pNorth.add(item3);

        //5.2一级菜单“快递单管理”的二级菜单
        btn = new JButton[2];//共2个二级菜单
        btn[0] = new JButton("添加快递单");//二级菜单名称
        btn[1] = new JButton("修改快递单");
        for (int i=0;i<btn.length;i=i+1)
        {
            btn[i].setBackground(Color.white);//背景色
            btn[i].setPreferredSize(new Dimension(220,30));//宽高
            btn[i].setFont(new Font("楷体", Font.BOLD,20));//字体
            btn[i].addActionListener(this);//点击事件
        }

        //5.3 一级菜单“打印管理”的二级菜单
        ctn = new JButton[1];//共1个二级菜单
        ctn[0] = new JButton("打印快递单");//二级菜单名称
        for (int i=0;i<ctn.length;i=i+1)
        {
            ctn[i].setBackground(Color.white);
            ctn[i].setPreferredSize(new Dimension(220,30));
            ctn[i].setFont(new Font("楷体", Font.BOLD,20));
            ctn[i].addActionListener(this);//点击事件
        }

        //5.4 一级菜单“用户管理”的二级菜单
        dtn = new JButton[2];
        dtn[0] = new JButton("添加用户");
        dtn[1] = new JButton("修改个人密码");
        for (int i=0;i<dtn.length;i =i+1)
        {
            dtn[i].setBackground(Color.white);
            dtn[i].setPreferredSize(new Dimension(220,30));
            dtn[i].setFont(new Font("楷体", Font.BOLD,20));
            dtn[i].addActionListener(this);//点击事件
        }

        //6.实现点击一级菜单显示二级菜单的功能
        //6.2为一级菜单按钮添加添加事件监听
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);

        //7.主界面右侧面板显示背景图
        bgImageLable = new JLabel(new ImageIcon("First/EPS/src/images/welcome.png"));
        bgImageLable.setBounds(0,0,1160,850);
        rightPanel.add(bgImageLable);

        //退出登录按钮
        exitButton.addActionListener(this);

        setVisible(true);//设置窗体可见
    }

    //      主方法
    public static void main(String[] args)
    {
        new MainFrame();//实例化登录窗体MainFrame
    }

    //重置按钮背景颜色--白色
    private void resetMenuButtonBackgroundColor()
    {
        btn[0].setBackground(Color.white);
        btn[1].setBackground(Color.white);
        ctn[0].setBackground(Color.white);
        dtn[0].setBackground(Color.white);
        dtn[1].setBackground(Color.white);
    }

    //自动生成ActionListener的抽象方法及方法体
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //每次点击按钮时，都先将按钮恢复成自色背景
        resetMenuButtonBackgroundColor();
        //6.3判断点击不同的一级菜单按钮，实现不同的功能
        if(e.getSource()== item1)
        {
            //System.out.println("快递管理");
            //6.4 显示"快递管理"二级菜单
            pSouth.removeAll();//移除左侧面板的下面板中的全部组件
            pNorth.setLayout(new GridLayout(1,1,10,10));//上面板1行列,只有快递管理
            pSouth.setLayout(new GridLayout(2,1,10,10));//下面板2行列,有打印管理和用户管理
            pNorth.add(item1);//上面板添加一级菜单快递管理
            pSouth.add(item2);//下面板添加一级梨单打印管理和用户管理
            pSouth.add(item3);

            //将一级菜单“快递管理”的二级菜单添加到二级菜单面板中
            for (int i=0;i<btn.length;i=i+1)
            {
                subMenuContainer.add(btn[i]);
            }
            //将一级菜单“打印管理”和“用户管理”的二级菜单从二级菜单面板中移除
            for (int i=0;i<ctn.length;i=i+1)
            {
                subMenuContainer.remove(ctn[i]);
            }
            for(int i=0;i<dtn.length;i=i+1)
            {
                subMenuContainer.remove(dtn[i]);
            }
            validate();//重新布局
            getContentPane().repaint();//重绘界面
        }

        else if(e.getSource()== item2)
        {
            //System.out.println("打印管理");
            //6.5 显示"打印管理"二级菜单
            pSouth.removeAll();//移除下面板中的组件
            pNorth.setLayout(new GridLayout(2,1,10,10));//上面板2行1列,快逆管理和打印管理
            pSouth.setLayout(new GridLayout(1,1,10,10));//下面板行列,用户管理
            pNorth.add(item1);//上面板添加一级菜单快递管理和打印管理
            pNorth.add(item2);
            pSouth.add(item3);//下面板添加一级菜单用户管理
            //将一级菜单“打印管理”的二级菜单添加到二级菜单面板中
            //并将一级菜单"快递管理"和"用户管理"的二级菜单从二级菜单面板中移除
            for(int i=0;i<btn.length;i=i+1)
            {
                subMenuContainer.remove(btn[i]);
            }
            for(int i=0;i<ctn.length;i++)
            {
                subMenuContainer.add(ctn[i]);
            }
            for(int i=0;i<dtn.length;i++)
            {
                subMenuContainer.remove(dtn[i]);
            }
            //重新布局并重绘界画
            validate();
            getContentPane().repaint();
        }

        else if(e.getSource()== item3)
        {
            //System.out.println("用户管理");
            //6.6 显示"用户管理"二级菜单
            pSouth.removeAll();//下面板组件全部移除
            pNorth.setLayout(new GridLayout(3,1,10,10));//上面板3行3列,所有一级菜单
            pSouth.setLayout(new GridLayout(0,1,10,10));//下面板无一级菜单
            pNorth.add(item1);//将一级菜单全部添加到上面板中
            pNorth.add(item2);
            pNorth.add(item3);
            //将一级菜单"用户管理"的二级菜单添加到二级菜单面板中
            //并将一级菜单"快递管理"和"打印管理"的二级菜单从二级菜单面板中移除
            for(int i=0;i<btn.length;i=i+1)
            {
                subMenuContainer.remove(btn[i]);
            }
            for(int i=0;i<ctn.length;i=i+1)
            {
                subMenuContainer.remove(ctn[i]);
            }
            for(int i=0;i<dtn.length;i=i+1)
            {
                subMenuContainer.add(dtn[i]);
            }
            //重新布局并重绘界面
            validate();
            getContentPane().repaint();
        }

        else if(e.getSource()== btn[0])
        {
            //System.out.println("添加快递单");
            btn[0].setBackground(new Color(135,176,243));//点击该按钮变色--表示漖活
            //显示右侧添加快递单页面
            rightPanel.removeAll();//主页右侧面板移除原有组件
            AddExpressPanel addExpress = new AddExpressPanel();//实例化添加快递单面板
            rightPanel.add(addExpress);//在主页的右侧面板中添加添加快递单面板
            rightPanel.updateUI();//更新右侧面板
        }

        else if(e.getSource()== btn[1])
        {
            //System.out.println("修改快递单");
            btn[1].setBackground(new Color(135,176,243));//点击该按钮变色--表示激活
            //显示右侧修改快递单负面
            rightPanel.removeAll();
            UpdateExpressPanel updateExpress = new UpdateExpressPanel();
            rightPanel.add(updateExpress);
            rightPanel.updateUI();
        }

        else if(e.getSource()== ctn[0])
        {
            //System.out.println("打印快递单");
            ctn[0].setBackground(new Color(135,176,243));//点击该按钮变色--表示激活
            //显示右侧打印快递单页面
            rightPanel.removeAll();
            PrintExpressPanel printExpress = new PrintExpressPanel();
            rightPanel.add(printExpress);
            rightPanel.updateUI();
        }

        else if(e.getSource()== dtn[0])
        {
            //System.out.println("添加用户");
            dtn[0].setBackground(new Color(135,176,243));//点击该按钮变色--表示漖活
            //显示右侧添加用户页面
            rightPanel.removeAll();
            AddUserPanel addUser = new AddUserPanel();
            rightPanel.add(addUser);
            rightPanel.updateUI();
        }

        else if(e.getSource()== dtn[1])
        {
            //System.out.println("修改个人密码");
            dtn[1].setBackground(new Color(135,176,243));//点击该按钮变色--表示激活

            //显示右侧修改用户密码页面
            rightPanel.removeAll();
            UpdateUserPasswordPanel updateUser = new UpdateUserPasswordPanel();
            rightPanel.add(updateUser);
            rightPanel.updateUI();
        }
        else if (e.getSource() == exitButton)
        {
            //退出登录按钮
            //1.清空用户工具类中存储的当前登录用户的信息
            UserSaveTool.setcurrentLoginUsername(null);
            UserSaveTool.setCurrentLoginUserPass(null);

            //2.退出主界面，返回登录页面
            this.setVisible(false);
            new LoginFrame();
        }
    }
}