package com.api.demo.dto;

import com.api.demo.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;
    private String name;
    private String responsibleName;
    private Long cityId;
    private String email;
    private Long segmentId;
    private Boolean status;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.responsibleName = company.getResponsibleName();
        this.cityId = company.getCity().getId();
        this.email = company.getEmail();
        this.segmentId = company.getSegment().getId();
        this.status = company.getStatus();
    }
}