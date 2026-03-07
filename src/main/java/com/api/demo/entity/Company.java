package com.api.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "responsible_name", nullable = false)
    @NotBlank
    private String responsibleName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @NotNull
    private City city;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(name = "segment_id", nullable = false)
    @NotNull
    private Segment segment;

    @Column(name = "status", nullable = false)
    @NotNull
    private Boolean status;
}