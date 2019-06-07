package com.ufrn.imd.acaideira.data;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import employee.presentation.Employee;

@Named
@RequestScoped
public class EmployeeDAO {
	@PersistenceContext(unitName = "EmployeePU")
	private EntityManager entityManager;

	@Resource
	private UserTransaction userTransaction;

	public Employee addNew(Employee employee) {
		try {
			userTransaction.begin();
			entityManager.persist(employee);
			userTransaction.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> findEmployees() {
		TypedQuery<Employee> query = entityManager.createNamedQuery("findAllEmployees", Employee.class);
		return query.getResultList();
	}
	
	public Employee findEmployee (Long id) {
		Employee query = entityManager.find(Employee.class, id);
		return query;
	}

	public void updateEmployee (Employee e) {
		try {
			userTransaction.begin();
			entityManager.merge(e);
			userTransaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void remove(Long id) {
		try {
			userTransaction.begin();
			Employee e = findEmployee(id);
			entityManager.remove(e);
			userTransaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
