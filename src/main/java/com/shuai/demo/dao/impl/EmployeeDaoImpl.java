package com.shuai.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shuai.demo.dao.EmployeeDao;
import com.shuai.demo.model.Employee;
import com.shuai.demo.model.exception.ResourceNotFoundException;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {

        return em.merge(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(Integer employeeId) {
        Employee entity = em.find(Employee.class, employeeId);
        if (null == entity) {
            throw new ResourceNotFoundException("not found");
        }
        return entity;
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        return em.merge(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        Employee entity = em.find(Employee.class, employeeId);
        if (null == entity) {
            throw new ResourceNotFoundException("not found");
        }
        em.remove(entity);
    }

    @Override
	public List<Employee> searchEmployees(String firstName, String lastName,
			String email) {
	    return em.createQuery("SELECT c FROM Employee").getResultList();
	}
}
