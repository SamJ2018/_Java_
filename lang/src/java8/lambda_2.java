package java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author sam
 * @apiNote 新的操作符"->"    左侧：参数列表    右侧：对抽象方法的实现，表达式所需要执行的功能
 * @since 2019-08-14-7:42 AM
 **/
public class lambda_2 {

    /**
     * 格式1：无参数，无返回值
     * () ->  System.out.println("hello");
     */
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        r1.run();

        //lambda：
        Runnable r2 = () -> System.out.println("hello lambda");//语法糖  跟匿名内部类相似
        r2.run();
    }

    /**
     * 格式2：有一个参数，无返回值  只有一个参数()可以不写
     */
    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("冲啊！");
    }

    /**
     * 格式3：有两个参数，多个返回值
     * lambda体中需要使用{}
     * 如果lambda体只有一条语句，大括号和return可省略不写
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    /**
     * lambda表达式的参数列表数据类可以省略不写，JVM可以通过上下文推断出数据类型——>类型推断
     */
    public void test4() {
        String[] strs = {"h", "e", "ll", "o"};
        /*但是这样推断不出来
         * String[] strs;
         * strs={"he","llo"};
         * */
    }

    /**
     * lambda表达式需要“函数式接口”的接口
     * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可使用注解@FunctionlInterface修饰 检查是否函数式接口
     */
    //需求：对一个数进行运算
    @Test
    public void test5() {
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }
}
