package com.example.taskmaster.taskmaster.service.api.exception;

public class EventTaskStartDateAfterEndDateException extends RuntimeException {

	public EventTaskStartDateAfterEndDateException() {
		super("Start date must be before end date");
	}
}
