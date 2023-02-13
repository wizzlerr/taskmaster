package com.example.taskmaster.controller.dto;

import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchParams {

	@NotNull
	@Min(1)
	private int size;

	@NotNull
	@Min(0)
	private int page;

	private String name;

	private Date dateCreated;

	private TaskTypeDTO taskType;

}
