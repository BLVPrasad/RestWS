package com.hexa.innovation.RestWS;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp")
public class Employee {
	
	@Id
	//@GeneratedValue
	
  	private int empId;
	
	private String empName;

	public Employee() {
		
	
	}

	public Employee(int empId, String empName) {
		//super();
		this.empId = empId;
		this.empName = empName;
	}

	

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	 @Override
	    public String toString() {
	        return "Employee [empId=" + empId + ", empName=" + empName +  "]";
	}

}
