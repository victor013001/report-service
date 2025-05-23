package com.pragma.challenge.report_service;

import static com.pragma.challenge.report_service.util.BootcampIdListData.getBootcampIdList;
import static com.pragma.challenge.report_service.util.BootcampReportDtoData.getBootcampReportDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pragma.challenge.report_service.domain.constants.ConstantsMsg;
import com.pragma.challenge.report_service.domain.constants.ConstantsRoute;
import com.pragma.challenge.report_service.domain.exceptions.StandardError;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.entity.BootcampReportEntity;
import com.pragma.challenge.report_service.infrastructure.adapters.persistence.repository.ReportBootcampRepository;
import com.pragma.challenge.report_service.infrastructure.entrypoints.dto.DefaultServerResponse;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("it")
@AutoConfigureWebTestClient
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportRouterRestIT {
  @Autowired WebTestClient webTestClient;
  @Autowired ReportBootcampRepository reportBootcampRepository;

  @BeforeEach
  void setUp() {

    reportBootcampRepository
        .saveAll(
            List.of(
                BootcampReportEntity.builder()
                    .id(1L)
                    .name("Bootcamp Java")
                    .description("Java Backend Bootcamp")
                    .launchDate(LocalDate.of(2025, 1, 15))
                    .durationInWeeks(12)
                    .profileCount(30)
                    .technologyCount(10)
                    .userCount(100)
                    .build()))
        .blockLast();
  }

  @Test
  void createBootcampReportSuccess() {
    webTestClient
        .post()
        .uri(ConstantsRoute.BASE_PATH + ConstantsRoute.BOOTCAMP_PATH)
        .bodyValue(getBootcampReportDto())
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(DefaultServerResponse.class)
        .consumeWith(
            response -> {
              var body = response.getResponseBody();
              assertNotNull(body);
              assertEquals(ConstantsMsg.SUCCESS_REGISTER_BOOTCAMP_REPORT_MSG, body.data());
            });
  }

  @Test
  void createBootcampReportBadRequest() {
    webTestClient
        .post()
        .uri(ConstantsRoute.BASE_PATH + ConstantsRoute.BOOTCAMP_PATH)
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(
            new ParameterizedTypeReference<DefaultServerResponse<Object, StandardError>>() {})
        .consumeWith(
            response -> {
              var body = response.getResponseBody();
              assertNotNull(body);
              assertEquals(ConstantsMsg.BAD_REQUEST_MSG, body.error().getDescription());
            });
  }

  @Test
  void addUserCountToBootcampSuccess() {
    webTestClient
        .patch()
        .uri(ConstantsRoute.BASE_PATH + ConstantsRoute.BOOTCAMP_PATH)
        .bodyValue(getBootcampIdList())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(DefaultServerResponse.class)
        .consumeWith(
            response -> {
              var body = response.getResponseBody();
              assertNotNull(body);
              assertEquals(
                  ConstantsMsg.SUCCESS_UPDATE_USER_COUNT_FOR_BOOTCAMP_REPORT_MSG, body.data());
            });
  }
}
