package com.pragma.challenge.report_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class ReportServiceApplicationTests {

  @Test
  void contextLoads() {
    // This method is intentionally left empty to verify that the Spring application context loads
  }
}
