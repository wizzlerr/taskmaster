package com.example.taskmaster.controller.v1;

import com.example.taskmaster.controller.dto.TaskDTO;
import com.example.taskmaster.controller.dto.TaskSearchDTO;
import com.example.taskmaster.controller.dto.TaskSearchParams;
import com.example.taskmaster.controller.dto.TaskUpdateDTO;
import com.example.taskmaster.taskmaster.model.TaskType;
import com.example.taskmaster.taskmaster.service.api.TaskMaster;
import com.example.taskmaster.taskmaster.service.api.TaskPageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.security.Principal;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
public class TaskController {

	private final TaskMaster taskMasterService;
	private final TaskMapper taskMapper;

	@GetMapping("/{id}")
	@Operation(summary = "Fetch Task by id")
	@Parameter(name = "id", description = "Task id")
	@RolesAllowed("USER")
	public ResponseEntity<TaskDTO> findById(@PathVariable("id") String id, Principal principal) {
		return ResponseEntity.ok(taskMapper.toTaskDto(taskMasterService.findById(id, principal.getName())));
	}

	@GetMapping
	@Operation(summary = "Search for tasks")
	@RolesAllowed("USER")
	public ResponseEntity<TaskSearchDTO> searchForTask(TaskSearchParams taskSearchParams, Principal principal) {
		return ResponseEntity.ok(taskMapper.toTaskSearchDto(taskMasterService.search(
			new TaskPageRequest(taskSearchParams.getPage(), taskSearchParams.getSize(), taskSearchParams.getName(), taskSearchParams.getDateCreated(),
				taskSearchParams.getTaskType() == null ? null : TaskType.valueOf(taskSearchParams.getTaskType().name())), principal.getName())));
	}

	@PostMapping
	@Operation(summary = "Create a Task")
	@RolesAllowed("USER")
	public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO task, Principal principal) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toTaskDto(taskMasterService.create(taskMapper.toTask(task), principal.getName())));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a Task")
	@RolesAllowed("USER")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") String id, @RequestBody TaskUpdateDTO task, Principal principal) {
		return ResponseEntity.ok(taskMapper.toTaskDto(taskMasterService.update(id, taskMapper.toTask(task), principal.getName())));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Task")
	@RolesAllowed("USER")
	public ResponseEntity<Void> deleteTask(@PathVariable("id") String id, Principal principal) {
		taskMasterService.delete(id, principal.getName());
		return ResponseEntity.ok().build();
	}

}
