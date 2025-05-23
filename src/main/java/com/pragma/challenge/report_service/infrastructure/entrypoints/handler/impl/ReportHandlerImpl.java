package com.pragma.challenge.report_service.infrastructure.entrypoints.handler.impl;

import static com.pragma.challenge.report_service.infrastructure.entrypoints.util.ResponseUtil.buildResponse;
import static com.pragma.challenge.report_service.infrastructure.entrypoints.util.ResponseUtil.buildStandardError;

import com.pragma.challenge.report_service.domain.api.ReportServicePort;
import com.pragma.challenge.report_service.domain.enums.ServerResponses;
import com.pragma.challenge.report_service.domain.exceptions.StandardException;
import com.pragma.challenge.report_service.domain.exceptions.standard_exception.BadRequest;
import com.pragma.challenge.report_service.infrastructure.entrypoints.dto.BootcampReportDto;
import com.pragma.challenge.report_service.infrastructure.entrypoints.handler.ReportHandler;
import com.pragma.challenge.report_service.infrastructure.entrypoints.mapper.BootcampReportMapper;
import com.pragma.challenge.report_service.infrastructure.entrypoints.mapper.ServerResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportHandlerImpl implements ReportHandler {
  private static final String LOG_PREFIX = "[REPORT_HANDLER] >>> ";

  private final ServerResponseMapper serverResponseMapper;
  private final BootcampReportMapper bootcampReportMapper;
  private final ReportServicePort reportServicePort;

  @Override
  public Mono<ServerResponse> createBootcampReport(ServerRequest request) {
    return request
        .bodyToMono(BootcampReportDto.class)
        .switchIfEmpty(Mono.error(BadRequest::new))
        .flatMap(
            bootcampReportDto -> {
              log.info(
                  "{} Register report for bootcamp with id: {}.",
                  LOG_PREFIX,
                  bootcampReportDto.bootcampId());
              return reportServicePort.registerBootcampReport(
                  bootcampReportMapper.toBootcampReport(bootcampReportDto));
            })
        .flatMap(
            ignore ->
                buildResponse(
                    ServerResponses.SUCCESS_REGISTER_BOOTCAMP_REPORT.getHttpStatus(),
                    ServerResponses.SUCCESS_REGISTER_BOOTCAMP_REPORT.getMessage(),
                    null,
                    serverResponseMapper))
        .doOnError(
            ex ->
                log.error(
                    "{} Exception {} caught. Caused by: {}",
                    LOG_PREFIX,
                    ex.getClass().getSimpleName(),
                    ex.getMessage()))
        .onErrorResume(
            StandardException.class,
            ex ->
                buildResponse(
                    ex.getHttpStatus(), null, ex.getStandardError(), serverResponseMapper))
        .onErrorResume(
            ex ->
                buildResponse(
                    ServerResponses.SERVER_ERROR.getHttpStatus(),
                    null,
                    buildStandardError(ServerResponses.SERVER_ERROR),
                    serverResponseMapper));
  }
}
