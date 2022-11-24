package com.equitasit.ms.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.equitasit.ms.emp.dto.EmpDTO;
import com.equitasit.ms.emp.entity.Employee;
import com.equitasit.ms.emp.repository.EmpRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { EmpService.class, ModelMapper.class })
 class EmpServiceTest {

	@Autowired
	private EmpService empService;

	@MockBean
	private EmpRepository empRepository;

	@Autowired
	private ModelMapper modelMapper;

	@BeforeEach
	 void init() {

	}

	@Test
	 void testSave() {

		EmpDTO empDTO = getEmpDTO();

		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenReturn(getEmployee(empDTO));

		EmpDTO saved = empService.save(empDTO);

		Assertions.assertNotNull(saved);

		Assertions.assertEquals(1234, saved.getEmpno());
	}

	@Test
	 void testUpdate() {

		EmpDTO empDTO = getEmpDTO();

		Mockito.when(empRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getEmployee()));

		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenReturn(getEmployee(empDTO));

		EmpDTO saved = empService.save(empDTO);

		Assertions.assertNotNull(saved);

		Assertions.assertEquals(1234, saved.getEmpno());
	}

	@Test
	 void testRemove() {

		Integer empno = 1234;

		Mockito.when(empRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getEmployee()));

		Mockito.doNothing().when(empRepository).deleteById(Mockito.anyInt());

		empService.remove(empno);

		Mockito.verify(empRepository).deleteById(Mockito.anyInt());
	}

	@Test
	 void testGet() {

		Integer empno = 1234;

		Mockito.when(empRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getEmployee()));

		EmpDTO empDTO = empService.get(empno);

		Assertions.assertNotNull(empDTO);

		Assertions.assertEquals(1234, empDTO.getEmpno());
	}

	@Test
	 void testGetAll() {

		Mockito.when(empRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getEmployee()));

		Employee employee = getEmployee();

		List<Employee> empList = new ArrayList<Employee>();

		empList.add(employee);

		Mockito.when(empRepository.findAll()).thenReturn(empList);

		List<EmpDTO> retEmpList = empService.getAll();

		Assertions.assertNotNull(retEmpList);

		Assertions.assertTrue(!retEmpList.isEmpty());

		Assertions.assertEquals(1234, retEmpList.get(0).getEmpno());
	}

	private EmpDTO getEmpDTO() {
		EmpDTO empDTO = new EmpDTO();
		empDTO.setEmpno(1234);
		empDTO.setEname("nityaay");
		return empDTO;
	}

	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmpno(1234);
		employee.setEname("nityaay");
		return employee;

	}

	private Employee getEmployee(EmpDTO empDTO) {

		return modelMapper.map(empDTO, Employee.class);
	}

}
