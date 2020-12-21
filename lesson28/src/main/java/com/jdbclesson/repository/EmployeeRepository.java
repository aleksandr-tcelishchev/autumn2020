package com.jdbclesson.repository;

import com.jdbclesson.entity.Employee;
import com.jdbclesson.mapper.EmployeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Employee getEmployeeById(Integer id){
        return jdbcTemplate.queryForObject("SELECT * from employee where id = ?",new EmployeMapper(), id);
    }

    public String getEmployeeNameById(Integer id){
        return jdbcTemplate.queryForObject("SELECT firstname from employee where id = ?",String.class,id);
    }

    public List<Map<String,Object>> getEmployeesPlain(){
        return jdbcTemplate.queryForList("SELECT * from employee");
    }

    public List<Employee> getEmployees(){
        return jdbcTemplate.query("SELECT * from employee", new EmployeMapper());
    }

    public void updateEmployee(Employee employee){
        String query = "UPDATE employee SET firstname = ?, lastname = ?, address = ?, position = ?";
        jdbcTemplate.update(query,employee.getFirstname(),employee.getLastname(), employee.getAddress(), employee.getPosition());
    }

    public Employee insertEmployee(Employee employee){
        String query = "INSERT INTO Employee (firstname,lastname,address,position) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setInt(4, employee.getPosition());
            return preparedStatement;
        }, keyHolder);
        return getEmployeeById(keyHolder.getKey().intValue());
    }
}
