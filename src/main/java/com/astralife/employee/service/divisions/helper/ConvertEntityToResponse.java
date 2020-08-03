package com.astralife.employee.service.divisions.helper;


import com.astralife.employee.dto.response.divisions.DivisionsResponse;
import com.astralife.employee.entity.Divisions;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

public class ConvertEntityToResponse {

    @Getter
    private DivisionsResponse mainResponse;

    private Divisions mainEntity;

    public DivisionsResponse convert(Divisions entity) {
        return convertEntityToDivisionsResponse(entity).getMainResponse();
    }

    private ConvertEntityToResponse convertEntityToDivisionsResponse(Divisions entity) {
        this.mainEntity   = entity;
        this.mainResponse = new DivisionsResponse();
        if (null != this.mainEntity) BeanUtils.copyProperties(this.mainEntity, this.mainResponse);
        return this;
    }
}
