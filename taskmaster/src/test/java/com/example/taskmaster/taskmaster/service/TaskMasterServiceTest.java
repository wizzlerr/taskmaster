package com.example.taskmaster.taskmaster.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.taskmaster.taskmaster.repository.dao.TaskDAO;
import com.example.taskmaster.taskmaster.repository.domain.EventTask;
import com.example.taskmaster.taskmaster.service.api.exception.TaskNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskMasterServiceTest {

	@Mock
	private TaskDAO taskDAO;

	@InjectMocks
	private TaskMasterService taskMasterService;

	@Test
	void shouldThrowTaskNotFoundExceptionWhenRetrievingNonExistentId() {
		//given
		String id = UUID.randomUUID().toString();
		String user = "user";

		when(taskDAO.findByIdAndUser(id, user)).thenThrow(TaskNotFoundException.class);

		//when

		//then
		assertThrows(TaskNotFoundException.class, () -> taskMasterService.findById(id, user));
	}

	@Test
	void shouldUpdateTask() {
		//given
		String id = UUID.randomUUID().toString();
		String user = "user";

		EventTask dbTask = new EventTask("venue", new Date(), new Date(), new ArrayList<>());

		when(taskDAO.findByIdAndUser(id, user)).thenReturn(Optional.of(dbTask));

		EventTask update = new EventTask();
		update.setVenue("new velue");

		//when
		taskMasterService.update(id, update, user);

		//then
		verify(taskDAO, times(1)).save(new EventTask("new velue", dbTask.getStartDate(), dbTask.getEndDate(), new ArrayList<>()));
	}
}