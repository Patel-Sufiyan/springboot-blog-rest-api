package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.entity.Post;

/**
 * @author Sufiyan
 * Jpa repository for Post entity
 *
 */
@Repository//not necessary using for readability
public interface PostRepository extends JpaRepository<Post, Long> {

}
