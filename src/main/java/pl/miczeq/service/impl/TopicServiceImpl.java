package pl.miczeq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.Topic;
import pl.miczeq.repository.TopicRepository;
import pl.miczeq.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService
{
	private final TopicRepository topicRepository;

	@Autowired
	public TopicServiceImpl(TopicRepository topicRepository)
	{
		this.topicRepository = topicRepository;
	}

	@Override
	public void save(Topic topic) throws DatabaseException, ValidationException
	{
		if(topic.getId() != null && topicRepository.findOne(topic.getId()) != null)
		{
			topicRepository.update(topic.getId(), topic);
		}
		else
		{
			topicRepository.save(topic);
		}
	}

	@Override
	public Topic findOne(Long id) throws DatabaseException
	{
		return topicRepository.findOne(id);
	}

	@Override
	public List<Topic> findAll() throws DatabaseException
	{
		return topicRepository.findAll();
	}

	@Override
	public void remove(Long id) throws DatabaseException
	{
		topicRepository.remove(id);
	}
}
