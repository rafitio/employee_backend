/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:24 PM
 *
 */

package com.astralife.employee.dto.response.positions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PositionsResponse {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "level")
    private String level;

    @JsonProperty(value = "name")
    private String name;
}
