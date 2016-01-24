package com.shuai.demo.model;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement (name="employees")
public class EmployeeList
{
    private List<Employee> employees = new ArrayList<Employee>();
 
    public List<Employee> getEmployees() {
        return employees;
    }
 
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}