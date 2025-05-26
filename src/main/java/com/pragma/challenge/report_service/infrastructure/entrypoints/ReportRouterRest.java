package com.pragma.challenge.report_service.infrastructure.entrypoints;

import static com.pragma.challenge.report_service.domain.constants.ConstantsRoute.BASE_PATH;
import static com.pragma.challenge.report_service.domain.constants.ConstantsRoute.BOOTCAMP_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.pragma.challenge.report_service.domain.constants.ConstantsMsg;
import com.pragma.challenge.report_service.domain.model.BootcampIdList;
import com.pragma.challenge.report_service.infrastructure.entrypoints.dto.BootcampReportDto;
import com.pragma.challenge.report_service.infrastructure.entrypoints.handler.ReportHandler;
import com.pragma.challenge.report_service.infrastructure.entrypoints.util.SwaggerResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ReportRouterRest {

  @Bean
  @RouterOperations({
    @RouterOperation(
        path = BASE_PATH + BOOTCAMP_PATH,
        method = POST,
        beanClass = ReportHandler.class,
        beanMethod = "createBootcampReport",
        operation =
            @Operation(
                operationId = "createBootcampReport",
                summary = "Create bootcamp report",
                requestBody =
                    @RequestBody(
                        content =
                            @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = BootcampReportDto.class))),
                responses = {
                  @ApiResponse(
                      responseCode = "201",
                      description = ConstantsMsg.SUCCESS_REGISTER_BOOTCAMP_REPORT_MSG,
                      content =
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema =
                                  @Schema(
                                      implementation =
                                          SwaggerResponses.DefaultMessageResponse.class))),
                  @ApiResponse(
                      responseCode = "400",
                      description = ConstantsMsg.BAD_REQUEST_MSG,
                      content =
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema =
                                  @Schema(
                                      implementation =
                                          SwaggerResponses.DefaultErrorResponse.class))),
                  @ApiResponse(
                      responseCode = "500",
                      description = ConstantsMsg.SERVER_ERROR_MSG,
                      content =
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema =
                                  @Schema(
                                      implementation =
                                          SwaggerResponses.DefaultErrorResponse.class)))
                })),
    @RouterOperation(
        path = BASE_PATH + BOOTCAMP_PATH,
        method = PATCH,
        beanClass = ReportHandler.class,
        beanMethod = "addUserCountToBootcamp",
        operation =
            @Operation(
                operationId = "addUserCountToBootcamp",
                summary = "Add user to the bootcamps report count",
                requestBody =
                    @RequestBody(
                        content =
                            @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = BootcampIdList.class))),
                responses = {
                  @ApiResponse(
                      responseCode = "200",
                      description = ConstantsMsg.SUCCESS_UPDATE_USER_COUNT_FOR_BOOTCAMP_REPORT_MSG,
                      content =
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema =
                                  @Schema(
                                      implementation =
                                          SwaggerResponses.DefaultMessageResponse.class))),
                  @ApiResponse(
                      responseCode = "400",
                      description = ConstantsMsg.BAD_REQUEST_MSG,
                      content =
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema =
                                  @Schema(
                                      implementation =
                                          SwaggerResponses.DefaultErrorResponse.class))),
                  @ApiResponse(
                      responseCode = "500",
                      description = ConstantsMsg.SERVER_ERROR_MSG,
                      content =
                          @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema =
                                  @Schema(
                                      implementation =
                                          SwaggerResponses.DefaultErrorResponse.class)))
                }))
  })
  public RouterFunction<ServerResponse> routerFunction(ReportHandler reportHandler) {
    return nest(
        path(BASE_PATH),
        route(POST(BOOTCAMP_PATH), reportHandler::createBootcampReport)
            .andRoute(PATCH(BOOTCAMP_PATH), reportHandler::addUserCountToBootcamp));
  }
}
