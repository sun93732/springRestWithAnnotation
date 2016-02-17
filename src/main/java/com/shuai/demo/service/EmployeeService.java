package com.shuai.demo.service;

import java.util.List;

import com.shuai.demo.model.Employee;

public interface EmployeeService {
	/**
	 * 
	 * @param employee - Employee object containing attributes (ORG_ID, USER_ID, ROLE ETC.)
	 * @return The employee that was created or exception if the employee is not created.
	 */
	Employee createEmployee(Employee employee);
	
	/**
	 * 
	 * @param employeeId - Employee Id of the employee that needs to be updated
	 * @param employee - Employee that needs to be updated
	 * @return The employee that was updated or exception if the employee is not updated.
	 */
	
	Employee updateEmployee(Employee employee);
	
	/**
	 * 
	 * @param employeeId - Employee Id of the employee that needs to be retrieved
	 * @return The retrieved employee
	 */
	
	Employee getEmployee(Integer id);
	
		
	/**
	 * Returns a list of the Employees found by the search
	 * 
	 * @param ids A list of IDs to search for
	 * @return A list of Employees
	 */
	List<Employee> searchEmployees(List<Integer> ids);
	
	/**
	 * Delete an existing employee
	 * 
	 * @param employeeId
	 *            The employee to be deleted
	 */
	void deleteEmployee(Integer id);
}