package com.astralife.employee.service.positions.helper;


import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.astralife.employee.entity.Positions;

public class PositionsHelper {
    private static PositionsHelper instance = new PositionsHelper();

    private ConvertEntityToResponse toResponse;

    public PositionsHelper() {
        toResponse = new ConvertEntityToResponse();
    }

    public PositionsResponse convertEntityToResponse(Positions entity) {
        return toResponse.convert(entity);
    }

    public static PositionsHelper getInstance() {
        return instance;
    }
}
