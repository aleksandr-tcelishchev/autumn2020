package com.jdbclesson;

import com.jdbclesson.entity.Employee;
import com.jdbclesson.repository.EmployeeRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("com.jdbclesson")
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);
//        Employee employee = employeeRepository.getEmployeeById(1);
//        employee.setLastname("Petrov");
//        employeeRepository.updateEmployee(employee);
        Employee employee = employeeRepository.insertEmployee(Employee.builder().firstname("Daniil 'Vtoroy").lastname("Danilov").position(1).address("Some street").build());
        System.out.println(employee);
    }
}
