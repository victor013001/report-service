package com.pragma.challenge.report_service.infrastructure.entrypoints.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface ReportHandler {
  Mono<ServerResponse> createBootcampReport(ServerRequest request);
}
