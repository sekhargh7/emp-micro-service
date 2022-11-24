package com.equitasit.ms.emp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "emp")
public class Employee {

	@Id
	private Integer empno;

	private String ename;

	private String job;

	private Integer mgr;

	private Date hiredate;

	private Float sal;

	private Float comm;

	private Integer deptno;

}
