package com.pragma.challenge.report_service.application.config;

import com.pragma.challenge.report_service.domain.spi.ReportPersistencePort;
import com.pragma.challenge.report_service.domain.usecase.ReportUseCase;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.ReportPersistenceAdapter;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.mapper.ReportBootcampEntityMapper;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.repository.ReportBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
@RequiredArgsConstructor
public class ReportUseCasesConfig {
  private final ReportBootcampRepository reportBootcampRepository;
  private final ReportBootcampEntityMapper reportBootcampEntityMapper;
  private final ReactiveMongoTemplate reactiveMongoTemplate;

  @Bean
  public ReportUseCase reportUseCase(ReportPersistencePort reportPersistencePort) {
    return new ReportUseCase(reportPersistencePort);
  }

  @Bean
  public ReportPersistencePort reportPersistencePort(TransactionalOperator transactionalOperator) {
    return new ReportPersistenceAdapter(
        transactionalOperator,
        reportBootcampRepository,
        reportBootcampEntityMapper,
        reactiveMongoTemplate);
  }

  @Bean
  public ReactiveTransactionManager transactionManager(ReactiveMongoDatabaseFactory factory) {
    return new ReactiveMongoTransactionManager(factory);
  }

  @Bean
  public TransactionalOperator transactionalOperator(
      ReactiveTransactionManager reactiveTransactionManager) {
    return TransactionalOperator.create(reactiveTransactionManager);
  }
}
