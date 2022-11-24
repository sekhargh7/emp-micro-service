package com.equitasit.ms.emp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.equitasit.ms.emp.dto.StatusDTO;
import com.equitasit.ms.emp.exception.EmpException;
import com.equitasit.ms.emp.exception.EmpExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class EmpExceptionHandler {

	@ExceptionHandler(value = EmpException.class)
	public ResponseEntity<?> handleEmpException(EmpException ex) {

		log.error("Error while getting the accounts", ex);

		if (ex.getMessage().equals(EmpExceptionConstants.EMP_NOT_FOUND.getValue())) {

			return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {

		log.error("Error while accessing the accounts app", ex);

		return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
