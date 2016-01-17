package com.shuai.demo.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuai.demo.event.EmployeeEvent;
import com.shuai.demo.exception.ResourceNotFoundException;
import com.shuai.demo.model.EmployeeListVO;
import com.shuai.demo.model.EmployeeVO;

@RestController
public class EmployeeRESTController implements ApplicationEventPublisherAware {
	// @RequestMapping(value = "/employees")
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody EmployeeListVO getAllEmployees() {
		EmployeeListVO employees = new EmployeeListVO();

		EmployeeVO empOne = new EmployeeVO(1, "Lokesh", "Gupta",
				"howtodoinjava@gmail.com");
		
		EmployeeVO empTwo = new EmployeeVO(2, "Amit", "Singhal",
				"asinghal@yahoo.com");
		EmployeeVO empThree = new EmployeeVO(3, "Kirti", "Mishra",
				"kmishra@gmail.com");

		employees.getEmployees().add(empOne);
		employees.getEmployees().add(empTwo);
		employees.getEmployees().add(empThree);
		publisher.publishEvent(new EmployeeEvent(this, empThree));
		return employees;
	}

	// @RequestMapping(value = "/employees/{id}")
	// @ResponseBody
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable("id") int id) throws ResourceNotFoundException {
		if (id <= 3) {
			EmployeeVO employee = new EmployeeVO(1, "Lokesh", "Gupta",
					"howtodoinjava@gmail.com");
			return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("not found");
		}
	}

	private ApplicationEventPublisher publisher;

	// You must override this method; It will give you acces to
	// ApplicationEventPublisher
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
}