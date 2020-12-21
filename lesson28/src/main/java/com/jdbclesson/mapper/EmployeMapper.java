package com.jdbclesson.mapper;

import com.jdbclesson.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return Employee.builder().
                id(resultSet.getInt(1)).
                firstname(resultSet.getString(2)).
                lastname(resultSet.getString(3)).
                address(resultSet.getString(4)).
                position(resultSet.getInt(5)).
                build();
    }
}
