package com.example.school_mgnt_sb.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class StatusException extends RuntimeException{
    public HttpStatus status;
    public StatusException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }
}
