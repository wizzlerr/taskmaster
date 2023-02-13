package com.example.taskmaster.controller.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	property = "type")
@JsonSubTypes({
	@Type(value = EventTaskDTO.class, name = "EVENT"),
	@Type(value = ReminderTaskDTO.class, name = "REMINDER")
})
public abstract class TaskDTO {

	protected String id;

	@NotBlank
	protected String name;

	protected Date dateCreated;

	protected List<TaskNotificationSettingsDTO> notifications;

	public abstract TaskTypeDTO getType();

}
