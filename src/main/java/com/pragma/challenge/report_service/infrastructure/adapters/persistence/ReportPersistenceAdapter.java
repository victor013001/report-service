package com.pragma.challenge.report_service.infrastructure.adapters.persistence;

import com.pragma.challenge.report_service.domain.model.BootcampIdList;
import com.pragma.challenge.report_service.domain.model.BootcampReport;
import com.pragma.challenge.report_service.domain.spi.ReportPersistencePort;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.entity.BootcampReportEntity;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.mapper.ReportBootcampEntityMapper;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.repository.ReportBootcampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportPersistencePort {
  private static final String LOG_PREFIX = "[REPORT_PERSISTENCE_ADAPTER] >>>";

  private final TransactionalOperator transactionalOperator;
  private final ReportBootcampRepository reportBootcampRepository;
  private final ReportBootcampEntityMapper reportBootcampEntityMapper;
  private final ReactiveMongoTemplate reactiveMongoTemplate;

  @Override
  public Mono<BootcampReport> registerBootcampReport(BootcampReport bootcampReport) {
    log.info(
        "{} Register report for bootcamp with id: {}.", LOG_PREFIX, bootcampReport.bootcampId());
    return reportBootcampRepository
        .save(reportBootcampEntityMapper.toEntity(bootcampReport))
        .map(reportBootcampEntityMapper::toModel);
  }

  @Override
  public Mono<Void> updateBootcampReportUserCount(BootcampIdList bootcampIdList) {
    return Flux.fromIterable(bootcampIdList.ids())
        .flatMap(
            bootcampId -> {
              log.info(
                  "{} Increasing user count for bootcamp with id: {}.", LOG_PREFIX, bootcampId);
              return reactiveMongoTemplate.updateFirst(
                  Query.query(Criteria.where("_id").is(bootcampId)),
                  new Update().inc("userCount", 1),
                  BootcampReportEntity.class);
            })
        .then();
  }
}
