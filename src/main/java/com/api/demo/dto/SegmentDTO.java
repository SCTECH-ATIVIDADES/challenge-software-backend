package com.api.demo.dto;

import com.api.demo.entity.Segment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentDTO {

    private Long id;
    private String name;

    public SegmentDTO(Segment segment) {
        this.id = segment.getId();
        this.name = segment.getName();
    }
}