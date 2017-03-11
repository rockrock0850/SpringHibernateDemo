package com.promeritage.shtest.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.promeritage.shtest.dao.IEmployeeDao;
import com.promeritage.shtest.entity.Employee;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao  {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<Employee> select() {
		Session session = sessionFactory.getCurrentSession();
		List<Employee> list = session.createQuery("from Employee").list();

		return list;
	}

	@Override
	public Employee select(long id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Employee employee = (Employee) session.get(Employee.class, id);

			return employee == null ? null : employee;
		} catch (Exception e) {
			log.error(e, e);
		}
		
		return null;
	}

	@Override
	public Employee insert(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(employee);
			
			return employee;
		} catch (Exception e) {
			log.error(e, e);
		}
		
		return null;
	}

	@Override
	public Employee update(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(employee);
			
			return employee;
		} catch (Exception e) {
			log.error(e, e);
		}
		
		return null;
	}

	@Override
	public void delete(long id) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, id);
        if (null != employee) {
        	this.sessionFactory.getCurrentSession().delete(employee);
        }
	}

}
