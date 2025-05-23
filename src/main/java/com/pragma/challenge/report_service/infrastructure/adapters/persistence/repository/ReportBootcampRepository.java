package com.pragma.challenge.report_service.infrastructure.adapters.persistence.repository;

import com.pragma.challenge.report_service.infrastructure.adapters.persistence.entity.BootcampReportEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportBootcampRepository
    extends ReactiveMongoRepository<BootcampReportEntity, Long> {}
