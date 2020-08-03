package com.astralife.employee.dto;


import lombok.Getter;
import lombok.Setter;

public class ErrorDetail {

    @Getter @Setter
    private String title;

    @Getter @Setter
    private int status;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private long timeStamp;

    @Getter @Setter
    private String detail;
}
