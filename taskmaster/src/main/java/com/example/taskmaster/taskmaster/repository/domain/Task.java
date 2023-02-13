package com.example.taskmaster.taskmaster.repository.domain;

import com.example.taskmaster.taskmaster.model.TaskNotificationSettings;
import com.example.taskmaster.taskmaster.model.TaskType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task")
public class Task {

	@Id
	protected String id;

	protected String name;

	protected Date dateCreated;

	protected TaskType taskType;

	protected List<TaskNotificationSettings> notifications;

	protected String user;

	public Task(final String name, final Date dateCreated, final TaskType taskType, final String user) {
		this.name = name;
		this.dateCreated = dateCreated;
		this.taskType = taskType;
		this.user = user;
	}
}
