package com.shuai.demo.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shuai.demo.event.EmployeeEvent;
import com.shuai.demo.exception.BadRequestException;
import com.shuai.demo.exception.ResourceNotFoundException;
import com.shuai.demo.model.Employee;
import com.shuai.demo.model.EmployeeList;

@RestController
public class EmployeeRESTController implements ApplicationEventPublisherAware {

	// one subclass of ApplicationListener will be listening for this event.
	private ApplicationEventPublisher publisher;

	private static EmployeeList employees = new EmployeeList();
	static {
		Employee empOne = new Employee(1, "Lokesh", "Gupta",
				"howtodoinjava@gmail.com");

		Employee empTwo = new Employee(2, "Amit", "Singhal",
				"asinghal@yahoo.com");
		Employee empThree = new Employee(3, "Kirti", "Mishra",
				"kmishra@gmail.com");

		employees.getEmployees().add(empOne);
		employees.getEmployees().add(empTwo);
		employees.getEmployees().add(empThree);
	}

	// @RequestMapping(value = "/employees")
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody EmployeeList getAllEmployees() {
		return employees;
	}

	// @RequestMapping(value = "/employees/{id}")
	// @ResponseBody -- this is used if produces is not specified.
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id)
			throws ResourceNotFoundException {
		if (id <= 3) {
			Employee employee = new Employee(1, "Lokesh", "Gupta",
					"howtodoinjava@gmail.com");
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("not found");
		}
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)	
	public Employee updateEmployee(@PathVariable("id") int id, 
			 @RequestBody @Validated Employee entity) //if we don't have @RequestBody, the fields of entity will be null .....
			throws ResourceNotFoundException {
		if (!entity.getId().equals(id)) {
			throw new BadRequestException("id", "the id must be the same");
		}
		boolean found = false;
		for (Employee emp : employees.getEmployees()) {
			if (emp.getId().equals(entity.getId())) {
				emp.setEmail(entity.getEmail());
				emp.setFirstName(entity.getFirstName());
				emp.setLastName(entity.getLastName());
				entity = emp;
				found = true;
				break;
			}
		}
		if (!found) {
			throw new ResourceNotFoundException("not found");
		}
		publisher.publishEvent(new EmployeeEvent(this, entity));
		return entity;
	}

	// You must override this method; It will give you acces to
	// ApplicationEventPublisher
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
}