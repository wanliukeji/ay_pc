package com.example.demo.Utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Chenny
 * @version 1.0
 * @date 2020/4/8 11:19
 * @email bbc123good@163.com
 * @address
 * @describe
 * 1.随机三个20以内加减程序 3 + 4 – 2 = ( )
 * 2.结果不能为负数
 * 3.x 可以出现在 任意位置
 */
public class mathUtil {
    static class Obj {
        public int a,b,c,d;
        public Obj(Integer a, Integer b, Integer c, Integer d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
    public static void main(String[] args) {
        math();
    }
    public static void math() {
        int a, b, c, count, num = 0;
        count = 0;
        Obj obj= null;
        ArrayList $arry = new ArrayList();
        while (count < 1000) {
            count++;
            a = (int) (Math.random() * 20);
            b = (int) (Math.random() * 20);
            c = (int) (Math.random() * 20);
            num = a + b + c;
            if (num > 0 && num < 20) {
                obj = new Obj(a,b,c,num);
                $arry.add(obj);
            }
        }
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int n = random.nextInt($arry.size());
            Obj obj3 = (Obj) $arry.get(n);
           int k = (int) (Math.random() * 20);
           if (k > 15 && k <= 20) {
               System.out.println("随机计算题:" + "() + "  + StringUtil.toString(obj3.b) + " + "+ StringUtil.toString(obj3.c) + " = " + StringUtil.toString(obj3.d));
           } else if (k > 10 && k <= 15 ) {
               System.out.println("随机计算题:" + StringUtil.toString(obj3.d) + " - "  + StringUtil.toString(obj3.b) + " - " + StringUtil.toString(obj3.c) + " = ()");
           } else {
               System.out.println("随机计算题:" + StringUtil.toString(obj3.d) + " - "  + StringUtil.toString(obj3.b) + " - () " + " = " + StringUtil.toString(obj3.a));
           }
        }
    }
}
