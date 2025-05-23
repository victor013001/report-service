package com.pragma.challenge.report_service.domain.spi;

import com.pragma.challenge.report_service.domain.model.BootcampIdList;
import com.pragma.challenge.report_service.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface ReportPersistencePort {
  Mono<BootcampReport> registerBootcampReport(BootcampReport bootcampReport);

  Mono<Void> updateBootcampReportUserCount(BootcampIdList bootcampIdList);
}
