package GoModle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    static String input = "    ";
    JFrame frame = new JFrame();//   用JFrame创建一个名为frame的顶级容器
    FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);
    JLabel name1 = new JLabel();
    static JTextField nametext = new JTextField();//JTextField在窗口上添加一个可输入可见文本的文本框，
    JLabel name = new JLabel("输入约束条件e");//同上，此处添加的是内容为“输入约束条件e”的JLabel
    JLabel name2 = new JLabel();
    JLabel message = new JLabel("根据GO模型得到结果如下：");
    JLabel name3 = new JLabel();
    JLabel name4 = new JLabel();
    JLabel message1 = new JLabel("a的值为：");
    JLabel name6 = new JLabel();
    JLabel message2 = new JLabel("b的值为：");
    JLabel name5 = new JLabel();
    JLabel name7 = new JLabel();
    static JButton bu = new JButton("计算");

    public void CreatUI(GO tmp) {
        //1.1创建一个顶级容器，也就是空白窗口，并为此窗口设置属性（窗口名称，大小，显示位置，关闭设置）

        frame.setTitle("GO_Modle");//设置窗口名称
        frame.setSize(540, 427);//设置窗口大小
        frame.setLocationRelativeTo(null);//设置窗口位于屏幕中央

        //1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
        //用FlowLayout创建一个名为f1的对象,需要添加的包名为java.awt.FlowLayout，其中LEFT表示左对齐，CENTER表示居中对齐，RIGHT表示右对齐
        frame.setLayout(f1);//frame窗口设置为f1的流式左对齐

        //创建一个空的JLabel，它的长度宽度为110,30，因为窗口是流式左对齐，左侧由空的JLabel填充
        //设置空JLabel长度大小，此处不能使用setSize设置大小，setSize只能设置顶级容器大小，此处用setPreferredSize，Dimension给出大小，需要添加的包名为java.awt.Dimension.
        name1.setPreferredSize(new Dimension(110, 30));
        frame.add(name1);//将空JLabel添加入窗口

        //此处添加的是内容为“输入约束条件e”的JLabel
        frame.add(name);
        //JTextField在窗口上添加一个可输入可见文本的文本框，
        nametext.setPreferredSize(new Dimension(220, 30));//设置文本框大小
        frame.add(nametext);//添加到窗口上

        //同name1

        name2.setPreferredSize(new Dimension(110, 30));
        frame.add(name2);

        //同name
        frame.add(message);

        name3.setPreferredSize(new Dimension(140, 30));
        frame.add(name3);

        name4.setPreferredSize(new Dimension(110, 30));
        frame.add(name4);

        frame.add(message1);

        name6.setPreferredSize(new Dimension(110, 30));
        frame.add(name6);

        frame.add(message2);

        name5.setPreferredSize(new Dimension(110, 30));
        frame.add(name5);

        name7.setPreferredSize(new Dimension(110, 30));
        frame.add(name7);

        //JButton创建一个可点击的按钮，按钮上可显示文本图片

        frame.add(bu);
        //设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
        frame.setVisible(true);


    }

    public static void GetE() {
        input = nametext.getText();// 获取 JTextField 内容
    }

    public static void ShowResult(GUI UI, GO tmp) {
        UI.name6.setText(String.valueOf(tmp.Result_a));
        UI.name5.setText(String.valueOf(tmp.Result_b));//
    }

    public static void main(String argc[]) {
        GO tmp = new GO();
        GUI UI = new GUI();
        UI.CreatUI(tmp);

        GUI.bu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello");
                GUI.GetE();

                tmp.e = Double.parseDouble(input);
                tmp.Prepare(tmp);
                ShowResult(UI, tmp);
            }
        });

    }
}


