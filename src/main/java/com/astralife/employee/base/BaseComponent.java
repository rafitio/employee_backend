/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:23 PM
 *
 */

package com.astralife.employee.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

//import javax.validation.Validator;

public abstract class BaseComponent {

    @Autowired
    protected Environment environment;

    /*@Autowired
    protected Validator validator;*/
}
