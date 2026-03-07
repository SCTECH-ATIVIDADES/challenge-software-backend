package com.api.demo.dto;

import com.api.demo.entity.Company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "Nome do responsável é obrigatório")
    private String responsibleName;
    @NotNull(message = "ID da cidade é obrigatório")
    private Long cityId;
    @Email(message = "Email deve ser válido")
    private String email;
    @NotNull(message = "ID do segmento é obrigatório")
    private Long segmentId;
    @NotNull(message = "Status é obrigatório")
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