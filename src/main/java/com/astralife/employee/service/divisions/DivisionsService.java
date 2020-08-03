/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:15 PM
 *
 */

package com.astralife.employee.service.divisions;

import com.astralife.employee.dto.response.divisions.DivisionsResponse;
import com.astralife.employee.entity.Divisions;
import io.reactivex.Single;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DivisionsService {
    Single<DivisionsResponse> listSingle(String id);

    Single<List<DivisionsResponse>> lists(int size, int page, String sort, String order, Specification<Divisions> specification);
}
