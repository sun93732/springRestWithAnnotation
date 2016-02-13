package com.shuai.demo.model.event;

import org.springframework.context.ApplicationEvent;

import com.shuai.demo.model.Employee;

public class EmployeeEvent extends ApplicationEvent {
	private Employee employee;

	private static final long serialVersionUID = 2856616797284784815L;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeEvent(Object source, Employee employee) {
		super(source);
		this.employee = employee;
		// TODO Auto-generated constructor stub
	}

}
