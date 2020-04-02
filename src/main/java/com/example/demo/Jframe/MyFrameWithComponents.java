package com.example.demo.Jframe;

import java.awt.*;

import javax.swing.*;

/**
 * @author Chenny
 * @version 1.0
 * @date 2020/4/3 7:17
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe JAVA图形化程序 身份证本地识别
 */
public class MyFrameWithComponents extends JFrame{

    private static final long serialVersionUID = 1L;

    // 定义组件
    JPanel jp1, jp2;
    JButton jb1, jb2, jb3, jb4, jb5, jb6;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField tf;
    JPasswordField psf;
    JRadioButton rb1;
    JRadioButton rb2;
    JButton bt1;
    JButton bt2;

    public static void main(String[] args) {
        new MyFrameWithComponents();
    }

    public MyFrameWithComponents() {
        // 创建组件
        // JPanel布局默认是FlowLayout流布局
        this.setVisible(true);
        this.setSize(350, 230);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("登陆界面");
        label2 = new JLabel("账号：");
        label3 = new JLabel("密码：");
        tf = new JTextField();
        psf = new JPasswordField();
        rb1 = new JRadioButton("记住密码");
        rb2 = new JRadioButton("自动登陆");
        bt1 = new JButton("登陆");

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        // 添加间隔
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(label3));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(psf).addComponent(rb1)
                .addComponent(rb2).addComponent(tf).addComponent(bt1));
        hGroup.addGap(5);
        // 设置水平分组
        layout.setHorizontalGroup(hGroup);

        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label3).addComponent(psf));
        vGroup.addGroup(layout.createParallelGroup().addComponent(rb1));
        vGroup.addGroup(layout.createParallelGroup().addComponent(rb2));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(bt1));
        vGroup.addGap(10);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }

}
