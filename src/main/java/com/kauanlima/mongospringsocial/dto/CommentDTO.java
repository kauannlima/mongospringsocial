package com.kauanlima.mongospringsocial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.kauanlima.mongospringsocial.domain.Comments;
import com.kauanlima.mongospringsocial.domain.Post;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private String Id;
    private String text;
    private AuthorDTO authorDTO;

    public CommentDTO(Comments obj) {
        Id = obj.getId();
        this.text = obj.getText();
        this.authorDTO = getAuthorDTO();
    }

}
