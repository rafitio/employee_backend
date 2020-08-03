package com.astralife.employee.service.employees.helper;


import com.astralife.employee.dto.response.employees.EmployeesResponse;
import com.astralife.employee.entity.Employees;
import com.astralife.employee.service.employees.helper.ConvertEntityToResponse;

public class EmployeesHelper {
    private static EmployeesHelper instance = new EmployeesHelper();

    private ConvertEntityToResponse toResponse;

    public EmployeesHelper() {
        toResponse = new ConvertEntityToResponse();
    }

    public EmployeesResponse convertEntityToResponse(Employees entity) {
        return toResponse.convert(entity);
    }

    public static EmployeesHelper getInstance() {
        return instance;
    }
}
