package pl.miczeq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.User;
import pl.miczeq.repository.UserRepository;
import pl.miczeq.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public void save(User user) throws DatabaseException, ValidationException
	{
		if(user.getId() != null && userRepository.findOne(user.getId()) != null)
		{
			userRepository.update(user.getId(), user);
		}
		else
		{
			userRepository.save(user);
		}
	}

	@Override
	public User findOne(Long id) throws DatabaseException
	{
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() throws DatabaseException
	{
		return userRepository.findAll();
	}

	@Override
	public void remove(Long id) throws DatabaseException
	{
		userRepository.remove(id);
	}
}
