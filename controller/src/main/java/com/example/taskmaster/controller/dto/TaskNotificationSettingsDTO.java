package com.example.taskmaster.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskNotificationSettingsDTO {

	private NotificationTypeDTO type;
	private Long value;

}
