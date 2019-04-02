import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JM {
    int N;
    int Left, Right, root;
    double Fai;
    double P, ev, ex;

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

        list.add(0);
        try (FileReader reader = new FileReader(PATH);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] ary = aryChange(line);
                list.add(ary[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SetEvEx(JM tmp, int n1, int n2) {
        tmp.ev = n1;
        tmp.ex = n2;
    }

    public static void GetP(List<Integer> list, JM tmp) {
        int temp = 0;
        for (int i = 1; i <= list.size() - 1; i++)
            temp += (i - 1) * (list.get(i) - list.get(i - 1));
        tmp.P = temp / list.get(list.size() - 1);
    }

    public static double Function_f(List<Integer> list, int num, JM tmp) {
        double temp = 0.0f;
        for (int i = 1; i <= list.size() - 1; i++) {
            temp = 1 / (float) (num - i + 1);
        }
        temp -= (list.size() - 1) / (num - tmp.P);
        return temp;

    }

    public static boolean Step1(List<Integer> list, JM tmp) {
        if (tmp.P > ((list.size() - 1) - 1) / 2) {
            tmp.Left = (list.size() - 1) - 1;
            tmp.Right = list.size() - 1;
            return true;
        } else return false;
    }

    public static void Step2(List<Integer> list, JM tmp) {
        while (Function_f(list, tmp.Right, tmp) > tmp.ev) {
            tmp.Left = tmp.Right;
            tmp.Right++;
        }
        if (Function_f(list, tmp.Right, tmp) < 0 - tmp.ex) {
            Step3(list, tmp);
        } else {
            tmp.root = tmp.Right;
            Step5(list, tmp);
        }
    }

    public static void Step3(List<Integer> list, JM tmp) {
        if (Math.abs(tmp.Right - tmp.Left) < tmp.ex) {
            tmp.root = (tmp.Right + tmp.Left) / 2;
            Step5(list, tmp);
        } else if (Math.abs(tmp.Right - tmp.Left) > tmp.ex) {
            tmp.root = (tmp.Right + tmp.Left) / 2;
            Step4(list, tmp);
        }
    }

    public static void Step4(List<Integer> list, JM tmp) {
        if (Function_f(list, tmp.root, tmp) > tmp.ev) {
            tmp.Left = tmp.root;
            Step3(list, tmp);
        } else if (Function_f(list, tmp.root, tmp) < 0 - tmp.ev) {
            tmp.Right = tmp.root;
            Step3(list, tmp);
        } else {
            Step5(list, tmp);
        }
    }

    public static void Step5(List<Integer> list, JM tmp) {
        int temp = 0;
        tmp.N = tmp.root;

        for (int i = 1; i <= list.size() - 1; i++) {
            temp += (i - 1) * (list.get(i) - list.get(i - 1));
        }
        temp = tmp.N * list.get(list.size() - 1);
        tmp.Fai = (list.size() - 1) / (float) temp;
    }

    public static void main(String[] argc) {
        JM jm = new JM();
        String pathname = "C:\\Users\\Tao\\Desktop\\SYS1(failue_count).txt";
        List<Integer> list = new ArrayList<Integer>();
        readfile(pathname, list);
        SetEvEx(jm, 5, 7);
        GetP(list, jm);
        if (Step1(list, jm) == true) {
            Step2(list, jm);
        }
        System.out.println("当ev取" + jm.ev + ",ex取" + jm.ex + "时:");
        System.out.println("N的取值为：" + jm.N);
        System.out.println("Fai的取值为：" + jm.Fai);
    }
}
