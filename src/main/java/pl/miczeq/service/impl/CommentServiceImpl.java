package pl.miczeq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miczeq.exception.DatabaseException;
import pl.miczeq.exception.ValidationException;
import pl.miczeq.model.Comment;
import pl.miczeq.repository.CommentRepository;
import pl.miczeq.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{
	private final CommentRepository commentRepository;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository)
	{
		this.commentRepository = commentRepository;
	}

	@Override
	public void save(Comment comment) throws DatabaseException, ValidationException
	{
		if(comment.getId() != null && commentRepository.findOne(comment.getId()) != null)
		{
			commentRepository.update(comment.getId(), comment);
		}
		else
		{
			commentRepository.save(comment);
		}
	}

	@Override
	public Comment findOne(Long id) throws DatabaseException
	{
		return commentRepository.findOne(id);
	}

	@Override
	public List<Comment> findAll() throws DatabaseException
	{
		return commentRepository.findAll();
	}
	
	public List<Comment> findAllByTopicId(Long id) throws DatabaseException
	{
		return commentRepository.findAllByTopicId(id);
	}

	@Override
	public void remove(Long id) throws DatabaseException
	{
		commentRepository.remove(id);
	}
}
