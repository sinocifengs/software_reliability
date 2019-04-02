package GoModle;

import java.io.FileReader;
import java.io.*;
import java.util.*;

/* 步骤一：误差允许值D，若0< D < 1/2,xl=(1-2D)/2,xr=1/D,转步骤二；若D >= 1/2,无解，转步骤五
 * 步骤二：算xm=（xl + xr）/2，若|xr-xl| <= $,转步骤四
 * 步骤三：f = （1 - D xm）exp xm + (D - 1)xm -1,若f > $ ,xl=xm,转步骤二；若f < -$,xr=xm,转步骤二
 * 步骤四：计算b = xm/Tn和a=N/(1-exp(-b Tn))
 * 步骤五：停止计算
 **/
public class GO {
    double xl = 0.0f, xr = 0.0f, xm = 0.0f;
    double D = 0.0f, e = 0.5f;
    double f = 0.0f;
    double Result_a = 0.0f, Result_b = 0.0f;

    /*字符串数组解析成int数组*/
    public static int[] aryChange(String Line) {
        /*.trim()可以去掉首尾多余的空格
         * .split("\\s+")表示用正则表达式去匹配切割,\\s+表示匹配一个或者以上的空白符
         * */
        String[] ss = Line.trim().split("\\s+");
        int[] ary = new int[ss.length];
        for (int i = 0; i < ary.length; i++) {
            ary[i] = Integer.parseInt(ss[i]);
        }
        return ary;//返回一个int数组
    }

    /*读入text文件数据*/
    public static void readfile(String PATH, List<Integer> list) {

        try (FileReader reader = new FileReader(PATH);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            //List<Integer>list = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                int[] ary = aryChange(line);
                list.add(ary[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*实现GO模型算法*/
    public static void DoGoModle(List<Integer> list, GO tmp) {
        for (int i = 0; i < list.size(); i++) {
            tmp.D += list.get(i);
        }
        tmp.D = tmp.D / (list.size() * list.get(list.size() - 1));
        if (tmp.D < 1 / (float) 2 && tmp.D > 0) {           //这里是一个坑，刚开始没有在2前面加（float）强制类型转换，导致该判断条件恒为false
            tmp.xl = (1 - 2 * tmp.D) / (float) 2;
            tmp.xr = 1 / (float) tmp.D;
            Step2(list, tmp);
        } else {
            Step5();
        }
        System.out.println("当约束条件e的值为：" + tmp.e + "时，结果如下：");
        System.out.println("a = " + tmp.Result_a);
        System.out.println("b = " + tmp.Result_b);
    }

    //    public static void SetE(GO tmp,GUI UI){
//        tmp.e = UI.in;
//    }
    public static void Step2(List<Integer> list, GO tmp) {
        tmp.xm = (tmp.xr + tmp.xl) / 2;
        double temp = tmp.xr - tmp.xl;
        if (temp > 0.0f) temp = -temp;
        if (temp <= tmp.e) {
            Step4(list, tmp);
        }
        Step3(list, tmp);
    }

    public static void Step3(List<Integer> list, GO tmp) {
        double temp = 0;
        temp = Math.exp(tmp.xm);
        temp = (1 - tmp.D * tmp.xm) * temp;
        tmp.f = temp + (tmp.D - 1) * tmp.xm - 1;

        if (tmp.f > tmp.e) {
            tmp.xl = tmp.xm;
            Step2(list, tmp);
        } else if (tmp.f < -tmp.e) {
            tmp.xr = tmp.xm;
            Step2(list, tmp);
        } else {
            Step4(list, tmp);
        }
    }

    public static void Step4(List<Integer> list, GO tmp) {
        tmp.Result_b = tmp.xm / (float) list.get(list.size() - 1);
        double temp = 0;
        temp = Math.exp((-tmp.Result_b) * list.get(list.size() - 1));
        tmp.Result_a = list.size() / (float) (1 - temp);
        Step5();
    }

    public static void Step5() {

    }

    public static void Prepare(GO tmp) {
        String pathname = "C:\\Users\\Tao\\Desktop\\SYS1(failue_count).txt";
        List<Integer> list = new ArrayList<Integer>();

        readfile(pathname, list);
        DoGoModle(list, tmp);
    }
}
