package com.example.taskmaster.taskmaster.service.api.exception;

public class EventTaskEndDateBeforeStartDateException extends RuntimeException {

	public EventTaskEndDateBeforeStartDateException() {
		super("End date must be after start date");
	}
}
