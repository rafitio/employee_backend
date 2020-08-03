/*
 * Copyright (c) 2020. 
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:27 PM
 *
 */

package com.astralife.employee.service.positions;

import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.astralife.employee.entity.Positions;
import io.reactivex.Single;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionsService {
    Single<PositionsResponse> listSingle(String id);

    Single<List<PositionsResponse>> lists(int size, int page, String sort, String order, Specification<Positions> specification);
}
