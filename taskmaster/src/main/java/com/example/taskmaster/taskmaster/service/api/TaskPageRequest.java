package com.example.taskmaster.taskmaster.service.api;

import com.example.taskmaster.taskmaster.model.TaskType;
import java.util.Date;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class TaskPageRequest extends PageRequest {

	private final String name;

	private final Date dateCreated;

	private final TaskType taskType;

	public TaskPageRequest(final int page, final int size, final String name, final Date dateCreated, final TaskType taskType) {
		super(page, size, Sort.by("id"));
		this.name = name;
		this.dateCreated = dateCreated;
		this.taskType = taskType;
	}
}
