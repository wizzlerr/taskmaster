import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.taskmaster.app.TaskmasterApplication;
import com.example.taskmaster.controller.dto.EventTaskDTO;
import com.example.taskmaster.controller.dto.ReminderTaskDTO;
import com.example.taskmaster.controller.dto.ReminderTaskUpdateDTO;
import com.example.taskmaster.controller.dto.TaskDTO;
import com.example.taskmaster.controller.dto.TaskSearchDTO;
import com.example.taskmaster.controller.dto.TaskTypeDTO;
import com.example.taskmaster.taskmaster.model.TaskType;
import com.example.taskmaster.taskmaster.repository.dao.TaskDAO;
import com.example.taskmaster.taskmaster.repository.domain.EventTask;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TaskmasterApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class EndToEndTest {

	private final String path = "http://localhost:8080/api/v1/task";

	@Autowired
	TaskDAO taskDAO;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	void setUp() {
		restTemplate = restTemplate.withBasicAuth("user", "password");
	}

	@org.junit.jupiter.api.Test
	void shouldCreateUpdateFindSearchAndDeleteEventAndReminderTasks() {
		//given
		taskDAO.save(new EventTask(null, "name", new Date(), TaskType.EVENT, null, "user2", "v", new Date(), new Date(), null));

		EventTaskDTO eventTaskDTO = new EventTaskDTO(null, "event1", null, null, "venue", new Date(), new Date(), null, TaskTypeDTO.EVENT);

		//when
		final ResponseEntity<TaskDTO> taskDTOResponseEntity = restTemplate.postForEntity(path, eventTaskDTO, TaskDTO.class);

		//then
		assertEquals(HttpStatus.CREATED, taskDTOResponseEntity.getStatusCode());

		//given
		String eventId = taskDTOResponseEntity.getBody().getId();

		//when
		final ResponseEntity<TaskDTO> eventResponseEntity = restTemplate.getForEntity(path + "/" + eventId, TaskDTO.class);

		//then
		assertEquals(HttpStatus.OK, eventResponseEntity.getStatusCode());
		assertEquals(taskDTOResponseEntity.getBody(), eventResponseEntity.getBody());

		//given
		ReminderTaskDTO reminderTaskDTO = new ReminderTaskDTO(null, "reminder", new Date(), null, "reminder", new Date(), TaskTypeDTO.REMINDER);

		//when
		final ResponseEntity<TaskDTO> reminderDtoResponseEntity = restTemplate.postForEntity(path, reminderTaskDTO, TaskDTO.class);

		//then
		assertEquals(HttpStatus.CREATED, reminderDtoResponseEntity.getStatusCode());

		//given
		String reminderId = reminderDtoResponseEntity.getBody().getId();
		final String newReminderName = "new reminder name";
		ReminderTaskUpdateDTO reminderTaskUpdateDTO = new ReminderTaskUpdateDTO(newReminderName, null, null);

		//when
		final ResponseEntity<TaskDTO> reminderTaskUpdateDtoResponseEntity = restTemplate.exchange(path + "/" + reminderId, HttpMethod.PUT, new HttpEntity<>(reminderTaskUpdateDTO), TaskDTO.class);

		//then
		assertEquals(HttpStatus.OK, reminderTaskUpdateDtoResponseEntity.getStatusCode());
		assertEquals(taskDAO.findById(reminderId).get().getName(), newReminderName);

		//given
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path).queryParam("taskType", TaskTypeDTO.EVENT.name())
			.queryParam("size", "10").queryParam("page", 0);

		//when
		final ResponseEntity<TaskSearchDTO> searchDTOResponseEntity = restTemplate.getForEntity(builder.build().encode().toUri(), TaskSearchDTO.class);

		//then
		assertEquals(HttpStatus.OK, searchDTOResponseEntity.getStatusCode());
		assertEquals(1, searchDTOResponseEntity.getBody().getTotal());
		assertEquals(taskDTOResponseEntity.getBody(), searchDTOResponseEntity.getBody().getContent().get(0));

		//when
		restTemplate.delete(path + "/" + eventId);

		//then
		assertFalse(taskDAO.existsById(eventId));
		assertEquals(2, taskDAO.count());
	}
}
