package com.promeritage.shtest.entity;

// default package
// Generated 2016/7/7 �U�� 12:08:42 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

/**
 * Employee generated by hbm2java
 */
public class Employee implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3843577225286388397L;
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthDate;
	
	private String cellPhone;

	public Employee() {
	}

	public Employee(Date birthDate, String cellPhone) {
		this.birthDate = birthDate;
		this.cellPhone = cellPhone;
	}

	public Employee(String firstName, String lastName, Date birthDate, String cellPhone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.cellPhone = cellPhone;
	}

	public Long getId() {
		return this.id;
	}

	@Required
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

}
