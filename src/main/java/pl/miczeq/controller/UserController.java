package pl.miczeq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.User;
import pl.miczeq.service.UserService;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController
{
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@PostMapping("/")
	public @ResponseBody Map<String, Object> save(@RequestBody User user)
	{
		Map<String, Object> map = new HashMap<>();
		
		try
		{
			userService.save(user);
			map.put("status", "success");
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
			map.put("users", userService.findAll());
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
			map.put("user", userService.findOne(id));
			map.put("status", "success");
		}
		catch(DatabaseException e)
		{
			map.put("status", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody Map<String, Object> remove(@PathVariable("id") Long id)
	{
		Map<String, Object> map = new HashMap<>();
		
		try
		{
			userService.remove(id);
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
