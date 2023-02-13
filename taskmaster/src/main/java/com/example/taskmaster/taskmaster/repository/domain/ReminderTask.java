package com.example.taskmaster.taskmaster.repository.domain;

import com.example.taskmaster.taskmaster.model.TaskNotificationSettings;
import com.example.taskmaster.taskmaster.model.TaskType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task")
@TypeAlias("REMINDER")
public class ReminderTask extends Task {

	private String reminderText;
	private Date reminderDate;

	public ReminderTask(final String id, final String name, final Date dateCreated, final TaskType taskType,
		final List<TaskNotificationSettings> notifications, final String user, final String reminderText, final Date reminderDate) {
		super(id, name, dateCreated, taskType, notifications, user);
		this.reminderText = reminderText;
		this.reminderDate = reminderDate;
	}
}
