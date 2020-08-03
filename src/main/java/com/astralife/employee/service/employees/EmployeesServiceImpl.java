/*
 * Copyright (c) 2020. 
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 10:59 PM
 *
 */

package com.astralife.employee.service.employees;

import com.astralife.employee.base.BaseService;
import com.astralife.employee.dto.form_request.EmployeesFormRequest;
import com.astralife.employee.dto.request.EmployeesRequest;
import com.astralife.employee.dto.response.employees.EmployeesResponse;
import com.astralife.employee.dto.response.employees.EmployeesSeqResponse;
import com.astralife.employee.entity.Employees;
import com.astralife.employee.exception.ResourceNotFoundException;
import com.astralife.employee.repository.EmployeesRepository;
import com.astralife.employee.service.employees.helper.EmployeesHelper;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceImpl extends BaseService implements EmployeesService {
    private final Logger logger = LoggerFactory.getLogger(EmployeesServiceImpl.class);

    @Autowired
    private EmployeesRepository employeesRepository;

    @Getter
    private EmployeesResponse employeesResponse;

    private final EmployeesHelper helper = EmployeesHelper.getInstance();

    private Employees employees;

    /**
     * create
     * @param formRequest
     * @return
     */
    @Override
    public Single<EmployeesResponse> create(EmployeesFormRequest formRequest) {
        return this.createEmployees(this.convertCreateFormRequestToRequest(formRequest));
    }

    private Single<EmployeesResponse> createEmployees(EmployeesRequest request) {
        return Single.create(singleSubscriber -> {
            this.employees = this.employeesRepository.save(this.convertRequestToEntity(request));
            singleSubscriber.onSuccess(helper.convertEntityToResponse(this.employees));
        });
    }

    private EmployeesRequest convertCreateFormRequestToRequest(EmployeesFormRequest formRequest) {
        EmployeesRequest request = new EmployeesRequest();
        BeanUtils.copyProperties(formRequest, request);
        request.setDivisions(this.findDivisionsById(formRequest.getDivisionsId()));
        request.setPositions(this.findPositionsById(formRequest.getPositionId()));

        return request;
    }

    private Employees convertRequestToEntity(EmployeesRequest request) {
        Employees mainEntity = new Employees();
        BeanUtils.copyProperties(request, mainEntity);
        return mainEntity;
    }


    /**
     * update
     * @param id
     * @param formRequest
     * @return
     */
    @Override
    public Single<EmployeesResponse> update(Integer id, EmployeesFormRequest formRequest) {
        return this.doUpdate(id, this.convertUpdateFormRequestToRequest(formRequest));
    }

    private Single<EmployeesResponse> doUpdate(Integer id, EmployeesRequest request) {
        return Single.create(singleSubscriber -> {
            Optional<Employees> optional1 = Optional.ofNullable(this.employeesRepository.findOneById(id));

            if (!optional1.isPresent()) {
                singleSubscriber.onError(new ResourceNotFoundException());
            } else {
                Employees data = optional1.get();
                data.setName(request.getName());
                data.setNik(request.getNik());
                data.setDivisions(request.getDivisions());
                data.setPositions(request.getPositions());

                this.employees = this.employeesRepository.save(data);
                singleSubscriber.onSuccess(helper.convertEntityToResponse(this.employees));
            }
        });
    }

    private EmployeesRequest convertUpdateFormRequestToRequest(EmployeesFormRequest formRequest) {
        EmployeesRequest request = new EmployeesRequest();
        BeanUtils.copyProperties(formRequest, request);
        request.setDivisions(this.findDivisionsById(formRequest.getDivisionsId()));
        request.setPositions(this.findPositionsById(formRequest.getPositionId()));

        return request;
    }

    /**
     * delete
     * @param id
     * @return
     */
    @Override
    public Single<EmployeesResponse> delete(Integer id) {
        return this.doDelete(id);
    }

    private Single<EmployeesResponse> doDelete(Integer id) {
        return Single.create(completableSubscriber -> {
            Optional<Employees> optional = Optional.ofNullable(this.employeesRepository.findOneById(id));

            if (!optional.isPresent()) {
                completableSubscriber.onError(new ResourceNotFoundException());
            } else {
                Employees data = optional.get();
                data.setDeletedDate(new Date());
                this.employees = this.employeesRepository.save(data);
                completableSubscriber.onSuccess(helper.convertEntityToResponse(this.employees));
            }
        });
    }

    @Override
    public Single<EmployeesResponse> listSingle(Integer id) {
        return Single.create(singleSubscriber -> {
            Optional<Employees> optional = Optional.ofNullable(this.employeesRepository.findOneById(id));

            if (!optional.isPresent()) singleSubscriber.onError(new ResourceNotFoundException("Employees id " + id + " tidak ditemukan"));
            else {
                this.employees = optional.get();
                singleSubscriber.onSuccess(helper.convertEntityToResponse(this.employees));
            }
        });
    }

    @Override
    public Single<List<EmployeesResponse>> lists(int size, int page, String sort, String order, Specification<Employees> specification) {
        return this.findLists(size, page, sort, order, specification).map(this::convertEntityToResponseList);
    }

    /*@Override
    public Single<Integer> countSeq() {

        return Single.create(singleSubscriber -> {
            Optional<Employees> optional = Optional.ofNullable(this.employeesRepository.countSeq());

            singleSubscriber.onSuccess(this.convertSeqToResponse(optional.get()));
        });
    }*/

    /*private Integer convertSeqToResponse(Employees entity) {
        EmployeesSeqResponse employeesSeqResponse = new EmployeesSeqResponse();

        if (null != entity){
            return BeanUtils.copyProperties(entity, employeesSeqResponse);
        }else{
    }*/

    private Single<List<Employees>> findLists(int size, int page, String sort, String order, Specification<Employees> specification) {
        return Single.create(singleSubscriber -> {
            List<Employees> dataList;
            PageRequest    pageRequest;
            if (null != specification) dataList = this.employeesRepository.findAll(specification);
            else {
                pageRequest = (order.equalsIgnoreCase("desc")) ? PageRequest.of(page, size, Sort.by(sort).descending()) : PageRequest.of(page, size, Sort.by(sort).ascending());
                dataList    = this.employeesRepository.findAll(pageRequest).toList();
            }
            singleSubscriber.onSuccess(dataList);
        });
    }

    private List<EmployeesResponse> convertEntityToResponseList(List<Employees> lists) {
        return lists.stream()
                .map(helper::convertEntityToResponse)
                .collect(Collectors.toList());
    }
}
