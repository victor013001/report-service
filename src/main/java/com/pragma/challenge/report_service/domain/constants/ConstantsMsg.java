package com.pragma.challenge.report_service.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantsMsg {
  public static final String BAD_REQUEST_MSG =
      "The request could not be processed due to invalid or incomplete data.";
  public static final String SERVER_ERROR_MSG =
      "An unexpected server error occurred. Please try again later.";
  public static final String GATEWAY_ERROR_MSG =
      "Failed to process the request due to an internal gateway error.";
  public static final String GATEWAY_BAD_REQUEST_MSG =
      "An unexpected error occurred while processing the request through the gateway.";
  public static final String SUCCESS_REGISTER_BOOTCAMP_REPORT_MSG =
      "The bootcamp report was register successfully.";
  public static final String SUCCESS_UPDATE_USER_COUNT_FOR_BOOTCAMP_REPORT_MSG =
      "The bootcamp report was updated successfully.";
}
