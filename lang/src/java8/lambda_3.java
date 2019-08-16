package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author sam
 * @apiNote java8内置的四大核心函数式接口
 * consumer<T>:消费型接口   void accept(T t);
 * Supplier<T>:供给型接口
 * Function<T,R>: 函数型接口  R apply(T t)
 * Predicate<T> :断言接口   boolean  test(T t)
 * @since 2019-08-14-8:28 AM
 **/
public class lambda_3 {

    @Test
    public void test1() {
        happy(100.50, x -> System.out.println("消费了" + x * x));
        getNumList(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);

        String newStr = strHandler(" 我是一只 小毛驴  ", (str) -> str.trim());
        System.out.println(newStr);

        System.out.println("===============");
        List<String> strings = Arrays.asList("Hello", "world", "lambda");
        List<String> strings1 = filterStr(strings, (s) -> s.length() > 5);
        for (String s : strings1) {
            System.out.println(s);
        }

    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    //供给型接口  需求：产生指定整数，放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    //处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    //predicate<T> 断言形接口
    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        ArrayList<String> strList = new ArrayList<>();

        for (String s : list) {
            if (pre.test(s)) {
                strList.add(s);
            }
        }
        return strList;
    }

}
