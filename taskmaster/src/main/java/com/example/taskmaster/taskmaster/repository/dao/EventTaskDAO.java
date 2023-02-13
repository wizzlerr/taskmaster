package com.example.taskmaster.taskmaster.repository.dao;

import com.example.taskmaster.taskmaster.repository.domain.EventTask;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTaskDAO extends MongoRepository<EventTask, String> {

	@Query("{'notifications' : {type: 'ABSOLUTE'}, 'startDate' : {$gte : $0}}")
	List<EventTask> findAllAbsoluteNotificationType(Date now);

}
