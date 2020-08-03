package com.astralife.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseCustomDTO implements Serializable {

    private String name;

    private String message = "OK";

    private Integer code = 200;

    private Object errors;

    private Object data;

    public ResponseCustomDTO(String name, Integer code, String message, Object data) {
        this.name = name;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseCustomDTO(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public ResponseCustomDTO(String name, Object data, HttpStatus httpStatus) {
        this.name = name;
        this.data = data;
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public static ResponseCustomDTO withErrors(String name, Integer code, String message, Object error) {
        com.astralife.employee.dto.ResponseCustomDTO response = new com.astralife.employee.dto.ResponseCustomDTO();
        response.setName(name);
        response.setCode(code);
        response.setMessage(message);
        response.setErrors(error);
        return response;
    }
}
