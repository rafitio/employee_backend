package com.astralife.employee.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationErrorDetail {

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

    @Getter @Setter
    private Map<String, List<ValidationError>> errors = new HashMap<String, List<ValidationError>>();
}
