package com.astralife.employee.service.positions.helper;


import com.astralife.employee.dto.response.positions.PositionsResponse;
import com.astralife.employee.entity.Positions;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

public class ConvertEntityToResponse {

    @Getter
    private PositionsResponse mainResponse;

    private Positions mainEntity;

    public PositionsResponse convert(Positions entity) {
        return convertEntityToPositionsResponse(entity).getMainResponse();
    }

    private ConvertEntityToResponse convertEntityToPositionsResponse(Positions entity) {
        this.mainEntity   = entity;
        this.mainResponse = new PositionsResponse();
        if (null != this.mainEntity) BeanUtils.copyProperties(this.mainEntity, this.mainResponse);
        return this;
    }
}
