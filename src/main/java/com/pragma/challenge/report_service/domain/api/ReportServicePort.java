package com.pragma.challenge.report_service.domain.api;

import com.pragma.challenge.report_service.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface ReportServicePort {
  Mono<BootcampReport> registerBootcampReport(BootcampReport bootcampReport);
}
