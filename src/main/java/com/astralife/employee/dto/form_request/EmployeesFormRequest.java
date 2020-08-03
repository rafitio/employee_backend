/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 8/1/20, 9:25 AM
 *
 */

package com.astralife.employee.dto.form_request;

import com.astralife.employee.dto.response.divisions.DivisionsResponse;
import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesFormRequest implements Serializable {

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("divisions_id")
    private Integer divisionsId;

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("lastPosition")
    private String lastPosition;

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("nik")
    private String nik;

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("position_id")
    private Integer positionId;

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("type")
    private String type;

}
