package com.equitasit.ms.emp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equitasit.ms.emp.dto.EmpDTO;
import com.equitasit.ms.emp.entity.Employee;
import com.equitasit.ms.emp.exception.EmpException;
import com.equitasit.ms.emp.exception.EmpExceptionConstants;
import com.equitasit.ms.emp.repository.EmpRepository;

@Service
public class EmpService {

	@Autowired
	private EmpRepository empRepository;

	@Autowired
	private ModelMapper modelMapper;

	public EmpDTO save(EmpDTO empDTO) {

		Employee employee = modelMapper.map(empDTO, Employee.class);

		Employee savedEmployee = empRepository.save(employee);

		return modelMapper.map(savedEmployee, EmpDTO.class);
	}

	public EmpDTO update(EmpDTO empDTO) {

		Optional<Employee> empOptional = empRepository.findById(empDTO.getEmpno());

		if (!empOptional.isPresent()) {

			throw new EmpException(EmpExceptionConstants.EMP_NOT_FOUND.getValue());
		}

		Employee employee = modelMapper.map(empDTO, Employee.class);

		Employee savedEmployee = empRepository.save(employee);

		return modelMapper.map(savedEmployee, EmpDTO.class);
	}

	public void remove(Integer empno) {

		Optional<Employee> empOptional = empRepository.findById(empno);

		if (!empOptional.isPresent()) {

			throw new EmpException(EmpExceptionConstants.EMP_NOT_FOUND.getValue());
		}

		empRepository.deleteById(empno);

	}

	public EmpDTO get(Integer empno) {

		Optional<Employee> empOptional = empRepository.findById(empno);

		if (!empOptional.isPresent()) {

			throw new EmpException(EmpExceptionConstants.EMP_NOT_FOUND.getValue());
		}

		return modelMapper.map(empOptional.get(), EmpDTO.class);

	}

	public List<EmpDTO> getAll() {

		List<EmpDTO> empList = empRepository.findAll().stream().map(emp -> modelMapper.map(emp, EmpDTO.class))
				.collect(Collectors.toList());

		return empList;

	}
}
