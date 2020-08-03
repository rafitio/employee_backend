/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:23 PM
 *
 */

package com.astralife.employee.base;

import com.astralife.employee.util.constants.FieldLengthConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @JsonIgnore
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @JsonIgnore
    @Column(name = "updated_date", insertable = false)
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @JsonIgnore
    @Column(name = "deleted_date", insertable = false)
    private Date deletedDate;

    @JsonIgnore
    @Column(name = "status")
    private Boolean status;

    /*@JsonIgnore
    private transient String uuid;*/


    @PrePersist
    protected void onCreated() {
        //this.initializeUuid();
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdated() {
        this.updatedDate = new Date();
    }

    /*private void initializeUuid() {
        id = Optional.ofNullable(uuid).orElseGet(() -> UUID.randomUUID().toString());
    }*/
}
