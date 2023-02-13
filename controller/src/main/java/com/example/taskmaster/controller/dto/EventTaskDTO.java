package com.example.taskmaster.controller.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventTaskDTO extends TaskDTO {

	@NotBlank
	private String venue;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;

	private List<String> willingParticipants;

	@NotNull
	private TaskTypeDTO type;

	public EventTaskDTO(final String id, @NotBlank final String name, final Date dateCreated, final List<TaskNotificationSettingsDTO> notifications, final String venue, final Date startDate,
		final Date endDate, final List<String> willingParticipants,
		final TaskTypeDTO type) {
		super(id, name, dateCreated, notifications);
		this.venue = venue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.willingParticipants = willingParticipants;
		this.type = type;
	}
}
