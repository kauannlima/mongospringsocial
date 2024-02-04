package com.kauanlima.mongospringsocial.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id;
    private String nome;
    @Indexed(unique = true)
    private String email;
    private String senha;

    public User(String id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        setSenha(senha);
    }

     public void setSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senha);
    }
    

    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

}
