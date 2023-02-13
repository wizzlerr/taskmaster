package com.example.taskmaster.taskmaster.service;

import com.example.taskmaster.taskmaster.repository.dao.TaskDAO;
import com.example.taskmaster.taskmaster.repository.domain.EventTask;
import com.example.taskmaster.taskmaster.repository.domain.Task;
import com.example.taskmaster.taskmaster.service.api.TaskMaster;
import com.example.taskmaster.taskmaster.service.api.TaskPageRequest;
import com.example.taskmaster.taskmaster.service.api.exception.EventTaskEndDateBeforeStartDateException;
import com.example.taskmaster.taskmaster.service.api.exception.EventTaskStartDateAfterEndDateException;
import com.example.taskmaster.taskmaster.service.api.exception.TaskNotFoundException;
import com.example.taskmaster.taskmaster.util.BeanUtil;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskMasterService implements TaskMaster {

	private final TaskDAO taskDAO;

	@Override
	public Page<Task> search(final Pageable pageable, final String user) {
		TaskPageRequest taskPageRequest = (TaskPageRequest) pageable;
		final Task exmaple = new Task(taskPageRequest.getName(), taskPageRequest.getDateCreated(), taskPageRequest.getTaskType(), user);
		return taskDAO.findAll(Example.of(exmaple), pageable);
	}

	@Override
	public Task findById(String id, final String user) {
		return taskDAO.findByIdAndUser(id, user).orElseThrow(TaskNotFoundException::new);
	}

	@Override
	public Task create(Task task, final String user) {

		if (task instanceof EventTask && ((EventTask) task).getStartDate().after(((EventTask) task).getEndDate())) {
			throw new EventTaskStartDateAfterEndDateException();
		}

		task.setDateCreated(new Date());
		task.setUser(user);
		return taskDAO.save(task);
	}

	@Override
	public Task update(final String id, final Task task, final String user) {
		final Task byId = findById(id, user);

		if (task instanceof EventTask) {
			EventTask eventTask = (EventTask) task;
			EventTask dbEventTask = (EventTask) byId;

			if (eventTask.getStartDate() != null && eventTask.getStartDate().after(dbEventTask.getEndDate())) {
				throw new EventTaskStartDateAfterEndDateException();
			}

			if (eventTask.getEndDate() != null && eventTask.getEndDate().before(dbEventTask.getStartDate())) {
				throw new EventTaskEndDateBeforeStartDateException();
			}
		}

		BeanUtil.copyProperties(task, byId);

		return taskDAO.save(byId);
	}

	@Override
	public void delete(final String id, final String user) {
		taskDAO.deleteByIdAndUser(id, user);
	}
}
