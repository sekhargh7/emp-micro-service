package com.equitasit.ms.emp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
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
