package com.example.taskmaster.taskmaster.repository.dao;

import com.example.taskmaster.taskmaster.repository.domain.Task;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO extends MongoRepository<Task, String> {

	Optional<Task> findByIdAndUser(String id, String user);

	void deleteByIdAndUser(String id, String user);

}
