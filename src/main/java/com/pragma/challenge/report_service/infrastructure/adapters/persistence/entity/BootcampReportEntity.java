package com.pragma.challenge.report_service.infrastructure.adapters.persistence.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Bootcamp")
public class BootcampReportEntity {
  @Id private Long id;
  private String name;
  private String description;
  private LocalDate launchDate;
  private int durationInWeeks;
  private int profileCount;
  private int technologyCount;
  private int userCount;
}
