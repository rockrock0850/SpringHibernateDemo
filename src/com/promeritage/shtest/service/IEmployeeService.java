package com.promeritage.shtest.service;

import java.util.List;

import com.promeritage.shtest.entity.Employee;
import com.promeritage.shtest.vo.form.EmployeeVO;

public interface IEmployeeService {
	public List<Employee> select();
	
	public Employee select(long id);
	
	public Employee insert(EmployeeVO employeeVO);

	public Employee update(EmployeeVO employeeVO);

	public void delete(long id);

}
