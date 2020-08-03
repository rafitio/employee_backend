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
public class EmployeesResponse {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "divisionsId")
    private Integer divisionsId;

    @JsonProperty(value = "division")
    private DivisionsResponse divisions;

    @JsonProperty(value = "lastPosition")
    private String lastPosition;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "nik")
    private String nik;

    @JsonProperty(value = "positionsId")
    private Integer positionsId;

    @JsonProperty(value = "position")
    private PositionsResponse position;

    @JsonProperty(value = "type")
    private String type;


}
