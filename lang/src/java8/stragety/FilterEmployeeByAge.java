package java8.stragety;

import java8.entity.Employee;

/**
 * @author: sam
 * @apiNote
 * @since 2019-08-13-1:23 PM
 **/
public class FilterEmployeeByAge implements FilterEmployee {

    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() > 35;
    }
}
