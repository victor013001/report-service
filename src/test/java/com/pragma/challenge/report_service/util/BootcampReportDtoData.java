package com.pragma.challenge.report_service.util;

import com.pragma.challenge.report_service.infrastructure.entrypoints.dto.BootcampReportDto;
import java.time.LocalDate;

public class BootcampReportDtoData {
  private BootcampReportDtoData() throws InstantiationException {
    throw new InstantiationException("Data class cannot be instantiated");
  }

  public static BootcampReportDto getBootcampReportDto() {
    return new BootcampReportDto(
        2L, "Bootcamp Java", "Java Backend Bootcamp", LocalDate.of(2025, 1, 15), 12, 30, 10, 100);
  }
}
