package com.kauanlima.mongospringsocial.domain;

import com.kauanlima.mongospringsocial.dto.AuthorDTO;
import com.kauanlima.mongospringsocial.dto.CommentDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document
public class Post {

    private List<Comments> comments = new ArrayList<>();

    @Id
    private String Id;
    private String title;
    private String body;
    private AuthorDTO author;

    public Post(String id, String title, String body, AuthorDTO author) {
        Id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }
}
