package com.example.taskmaster.controller.v1;

import com.example.taskmaster.controller.dto.ErrorResponseDTO;
import com.example.taskmaster.taskmaster.service.api.exception.EventTaskEndDateBeforeStartDateException;
import com.example.taskmaster.taskmaster.service.api.exception.EventTaskStartDateAfterEndDateException;
import com.example.taskmaster.taskmaster.service.api.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value
		= {TaskNotFoundException.class})
	protected ResponseEntity<ErrorResponseDTO> handleNotFound(
		RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value
		= {EventTaskEndDateBeforeStartDateException.class, EventTaskStartDateAfterEndDateException.class})
	protected ResponseEntity<ErrorResponseDTO> handleNotAcceptable(
		RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

}
