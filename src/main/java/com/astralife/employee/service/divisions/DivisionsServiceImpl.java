/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:16 PM
 *
 */

package com.astralife.employee.service.divisions;

import com.astralife.employee.base.BaseService;
import com.astralife.employee.dto.response.divisions.DivisionsResponse;
import com.astralife.employee.entity.Divisions;
import com.astralife.employee.exception.ResourceNotFoundException;
import com.astralife.employee.repository.DivisionsRepository;
import com.astralife.employee.service.divisions.helper.DivisionsHelper;
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
public class DivisionsServiceImpl extends BaseService implements DivisionsService {
    private final Logger logger = LoggerFactory.getLogger(DivisionsServiceImpl.class);

    @Autowired
    private DivisionsRepository divisionsRepository;

    @Getter
    private DivisionsResponse divisionsResponse;

    private final DivisionsHelper helper = DivisionsHelper.getInstance();

    private Divisions divisions;

    @Override
    public Single<DivisionsResponse> listSingle(String id) {
        return Single.create(singleSubscriber -> {
            Optional<Divisions> optional = this.divisionsRepository.findById(id);

            if (!optional.isPresent()) singleSubscriber.onError(new ResourceNotFoundException("Divisions id " + id + " tidak ditemukan"));
            else {
                this.divisions = optional.get();
                singleSubscriber.onSuccess(helper.convertEntityToResponse(this.divisions));
            }
        });
    }

    @Override
    public Single<List<DivisionsResponse>> lists(int size, int page, String sort, String order, Specification<Divisions> specification) {
        return this.findLists(size, page, sort, order, specification).map(this::convertEntityToResponseList);
    }

    private Single<List<Divisions>> findLists(int size, int page, String sort, String order, Specification<Divisions> specification) {
        return Single.create(singleSubscriber -> {
            List<Divisions> dataList;
            PageRequest    pageRequest;
            if (null != specification) dataList = this.divisionsRepository.findAll(specification);
            else {
                pageRequest = (order.equalsIgnoreCase("desc")) ? PageRequest.of(page, size, Sort.by(sort).descending()) : PageRequest.of(page, size, Sort.by(sort).ascending());
                dataList    = this.divisionsRepository.findAll(pageRequest).toList();
            }
            singleSubscriber.onSuccess(dataList);
        });
    }

    private List<DivisionsResponse> convertEntityToResponseList(List<Divisions> lists) {
        return lists.stream()
                .map(helper::convertEntityToResponse)
                .collect(Collectors.toList());
    }
}
