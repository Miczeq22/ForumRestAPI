package pl.miczeq.service;

import java.util.List;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.Comment;

public interface CommentService
{
	void save(Comment comment) throws DatabaseException, ValidationException;
	
	Comment findOne(Long id) throws DatabaseException;
	
	List<Comment> findAll() throws DatabaseException;
	
	List<Comment> findAllByTopicId(Long id) throws DatabaseException;
	
	void remove(Long id) throws DatabaseException;
}
