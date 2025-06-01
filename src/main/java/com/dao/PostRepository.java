package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.Post;
import com.bean.User;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAllByUser(User user);
}
