package com.springboot.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private ModelMapper modelMapper;

	@Autowired
	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub

//		postRepository.saveAndFlush(postDto);

		return mapToDto(postRepository.save(mapToEntity(postDto)));
	}

	@Override
	public List<PostDto> getAllPost(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub

		//use PagingandSortingRepository findAll(pageAble)
		
		Pageable pageable =  PageRequest.of(pageNumber, pageSize); 
		Page<Post>  postPageList = postRepository.findAll(pageable);
		
		List<Post> listOfPost=postPageList.getContent();  /// getcontent returns the list of post from Page object
		
//		return postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
		return listOfPost.stream().map(post-> mapToDto(post)).collect(Collectors.toList());

	}
	
	@Override
	public PostDto getPostById(Long id ) {
		return  mapToDto(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id "+id +"not Present")));
	}

	private Post mapToEntity(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);

	}

	private PostDto mapToDto(Post post) {

		return modelMapper.map(post, PostDto.class);
	}

}
