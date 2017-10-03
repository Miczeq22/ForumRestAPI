package pl.miczeq.service;

import java.util.List;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.User;

public interface UserService
{
	void save(User user) throws DatabaseException, ValidationException;
	
	User findOne(Long id) throws DatabaseException;
	
	List<User> findAll() throws DatabaseException;
	
	void remove(Long id) throws DatabaseException;
}
