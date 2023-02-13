package com.example.taskmaster.taskmaster.repository.dao;

import com.example.taskmaster.taskmaster.repository.domain.ReminderTask;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderTaskDAO extends MongoRepository<ReminderTask, String> {

	@Query("{'notifications' : {type: 'ABSOLUTE'}, 'reminderDate' : {$gte : $0}}")
	List<ReminderTask> findAllAbsoluteNotificationType(Date now);

}
