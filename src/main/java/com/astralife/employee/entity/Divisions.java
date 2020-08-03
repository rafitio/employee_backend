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
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@ToString
@NoArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = EntityTableName.EMPL_DIVISIONS, schema = EntitySchemaName.EMPL)
@Where(clause = "deleted_date is null")
public class Divisions extends BaseModel {
    @Id
    @Column(name = "id")
    @NotNull
    private Integer id;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, length = 15)
    private String name;

}
