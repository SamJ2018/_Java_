package java8;

import java8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sam
 * @apiNote
 * @since 2019-08-14-8:08 AM
 **/
public class exer {

    //年龄相等按姓名排，否则按年龄倒叙排
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 22, 9999),
            new Employee("李四", 16, 8888),
            new Employee("王五", 37, 7777),
            new Employee("赵六", 28, 6666),
            new Employee("田七", 43, 5555)
    );

    @Test
    public void test() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //需求 用于处理任意字符串字符串
    @Test
    public void test6() {
        String s = strHandle("   我是一只小毛驴 ", (x) -> x.trim().substring(4, 7));
        System.out.println(s);
    }


    public String strHandle(String str, Myfun myfun) {
        return myfun.stringHandle(str);
    }

    @Test
    public void test7() {
        op(10033, 125221, (x, y) -> x * y);
        op(10033, 125221, (x, y) -> x + y);
        op(10033, 125221, (x, y) -> x - y);
        op(10033, 125221, (x, y) -> x / y);
    }

    //对两个long型数据进行处理
    public void op(long l1, long l2, Myfun2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }
}


interface Myfun {
    String stringHandle(String oldStr);
}

interface Myfun2<T, R> {
    R getValue(T t1, T t2);
}