package com.kauanlima.mongospringsocial.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String id;
    private String name;
    private String email;

    public UserResponseDTO(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


}
