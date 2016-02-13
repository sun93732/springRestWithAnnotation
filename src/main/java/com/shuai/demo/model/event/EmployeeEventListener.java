package com.shuai.demo.model.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventListener implements ApplicationListener<EmployeeEvent>
{
    public void onApplicationEvent(EmployeeEvent event)
    {
        EmployeeEvent employeeEvent = (EmployeeEvent) event;
 
        System.out.println("Employee " +  " with details : " + employeeEvent.getEmployee());
 
        // Do more processing as per application logic
    }
}