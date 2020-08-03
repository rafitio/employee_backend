/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:24 PM
 *
 */

package com.astralife.employee.dto.response.divisions;

import com.astralife.employee.util.constants.FieldLengthConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DivisionsResponse {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private String name;
}
