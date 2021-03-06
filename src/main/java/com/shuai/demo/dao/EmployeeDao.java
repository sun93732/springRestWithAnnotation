package com.shuai.demo.dao;

import java.util.List;

import com.shuai.demo.model.Employee;

public interface EmployeeDao {

    public Employee createEmployee(Employee employee);

    public Employee getEmployee(Integer employeeId);

    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(Integer employeeId);

    public List<Employee> searchEmployees(String firstName, String lastName, String email);

}
