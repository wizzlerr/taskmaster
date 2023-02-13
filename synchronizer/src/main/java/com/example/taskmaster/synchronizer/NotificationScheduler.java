package com.example.taskmaster.synchronizer;

import com.example.taskmaster.taskmaster.repository.dao.EventTaskDAO;
import com.example.taskmaster.taskmaster.repository.dao.ReminderTaskDAO;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationScheduler {

	private final ReminderTaskDAO reminderTaskDAO;
	private final EventTaskDAO eventTaskDAO;

	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask() {
		Date now = new Date();

		reminderTaskDAO.findAllAbsoluteNotificationType(now);
		eventTaskDAO.findAllAbsoluteNotificationType(now);

		//send notifications
	}

}
