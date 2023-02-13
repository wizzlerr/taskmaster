package com.example.taskmaster.controller.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderTaskDTO extends TaskDTO {

	@NotBlank
	private String reminderText;

	@NotNull
	private Date reminderDate;

	@NotNull
	private TaskTypeDTO type;

	public ReminderTaskDTO(final String id, @NotBlank final String name, final Date dateCreated, final List<TaskNotificationSettingsDTO> notifications, final String reminderText) {
		super(id, name, dateCreated, notifications);
		this.reminderText = reminderText;
	}

	public ReminderTaskDTO(final String id, @NotBlank final String name, final Date dateCreated, final List<TaskNotificationSettingsDTO> notifications, final String reminderText,
		final Date reminderDate, final TaskTypeDTO type) {
		super(id, name, dateCreated, notifications);
		this.reminderText = reminderText;
		this.reminderDate = reminderDate;
		this.type = type;
	}
}
