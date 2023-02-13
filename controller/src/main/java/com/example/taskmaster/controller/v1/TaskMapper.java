package com.example.taskmaster.controller.v1;

import com.example.taskmaster.controller.dto.EventTaskDTO;
import com.example.taskmaster.controller.dto.EventTaskUpdateDTO;
import com.example.taskmaster.controller.dto.ReminderTaskDTO;
import com.example.taskmaster.controller.dto.ReminderTaskUpdateDTO;
import com.example.taskmaster.controller.dto.TaskDTO;
import com.example.taskmaster.controller.dto.TaskSearchDTO;
import com.example.taskmaster.controller.dto.TaskUpdateDTO;
import com.example.taskmaster.taskmaster.repository.domain.EventTask;
import com.example.taskmaster.taskmaster.repository.domain.ReminderTask;
import com.example.taskmaster.taskmaster.repository.domain.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	@Mappings({
		@Mapping(target = "total", expression = "java(page.getTotalElements())")
	})
	TaskSearchDTO toTaskSearchDto(Page<Task> page);

	@Mappings({
		@Mapping(source = "taskType", target = "type", defaultValue = "REMINDER")
	})
	ReminderTaskDTO toReminderTaskDTO(ReminderTask reminderTask);

	@Mappings({
		@Mapping(source = "type", target = "taskType", defaultValue = "REMINDER")
	})
	ReminderTask toReminderTask(ReminderTaskDTO reminderTask);

	ReminderTask toReminderTask(ReminderTaskUpdateDTO reminderTask);

	@Mappings({
		@Mapping(source = "taskType", target = "type", defaultValue = "EVENT")
	})
	EventTaskDTO toEventTaskDTO(EventTask eventTask);

	@Mappings({
		@Mapping(source = "type", target = "taskType", defaultValue = "EVENT")
	})
	EventTask toEventTask(EventTaskDTO eventTask);

	EventTask toEventTask(EventTaskUpdateDTO eventTask);

	default TaskDTO toTaskDto(Task task) {
		if (task instanceof ReminderTask) {
			return toReminderTaskDTO((ReminderTask) task);
		} else if (task instanceof EventTask) {
			return toEventTaskDTO((EventTask) task);
		} else {
			throw new IllegalArgumentException("Unknown task type " + task.getTaskType());
		}
	}

	default Task toTask(TaskDTO task) {
		if (task instanceof ReminderTaskDTO) {
			return toReminderTask((ReminderTaskDTO) task);
		} else if (task instanceof EventTaskDTO) {
			return toEventTask((EventTaskDTO) task);
		} else {
			throw new IllegalArgumentException("Unknown task type " + task.getType());
		}
	}

	default Task toTask(TaskUpdateDTO task) {
		if (task instanceof ReminderTaskUpdateDTO) {
			return toReminderTask((ReminderTaskUpdateDTO) task);
		} else if (task instanceof EventTaskUpdateDTO) {
			return toEventTask((EventTaskUpdateDTO) task);
		} else {
			throw new IllegalArgumentException("Unknown task type");
		}
	}

}
