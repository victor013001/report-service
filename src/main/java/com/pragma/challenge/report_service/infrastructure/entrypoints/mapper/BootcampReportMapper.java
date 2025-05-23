package com.pragma.challenge.report_service.infrastructure.entrypoints.mapper;

import com.pragma.challenge.report_service.domain.model.BootcampReport;
import com.pragma.challenge.report_service.infrastructure.entrypoints.dto.BootcampReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BootcampReportMapper {
  BootcampReport toBootcampReport(BootcampReportDto bootcampReportDto);
}
