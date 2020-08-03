/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/30/20, 9:50 PM
 *
 */

package com.astralife.employee.controller;

import com.astralife.employee.base.BaseController;
import com.astralife.employee.dto.ResponseApi;
import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.astralife.employee.service.positions.PositionsService;
import com.astralife.employee.service.positions.PositionsServiceImpl;
import com.astralife.employee.util.profiler.task.Task;
import com.astralife.employee.util.profiler.when.When;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/main/positions")
public class PositionsController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(PositionsServiceImpl.class);

    @Autowired
    private PositionsService positionsService;

    private static final String TIPE = "positions";

    private final Task task = Task.getInstance();

    private final When when = When.getInstance();

    @CrossOrigin
    @Transactional
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseApi<PositionsResponse>>> listSingle(@PathVariable(value = "id") String id, HttpServletResponse httpServletResponse) {
        Profiler profiler = task.start(TIPE, "GET /positions/{id}");
        return this.positionsService.listSingle(id)
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("GET /positions/{id} completed", profiler, logger, null))
                .map(s -> ResponseEntity.ok(
                        ResponseApi.success(TIPE, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), null, s))
                );
    }

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseApi<List<PositionsResponse>>>> index(
            @RequestParam(value = "size", defaultValue = "15") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "createdDate") String sort,
            @RequestParam(value = "order", defaultValue = "DESC") String order
    ) {
        Profiler profiler = task.start(TIPE, "GET /" + TIPE);
        return this.positionsService.lists(size, page, sort, order, null)
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("GET /" + TIPE + " Completed", profiler, logger, null))
                .map(s -> ResponseEntity.ok(
                        ResponseApi.success(TIPE, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), null, s))
                );
    }
}
