package com.example.taskmaster.taskmaster.service.api.exception;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException() {
		super("Task not found");
	}
}
