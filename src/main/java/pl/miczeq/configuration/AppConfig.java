package pl.miczeq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.miczeq.repository.CommentRepository;
import pl.miczeq.repository.TopicRepository;
import pl.miczeq.repository.UserRepository;
import pl.miczeq.repository.impl.CommentRepositoryImpl;
import pl.miczeq.repository.impl.TopicRepositoryImpl;
import pl.miczeq.repository.impl.UserRepositoryImpl;

@Configuration
public class AppConfig
{
	@Bean
	public UserRepository userRepository()
	{
		return new UserRepositoryImpl();
	}
	
	@Bean
	public TopicRepository topicRepository() 
	{
		return new TopicRepositoryImpl();
	}
	
	@Bean
	public CommentRepository commentRepository()
	{
		return new CommentRepositoryImpl();
	}
}
