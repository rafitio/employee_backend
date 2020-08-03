/*
 * Copyright (c) 2020. 
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:27 PM
 *
 */

package com.astralife.employee.service.employees;

import com.astralife.employee.dto.response.employees.EmployeesResponse;
import com.astralife.employee.dto.form_request.EmployeesFormRequest;
import com.astralife.employee.entity.Employees;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeesService {
    Single<EmployeesResponse> create(EmployeesFormRequest formRequest);

    Single<EmployeesResponse> update(Integer id, EmployeesFormRequest formRequest);

    Single<EmployeesResponse> delete(Integer id);

    Single<EmployeesResponse> listSingle(Integer id);

    Single<List<EmployeesResponse>> lists(int size, int page, String sort, String order, Specification<Employees> specification);

    //Single<Integer> countSeq();

}
