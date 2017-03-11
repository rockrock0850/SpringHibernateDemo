package com.promeritage.shtest.dao;

import java.util.List;

import com.promeritage.shtest.entity.Employee;

public interface IEmployeeDao {
	public List<Employee> select();

	public Employee select(long id);
	
	public Employee insert(Employee employee);

	public Employee update(Employee employee);

	public void delete(long id);
}