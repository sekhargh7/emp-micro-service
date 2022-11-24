package com.equitasit.ms.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equitasit.ms.emp.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
