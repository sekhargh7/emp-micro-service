package com.equitasit.ms.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equitasit.ms.emp.dto.EmpDTO;
import com.equitasit.ms.emp.service.EmpService;



@RestController
@RequestMapping("/emp")
@SuppressWarnings("rawtypes")
public class EmpController {

	@Autowired
	private EmpService empService;

	@PostMapping
	public ResponseEntity save(@RequestBody EmpDTO empDTO) {

		ResponseEntity responseEntity = null;

		EmpDTO savedEmpDTO = empService.save(empDTO);

		responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(savedEmpDTO);

		return responseEntity;

	}

	@PutMapping
	public ResponseEntity update(@RequestBody EmpDTO empDTO) {

		ResponseEntity responseEntity = null;

		EmpDTO savedEmpDTO = empService.update(empDTO);

		responseEntity = ResponseEntity.status(HttpStatus.OK).body(savedEmpDTO);

		return responseEntity;

	}

	@DeleteMapping("/{empId}")
	public ResponseEntity remove(@PathVariable("empId") Integer empId) {

		ResponseEntity responseEntity = null;

		empService.remove(empId);
		
		responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return responseEntity;

	}

	@GetMapping("/{empId}")
	public ResponseEntity get(@PathVariable("empId") Integer empId) {

		
		ResponseEntity responseEntity = null;

		EmpDTO empDTO = empService.get(empId);
		responseEntity = ResponseEntity.status(HttpStatus.OK).body(empDTO);

		return responseEntity;

	}

	@GetMapping
	public ResponseEntity getAll() {

		ResponseEntity responseEntity = null;

		List<EmpDTO> empDTOList = empService.getAll();
		
		responseEntity = ResponseEntity.status(HttpStatus.OK).body(empDTOList);

		return responseEntity;

	}

}
