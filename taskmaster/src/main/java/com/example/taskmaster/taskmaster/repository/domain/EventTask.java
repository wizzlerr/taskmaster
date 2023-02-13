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
@TypeAlias("EVENT")
public class EventTask extends Task {

	private String venue;
	private Date startDate;
	private Date endDate;
	private List<String> willingParticipants;

	public EventTask(final String id, final String name, final Date dateCreated, final TaskType taskType,
		final List<TaskNotificationSettings> notifications, final String user, final String venue, final Date startDate, final Date endDate, final List<String> willingParticipants) {
		super(id, name, dateCreated, taskType, notifications, user);
		this.venue = venue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.willingParticipants = willingParticipants;
	}
}
