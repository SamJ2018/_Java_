package java8.stragety;

import java8.entity.Employee;

/**
 * @author: sam
 * @apiNote
 * @since 2019-08-13-1:27 PM
 **/
public class FilterEmployeeBySalry implements FilterEmployee {
    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
