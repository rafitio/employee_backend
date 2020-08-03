/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 8/1/20, 9:12 AM
 *
 */

package com.astralife.employee.controller;

import com.astralife.employee.base.BaseController;
import com.astralife.employee.dto.ResponseApi;
import com.astralife.employee.dto.ResponseEmpty;
import com.astralife.employee.dto.form_request.EmployeesFormRequest;
import com.astralife.employee.dto.response.employees.EmployeesResponse;
import com.astralife.employee.entity.Employees;
import com.astralife.employee.repository.EmployeesRepository;
import com.astralife.employee.service.divisions.DivisionsServiceImpl;
import com.astralife.employee.service.employees.EmployeesService;
import com.astralife.employee.util.profiler.task.Task;
import com.astralife.employee.util.profiler.when.When;
import io.reactivex.Observable;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/main/employees")
public class EmployeesController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(DivisionsServiceImpl.class);

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesRepository employeesRepository;

    private static final String TIPE = "employees";

    private final Task task = Task.getInstance();

    private final When when = When.getInstance();

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseApi<EmployeesResponse>>> listSingle(@PathVariable(value = "id") String id, HttpServletResponse httpServletResponse) {
        Profiler profiler = task.start(TIPE, "GET /" + TIPE + "/{id}");
        return this.employeesService.listSingle(Integer.parseInt(id))
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("GET /" + TIPE + "/{id} Completed", profiler, logger, null))
                .map(s -> ResponseEntity.ok(
                        ResponseApi.success(TIPE, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), null, s))
                );
    }

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseApi<List<EmployeesResponse>>>> index(
            @RequestParam(value = "size", defaultValue = "15") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "createdDate") String sort,
            @RequestParam(value = "order", defaultValue = "DESC") String order,
            HttpServletResponse httpServletResponse
    ) {
        Profiler profiler = task.start(TIPE, "GET /" + TIPE);
        return this.employeesService.lists(size, page, sort, order, null)
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("GET /" + TIPE + " Completed", profiler, logger, null))
                .map(s -> ResponseEntity.ok(
                        ResponseApi.success(TIPE, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), null, s))
                );
    }

    @CrossOrigin
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseApi>> create(@Valid @RequestBody EmployeesFormRequest formRequest, HttpServletResponse httpServletResponse) {
        Profiler profiler = task.start(TIPE, "POST /" + TIPE);
        return this.employeesService.create(formRequest)
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("POST /" + TIPE + " Completed", profiler, logger, null))
                .map(s -> ResponseEntity
                        .created(URI.create("/main/" + TIPE + "/" + s.getId()))
                        .body(ResponseApi.created(TIPE, s))
                );
    }

    @CrossOrigin
    @Transactional
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<ResponseApi>> update(@PathVariable(value = "id") String id, @Valid @RequestBody EmployeesFormRequest formRequest, HttpServletResponse httpServletResponse) {
        Profiler profiler = task.start(TIPE, "PATCH /" + TIPE + "/{id}");
        return this.employeesService.update(Integer.parseInt(id), formRequest)
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("PATCH /" + TIPE + "/{id} Completed", profiler, logger, null))
                .map(s -> ResponseEntity.ok(ResponseApi.ok(TIPE, s)));
    }

    @CrossOrigin
    @Transactional
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public Single<ResponseEntity<ResponseApi>> delete(@PathVariable(value = "id") String id) {
        Profiler profiler = task.start(TIPE, "DELETE /employee/{id}");
        return this.employeesService.delete(Integer.valueOf(id))
                .subscribeOn(Schedulers.io())
                .doOnError(t -> when.fail(null, profiler, logger, t))
                .doOnSuccess(s -> when.fine("PATCH /" + TIPE + "/{id} Completed", profiler, logger, null))
                .map(s -> ResponseEntity.ok(ResponseApi.ok(TIPE, s)));
    }

    @CrossOrigin
    @GetMapping(value = "/sequence", produces = MediaType.APPLICATION_JSON_VALUE)
    public String countSequence(
            HttpServletResponse httpServletResponse
    ) {
        List<Employees> employees = this.employeesRepository.findAll();

        String seq = String.format("%04d", employees.size()+1);
        //myStrings.subscribe(System.out::println);

        return seq;
    }

}
