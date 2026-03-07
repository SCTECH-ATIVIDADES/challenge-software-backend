package com.api.demo.dto;

import com.api.demo.entity.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;
    private Integer code;
    private String name;

    public CityDTO(City city) {
        this.id = city.getId();
        this.code = city.getCode();
        this.name = city.getName();
    }
}