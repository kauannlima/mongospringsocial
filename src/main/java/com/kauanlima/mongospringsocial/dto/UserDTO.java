package com.kauanlima.mongospringsocial.dto;

import com.kauanlima.mongospringsocial.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public UserDTO() {

    }

    public UserDTO(User obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
        senha = obj.getSenha();
    }
}
