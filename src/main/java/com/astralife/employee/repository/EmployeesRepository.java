/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 11:02 PM
 *
 */

package com.astralife.employee.repository;

import com.astralife.employee.dto.ResponseApi;
import com.astralife.employee.entity.Employees;
import io.reactivex.Single;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface EmployeesRepository extends JpaRepository<Employees, String>, JpaSpecificationExecutor<Employees> {
    @Query("SELECT x FROM Employees x WHERE x.id = ?1")
    Employees findOneById(Integer id);

    @Query("SELECT COUNT(x) AS SEQ FROM Employees x")
    Single<ResponseEntity<ResponseApi<Integer>>> countSeq();
}
