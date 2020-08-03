package com.astralife.employee.service.divisions.helper;


import com.astralife.employee.dto.response.divisions.DivisionsResponse;
import com.astralife.employee.entity.Divisions;

public class DivisionsHelper {
    private static DivisionsHelper instance = new DivisionsHelper();

    private ConvertEntityToResponse toResponse;

    public DivisionsHelper() {
        toResponse = new ConvertEntityToResponse();
    }

    public DivisionsResponse convertEntityToResponse(Divisions entity) {
        return toResponse.convert(entity);
    }

    public static DivisionsHelper getInstance() {
        return instance;
    }
}
