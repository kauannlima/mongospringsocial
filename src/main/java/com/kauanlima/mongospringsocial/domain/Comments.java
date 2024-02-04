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
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Comments {

    private List<Post> post = new ArrayList<>();

    @Id
    private String Id;
    private String text;
    private AuthorDTO authorDTO;

    public Comments(String id, String text, AuthorDTO authorDTO) {
        Id = id;
        this.text = text;
        this.authorDTO = authorDTO;
    }

  
}
