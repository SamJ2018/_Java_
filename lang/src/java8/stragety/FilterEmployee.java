package java8.stragety;

import java8.entity.Employee;

/**
 * @author: sam
 * @apiNote
 * @since 2019-08-13-1:22 PM
 **/
public interface FilterEmployee {
    //通用的比较方法
    boolean filter(Employee employee);
}
