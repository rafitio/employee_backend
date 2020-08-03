package com.astralife.employee.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseApi<T> {
    private String  name;
    private String  message;
    private Integer code;
    private T       errors;
    private T       data;

    public static com.astralife.employee.dto.ResponseApi successNoData() {
        return com.astralife.employee.dto.ResponseApi.builder().build();
    }

    public static <T> com.astralife.employee.dto.ResponseApi<T> successWithData(T data) {
        return com.astralife.employee.dto.ResponseApi.<T>builder().data(data).build();
    }

    public static <T> com.astralife.employee.dto.ResponseApi<T> success(String name, String message, Integer code, T errors, T data) {
        return com.astralife.employee.dto.ResponseApi.<T>builder().name(name).message(message).code(code).errors(errors).data(data).build();
    }

    public static <T> com.astralife.employee.dto.ResponseApi error(T errors) {
        return com.astralife.employee.dto.ResponseApi.builder().errors(errors).build();
    }

    public static <T> com.astralife.employee.dto.ResponseApi ok(String name, T data) {
        return com.astralife.employee.dto.ResponseApi.builder().name(name).message(HttpStatus.OK.getReasonPhrase()).code(HttpStatus.OK.value()).data(data).build();
    }

    public static <T> com.astralife.employee.dto.ResponseApi created(String name, T data) {
        return com.astralife.employee.dto.ResponseApi.builder().name(name).message(HttpStatus.CREATED.getReasonPhrase()).code(HttpStatus.CREATED.value()).data(data).build();
    }
}
