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
import pl.miczeq.model.Comment;
import pl.miczeq.service.CommentService;

@CrossOrigin
@Controller
@RequestMapping("/comment")
public class CommentController
{
	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService)
	{
		this.commentService = commentService;
	}

	@PostMapping("/")
	public @ResponseBody Map<String, Object> save(@RequestBody Comment comment)
	{
		Map<String, Object> map = new HashMap<>();

		try
		{
			commentService.save(comment);
			map.put("status", "success");
		} catch (DatabaseException | ValidationException e)
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
			map.put("comments", commentService.findAll());
			map.put("status", "success");
		} catch (DatabaseException e)
		{
			map.put("status", "Error: " + e.getMessage());
			e.printStackTrace();
		}

		return map;
	}

	@GetMapping("/topic/{id}")
	public @ResponseBody Map<String, Object> findAllByTopicId(@PathVariable("id") Long id)
	{
		Map<String, Object> map = new HashMap<>();

		try
		{
			map.put("comments", commentService.findAllByTopicId(id));
			map.put("status", "success");
		} catch (DatabaseException e)
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
			map.put("comment", commentService.findOne(id));
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
