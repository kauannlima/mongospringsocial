package com.kauanlima.mongospringsocial.repository;


import com.kauanlima.mongospringsocial.domain.Comments;
import com.kauanlima.mongospringsocial.dto.CommentDTO;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends MongoRepository<Comments, String> {


}


