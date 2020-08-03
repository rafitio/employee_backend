/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:23 PM
 *
 */

package com.astralife.employee.base;

import com.astralife.employee.repository.DivisionsRepository;
import com.astralife.employee.repository.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class BaseTableSeeder {

    @Autowired
    protected Environment environment;

    @Autowired
    protected DivisionsRepository divisionsRepository;

    @Autowired
    protected PositionsRepository positionsRepository;



}
