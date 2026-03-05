package com.api.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.demo.dto.CompanyDTO;
import com.api.demo.entity.City;
import com.api.demo.entity.Company;
import com.api.demo.entity.Segment;
import com.api.demo.repository.CityRepository;
import com.api.demo.repository.CompanyRepository;
import com.api.demo.repository.SegmentRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CityRepository cityRepository;
    private final SegmentRepository segmentRepository;

    public CompanyService(CompanyRepository companyRepository, CityRepository cityRepository, SegmentRepository segmentRepository) {
        this.companyRepository = companyRepository;
        this.cityRepository = cityRepository;
        this.segmentRepository = segmentRepository;
    }

    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream().map(CompanyDTO::new).toList();
    }

    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id).map(CompanyDTO::new);
    }

    public CompanyDTO save(CompanyDTO companyDTO) {
        City city = cityRepository.findById(companyDTO.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        Segment segment = segmentRepository.findById(companyDTO.getSegmentId())
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        company.setResponsibleName(companyDTO.getResponsibleName());
        company.setCity(city);
        company.setEmail(companyDTO.getEmail());
        company.setSegment(segment);
        company.setStatus(companyDTO.getStatus());
        Company saved = companyRepository.save(company);
        return new CompanyDTO(saved);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}