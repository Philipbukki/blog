package com.springboot.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogErrorException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogErrorException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogErrorException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
