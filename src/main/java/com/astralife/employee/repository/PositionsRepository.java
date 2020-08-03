/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 11:02 PM
 *
 */

package com.astralife.employee.repository;

import com.astralife.employee.entity.Divisions;
import com.astralife.employee.entity.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PositionsRepository extends JpaRepository<Positions, String>, JpaSpecificationExecutor<Positions> {
    @Query("SELECT x FROM Positions x WHERE x.id = ?1")
    Positions findOneById(Integer id);
}
