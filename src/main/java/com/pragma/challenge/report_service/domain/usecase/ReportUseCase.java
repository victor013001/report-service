package com.pragma.challenge.report_service.domain.usecase;

import com.pragma.challenge.report_service.domain.api.ReportServicePort;
import com.pragma.challenge.report_service.domain.model.BootcampIdList;
import com.pragma.challenge.report_service.domain.model.BootcampReport;
import com.pragma.challenge.report_service.domain.spi.ReportPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReportUseCase implements ReportServicePort {

  private final ReportPersistencePort reportPersistencePort;

  @Override
  public Mono<BootcampReport> registerBootcampReport(BootcampReport bootcampReport) {
    return reportPersistencePort.registerBootcampReport(bootcampReport);
  }

  @Override
  public Mono<Void> updateUserCount(BootcampIdList bootcampIdList) {
    return reportPersistencePort.updateBootcampReportUserCount(bootcampIdList);
  }
}
