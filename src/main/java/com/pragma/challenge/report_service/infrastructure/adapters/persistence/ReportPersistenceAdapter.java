package com.pragma.challenge.report_service.infrastructure.adapters.persistence;

import com.pragma.challenge.report_service.domain.model.BootcampReport;
import com.pragma.challenge.report_service.domain.spi.ReportPersistencePort;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.mapper.ReportBootcampEntityMapper;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.repository.ReportBootcampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportPersistencePort {
  private static final String LOG_PREFIX = "[REPORT_PERSISTENCE_ADAPTER] >>>";

  private final TransactionalOperator transactionalOperator;
  private final ReportBootcampRepository reportBootcampRepository;
  private final ReportBootcampEntityMapper reportBootcampEntityMapper;

  @Override
  public Mono<BootcampReport> registerBootcampReport(BootcampReport bootcampReport) {
    log.info(
        "{} Register report for bootcamp with id: {}.", LOG_PREFIX, bootcampReport.bootcampId());
    return reportBootcampRepository
        .save(reportBootcampEntityMapper.toEntity(bootcampReport))
        .map(reportBootcampEntityMapper::toModel)
        .as(transactionalOperator::transactional);
  }
}
