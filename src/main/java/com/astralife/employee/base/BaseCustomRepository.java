/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:23 PM
 *
 */

package com.astralife.employee.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseCustomRepository extends com.astralife.employee.base.BaseComponent {

    @PersistenceContext
    protected EntityManager entityManager;

}
