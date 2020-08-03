/*
 * Copyright (c) 2020. 
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:59 PM
 *
 */

package com.astralife.employee.service.positions;

import com.astralife.employee.base.BaseService;
import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.astralife.employee.entity.Positions;
import com.astralife.employee.exception.ResourceNotFoundException;
import com.astralife.employee.repository.PositionsRepository;
import com.astralife.employee.service.positions.helper.PositionsHelper;
import io.reactivex.Single;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionsServiceImpl extends BaseService implements PositionsService {
    private final Logger logger = LoggerFactory.getLogger(PositionsServiceImpl.class);

    @Autowired
    private PositionsRepository positionsRepository;

    @Getter
    private PositionsResponse positionsResponse;

    private final PositionsHelper helper = PositionsHelper.getInstance();

    private Positions positions;

    @Override
    public Single<PositionsResponse> listSingle(String id) {
        return Single.create(singleSubscriber -> {
            Optional<Positions> optional = this.positionsRepository.findById(id);

            if (!optional.isPresent()) singleSubscriber.onError(new ResourceNotFoundException("Positions id " + id + " tidak ditemukan"));
            else {
                this.positions = optional.get();
                singleSubscriber.onSuccess(helper.convertEntityToResponse(this.positions));
            }
        });
    }

    @Override
    public Single<List<PositionsResponse>> lists(int size, int page, String sort, String order, Specification<Positions> specification) {
        return this.findLists(size, page, sort, order, specification).map(this::convertEntityToResponseList);
    }

    private Single<List<Positions>> findLists(int size, int page, String sort, String order, Specification<Positions> specification) {
        return Single.create(singleSubscriber -> {
            List<Positions> dataList;
            PageRequest    pageRequest;
            if (null != specification) dataList = this.positionsRepository.findAll(specification);
            else {
                pageRequest = (order.equalsIgnoreCase("desc")) ? PageRequest.of(page, size, Sort.by(sort).descending()) : PageRequest.of(page, size, Sort.by(sort).ascending());
                dataList    = this.positionsRepository.findAll(pageRequest).toList();
            }
            singleSubscriber.onSuccess(dataList);
        });
    }

    private List<PositionsResponse> convertEntityToResponseList(List<Positions> lists) {
        return lists.stream()
                .map(helper::convertEntityToResponse)
                .collect(Collectors.toList());
    }
}
