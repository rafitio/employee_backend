/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:23 PM
 *
 */

package com.astralife.employee.base;

import com.astralife.employee.entity.*;
import com.astralife.employee.exception.ResourceNotFoundException;
import com.astralife.employee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public abstract class BaseService extends com.astralife.employee.base.BaseComponent {

    @Autowired
    private DivisionsRepository divisionsRepository;

    @Autowired
    private PositionsRepository positionsRepository;

    protected Divisions findDivisionsById(Integer id) {
        Optional<Divisions> optional = Optional.ofNullable(this.divisionsRepository.findOneById(id));

        if (!optional.isPresent()) throw new ResourceNotFoundException("Divisi tidak ditemukan");
        else return optional.get();
    }

    protected Positions findPositionsById(Integer id) {
        Optional<Positions> optional = Optional.ofNullable(this.positionsRepository.findOneById(id));

        if (!optional.isPresent()) throw new ResourceNotFoundException("Posisi tidak ditemukan");
        else return optional.get();
    }

}
