package com.api.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.demo.entity.Segment;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {

    Optional<Segment> findByName(String name);
}