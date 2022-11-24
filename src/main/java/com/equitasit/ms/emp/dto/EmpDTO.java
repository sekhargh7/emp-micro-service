package com.equitasit.ms.emp.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
