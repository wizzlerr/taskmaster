package com.example.taskmaster.controller.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventTaskUpdateDTO extends TaskUpdateDTO {

	private String venue;
	private Date startDate;
	private Date endDate;
	private List<String> willingParticipants;

	public EventTaskUpdateDTO(final String name, final String venue, final Date startDate, final Date endDate, final List<String> willingParticipants) {
		super(name);
		this.venue = venue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.willingParticipants = willingParticipants;
	}
}
