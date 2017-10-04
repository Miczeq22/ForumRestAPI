package pl.miczeq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.Topic;
import pl.miczeq.service.TopicService;

@CrossOrigin
@Controller
@RequestMapping("/topic")
public class TopicController
{
	private final TopicService topicService;

	@Autowired
	public TopicController(TopicService topicService)
	{
		this.topicService = topicService;
	}
	
	@PostMapping("/")
	public @ResponseBody Map<String, Object> save(@RequestBody Topic topic)
	{
		Map<String, Object> map = new HashMap<>();
		
		try
		{
			map.put("status", "success");
			topicService.save(topic);
		}
		catch(DatabaseException | ValidationException e)
		{
			map.put("status", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
	
	@GetMapping("/")
	public @ResponseBody Map<String, Object> findAll()
	{
		Map<String, Object> map = new HashMap<>();
		
		try
		{
			map.put("topics", topicService.findAll());
			map.put("status", "success");
		}
		catch(DatabaseException e)
		{
			map.put("status", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Map<String, Object> findOne(@PathVariable("id") Long id)
	{
		Map<String, Object> map = new HashMap<>();
		
		try
		{
			map.put("topic", topicService.findOne(id));
			map.put("status", "success");
		}
		catch(DatabaseException e)
		{
			map.put("status", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
}
