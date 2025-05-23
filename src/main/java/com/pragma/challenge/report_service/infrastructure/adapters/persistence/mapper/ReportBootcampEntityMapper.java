package com.pragma.challenge.report_service.infrastructure.adapters.persistence.mapper;

import com.pragma.challenge.report_service.domain.model.BootcampReport;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.entity.BootcampReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportBootcampEntityMapper {
  @Mapping(target = "bootcampId", source = "id")
  BootcampReport toModel(BootcampReportEntity entity);

  @Mapping(target = "id", source = "bootcampId")
  BootcampReportEntity toEntity(BootcampReport model);
}
