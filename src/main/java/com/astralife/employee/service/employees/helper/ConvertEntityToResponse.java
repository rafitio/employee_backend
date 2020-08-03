package com.astralife.employee.service.employees.helper;


import com.astralife.employee.dto.response.employees.EmployeesResponse;
import com.astralife.employee.entity.Employees;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

public class ConvertEntityToResponse {

    @Getter
    private EmployeesResponse mainResponse;

    private Employees mainEntity;

    public EmployeesResponse convert(Employees entity) {
        return convertEntityToEmployeesResponse(entity).getMainResponse();
    }

    private ConvertEntityToResponse convertEntityToEmployeesResponse(Employees entity) {
        this.mainEntity   = entity;
        this.mainResponse = new EmployeesResponse();
        if (null != this.mainEntity) BeanUtils.copyProperties(this.mainEntity, this.mainResponse);
        return this;
    }
}
