package com.equitasit.ms.emp.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmpDTO {

	private Integer empno;

	private String ename;

	private String job;

	private Integer mgr;

	private Date hiredate;

	private Float sal;

	private Float comm;

	private Integer deptno;
}
