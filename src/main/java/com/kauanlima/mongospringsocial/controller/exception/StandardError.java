package com.kauanlima.mongospringsocial.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;


}
