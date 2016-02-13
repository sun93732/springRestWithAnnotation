package com.shuai.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shuai.demo.dao.EmployeeDao;
import com.shuai.demo.model.Employee;
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
 
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return em.merge(employee);
	}

	@Override
	@Transactional
	public Employee getEmployee(String EmployeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Employee updateEmployee(Employee Employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void deleteEmployee(String EmployeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> searchEmployees(String firstName, String lastName,
			String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
