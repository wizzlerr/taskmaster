package com.example.taskmaster.taskmaster.service.api;

import com.example.taskmaster.taskmaster.repository.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskMaster {

	Page<Task> search(Pageable pageable, String user);

	Task findById(String id, String user);

	Task create(Task task, String user);

	Task update(String id, Task task, String user);

	void delete(String id, String user);
}
