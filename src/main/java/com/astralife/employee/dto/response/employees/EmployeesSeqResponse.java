/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:24 PM
 *
 */

package com.astralife.employee.dto.response.employees;

import com.astralife.employee.dto.response.divisions.DivisionsResponse;
import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesSeqResponse {
    @JsonProperty(value = "sequence")
    private Integer sequence;

}
