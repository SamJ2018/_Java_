package java8;

import java8.entity.Employee;
import java8.stragety.FilterEmployee;
import java8.stragety.FilterEmployeeByAge;
import java8.stragety.FilterEmployeeBySalry;
import org.junit.Test;

import java.util.*;

/**
 * @author: sam
 * @apiNote
 * @since 2019-08-13-12:10 PM
 **/
public class lambda_1 {

    @Test
    public void test1() {

        //以前的匿名内部类的方式==
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(comparator);
    }


    /**
     * 使用lambda表达式之后
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //1、需求：获取当前公司中员工年龄大于35的员工信息
    //1、传统做法
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 22, 9999),
            new Employee("李四", 16, 8888),
            new Employee("王五", 37, 7777),
            new Employee("赵六", 28, 6666),
            new Employee("田七", 43, 5555)
    );

    @Test
    public void test3() {

        List<Employee> filterEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getAge() > 35) {
                filterEmployees.add(employee);
            }
        }

        System.out.println(filterEmployees);
    }

    //优化方式二：使用策略设计模式====================
    @Test
    public void test4() {
        //年龄>35
        FilterEmployee filterEmployee = new FilterEmployeeByAge();
        for (Employee employee : employees) {
            if (filterEmployee.filter(employee)) {
                System.out.println(employee);
            }
        }//for

        //工资>=5000
        filterEmployee = new FilterEmployeeBySalry();
        for (Employee employee : employees) {
            if (filterEmployee.filter(employee)) {
                System.out.println(employee);
            }
        }
        //但是还是有重复代码
    }

    //优化二：使用匿名内部类
    @Test
    public void test5() {
        List<Employee> employees = filterEmployee(this.employees, new FilterEmployee() {
            @Override
            public boolean filter(Employee employee) {
                return employee.getAge() > 35;
            }
        });

        System.out.println(employees);
    }

    List<Employee> filterEmployee(List<Employee> list, FilterEmployee filterEmployee) {
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (filterEmployee.filter(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }//

    //优化三：使用lambda
    @Test
    public void test6() {
        List<Employee> employees = filterEmployee(this.employees, (e) -> e.getSalary() <= 5000);
        employees.forEach(System.out::println);
    }

    //优化方式四 API stream
    @Test
    public void test7() {
        employees.stream().filter((e) -> e.getSalary() >= 5000).forEach(System.out::println);
    }


}
