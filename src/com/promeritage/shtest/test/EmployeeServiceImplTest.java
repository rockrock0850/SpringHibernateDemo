package com.promeritage.shtest.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.promeritage.shtest.dao.IEmployeeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config.xml")
@Service
public class EmployeeServiceImplTest {

	@Autowired
    private IEmployeeDao employeeDao;
	
	@Test
	@Transactional("transactionManager")
	public void testSelect() {
		Assert.assertNotNull(employeeDao.select());
	}

}
