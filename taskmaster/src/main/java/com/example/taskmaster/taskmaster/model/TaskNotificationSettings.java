package com.example.taskmaster.taskmaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskNotificationSettings {

	private NotificationType type;
	private Long value;

}
