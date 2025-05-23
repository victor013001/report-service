package com.pragma.challenge.report_service.infrastructure.entrypoints.dto;

import java.time.LocalDate;

public record BootcampReportDto(
    Long bootcampId,
    String name,
    String description,
    LocalDate launchDate,
    int durationInWeeks,
    int profileCount,
    int technologyCount,
    int userCount) {}
