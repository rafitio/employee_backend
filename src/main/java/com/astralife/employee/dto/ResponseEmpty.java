package com.astralife.employee.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseEmpty<T> {
    public static ResponseEmpty successNoData() {
        return ResponseEmpty.builder().build();
    }
}