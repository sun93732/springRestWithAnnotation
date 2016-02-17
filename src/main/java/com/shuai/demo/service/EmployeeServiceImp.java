package com.shuai.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shuai.demo.dao.EmployeeDao;
import com.shuai.demo.model.Employee;
@Component
public class EmployeeServiceImp implements EmployeeService {
    
    @Resource
    private EmployeeDao dao;

	@Override
	public Employee createEmployee(Employee employee) {
		return dao.createEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return dao.updateEmployee(employee);
	}

	@Override
	public Employee getEmployee(Integer id) {
		return dao.getEmployee(id);
	}

	@Override
	public List<Employee> searchEmployees(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(Integer id) {
		dao.deleteEmployee(id);
		
	}

}
