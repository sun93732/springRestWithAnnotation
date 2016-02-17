package com.shuai.demo.controller;

import javax.annotation.Resource;

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

import com.shuai.demo.model.Employee;
import com.shuai.demo.model.EmployeeList;
import com.shuai.demo.model.event.EmployeeEvent;
import com.shuai.demo.model.exception.BadRequestException;
import com.shuai.demo.model.exception.ResourceNotFoundException;
import com.shuai.demo.service.EmployeeService;

@RestController
public class EmployeeRESTController implements ApplicationEventPublisherAware {

    // one subclass of ApplicationListener will be listening for this event.
    private ApplicationEventPublisher publisher;

    @Resource
    private EmployeeService service;
    
    /* You must override this method; It will give you acces to ApplicationEventPublisher*/
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    // @RequestMapping(value = "/employees")
    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EmployeeList getAllEmployees() {
        return null;
    }

    // @RequestMapping(value = "/employees/{id}")
    // @ResponseBody -- this is used if produces is not specified.
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id)
                    throws ResourceNotFoundException {

        Employee entity = service.getEmployee(id);
        if (null != entity) {

            return new ResponseEntity<Employee>(entity, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("not found");
        }
    }

    /**
     *  Most of the time the @Valid & @Validated can interchange. one is from javax, while the other Spring.
     * @param id
     * @param entity
     * @return
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("id") int id,
                    @RequestBody @Validated Employee entity) throws ResourceNotFoundException {

        /*
         * f we don't have @RequestBody, the fields of entity will be null .....
         * Need to put some validator instance to the classpath like
         * hibernate-validator, or else the validationfactory won't work check
         * the beanValiidation() method in ServiceConfig.java
         */

        if (!entity.getId().equals(id)) {
            throw new BadRequestException("id", "the id must be the same");
        }
        Employee entityExit = service.getEmployee(id);
        if (null == entityExit) {
            throw new ResourceNotFoundException("not found");
        } else
        {
            entity = service.updateEmployee(entity);
        }
        publisher.publishEvent(new EmployeeEvent(this, entity));
        return entity;
    }

    /**
    
     * 
     * @param id
     * @param entity
     * @return
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/employees", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Employee createEmployee(@RequestBody @Validated Employee entity)
                    throws ResourceNotFoundException {
        return service.createEmployee(entity);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable Integer id) {
        service.deleteEmployee(id);
    }
    // Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {    
}