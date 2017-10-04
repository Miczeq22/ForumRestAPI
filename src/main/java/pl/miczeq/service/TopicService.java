package pl.miczeq.service;

import java.util.List;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.Topic;

public interface TopicService
{
	void save(Topic topic) throws DatabaseException, ValidationException;
	
	Topic findOne(Long id) throws DatabaseException;
	
	List<Topic> findAll() throws DatabaseException;
	
	void remove(Long id) throws DatabaseException;
}
