package com.shuai.demo.event;

import org.springframework.context.ApplicationEvent;

import com.shuai.demo.model.EmployeeVO;

public class EmployeeEvent extends ApplicationEvent {
	private EmployeeVO employee;

	private static final long serialVersionUID = 2856616797284784815L;

	public EmployeeVO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}

	public EmployeeEvent(Object source, EmployeeVO employee) {
		super(source);
		this.employee = employee;
		// TODO Auto-generated constructor stub
	}

}
