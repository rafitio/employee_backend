/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 8/1/20, 9:25 AM
 *
 */

package com.astralife.employee.dto.request;

import com.astralife.employee.entity.Divisions;
import com.astralife.employee.entity.Positions;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesRequest implements Serializable {

    private Divisions divisions;

    private String lastPosition;

    private String name;

    private String nik;

    private Positions positions;

    private String type;

}
