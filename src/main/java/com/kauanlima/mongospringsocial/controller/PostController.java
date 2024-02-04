package com.kauanlima.mongospringsocial.controller;

import com.kauanlima.mongospringsocial.controller.util.URL;
import com.kauanlima.mongospringsocial.domain.Post;
import com.kauanlima.mongospringsocial.domain.User;
import com.kauanlima.mongospringsocial.dto.PostDTO;
import com.kauanlima.mongospringsocial.dto.UserDTO;
import com.kauanlima.mongospringsocial.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/titlesearch/{title}")
    public ResponseEntity<List<Post>> findyByTitle(@PathVariable String title){
        List<Post> list = postService.findByTitle(title);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullsearch/")
    public ResponseEntity<List<Post>> findyByFull(@RequestParam(value = "text", defaultValue = "") String text,
                                                  @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                  @RequestParam(value = "maxDate", defaultValue = "") String maxDate ){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody PostDTO objDto) {
        Post obj = postService.fromDTO(objDto);
        obj = postService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
