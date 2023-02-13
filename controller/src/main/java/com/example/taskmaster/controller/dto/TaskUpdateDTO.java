package com.example.taskmaster.controller.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
	@Type(value = EventTaskUpdateDTO.class, name = "EVENT"),
	@Type(value = ReminderTaskUpdateDTO.class, name = "REMINDER")
})
public abstract class TaskUpdateDTO {

	protected String name;

}
