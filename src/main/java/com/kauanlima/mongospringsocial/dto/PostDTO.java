package com.kauanlima.mongospringsocial.dto;

import com.kauanlima.mongospringsocial.domain.Post;
import com.kauanlima.mongospringsocial.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private String id;
    private String title;
    private String body;
    private AuthorDTO author;

    public PostDTO() {

    }

    public PostDTO(Post obj) {
        id = obj.getId();
        title = obj.getTitle();
        body = obj.getBody();
        author = obj.getAuthor();
    }
}
