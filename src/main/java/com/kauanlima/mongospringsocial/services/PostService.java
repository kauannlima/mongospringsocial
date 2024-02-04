package com.kauanlima.mongospringsocial.services;

import com.kauanlima.mongospringsocial.domain.Post;
import com.kauanlima.mongospringsocial.domain.User;
import com.kauanlima.mongospringsocial.dto.PostDTO;
import com.kauanlima.mongospringsocial.dto.UserDTO;
import com.kauanlima.mongospringsocial.repository.PostRepository;
import com.kauanlima.mongospringsocial.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

  public List<Post> findAll(){
       return repo.findAll();
   }

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
      return repo.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime()+24*60*60*1000);
        return repo.fullSearch(text,minDate,maxDate);
    }

 public Post insert(Post obj) {
        return repo.insert(obj);
    }

    public Post fromDTO(PostDTO objDto) {
        return new Post(objDto.getId(), objDto.getTitle(), objDto.getBody(), objDto.getAuthor());
    }


}
