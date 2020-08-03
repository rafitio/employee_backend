/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:58 PM
 *
 */

package com.astralife.employee.entity;

import com.astralife.EntityTableName;
import com.astralife.employee.EntitySchemaName;
import com.astralife.employee.base.BaseModel;
import com.astralife.employee.util.constants.FieldLengthConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;


import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Audited
@Table(name = EntityTableName.EMPL_EMPLOYEES, schema = EntitySchemaName.EMPL)
@Where(clause = "deleted_date is null")
public class Employees extends BaseModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private Integer id;

    @Column(name = "divisions_id", insertable = false, updatable = false, length = FieldLengthConstant.ID)
    private Integer divisionsId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty(value = "divisions")
    @JoinColumn(name = "divisions_id", nullable = false)
    private Divisions divisions;

    @Column(name = "positions_id", insertable = false, updatable = false, length = FieldLengthConstant.ID)
    private Integer positionsId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty(value = "positions")
    @JoinColumn(name = "positions_id", nullable = false)
    private Positions positions;

    @JsonProperty(value = "name")
    @Column(name = "name", length = 10)
    private String name;

    @JsonProperty(value = "nik")
    @Column(name = "nik", length = 10)
    private String nik;

    @JsonProperty(value = "type")
    @Column(name = "type", length = 10)
    private String type;



}
