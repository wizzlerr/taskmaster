package com.example.taskmaster.controller.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReminderTaskUpdateDTO extends TaskUpdateDTO {

	private String reminderText;
	private Date reminderDate;

	public ReminderTaskUpdateDTO(final String name, final String reminderText, final Date reminderDate) {
		super(name);
		this.reminderText = reminderText;
		this.reminderDate = reminderDate;
	}
}
