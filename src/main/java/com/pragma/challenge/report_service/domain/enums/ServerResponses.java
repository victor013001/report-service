package com.pragma.challenge.report_service.domain.enums;

import com.pragma.challenge.report_service.domain.constants.ConstantsMsg;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServerResponses {
  BAD_REQUEST("E000", 400, ConstantsMsg.BAD_REQUEST_MSG),
  SERVER_ERROR("E001", 500, ConstantsMsg.SERVER_ERROR_MSG),
  GATEWAY_ERROR("E002", 500, ConstantsMsg.GATEWAY_ERROR_MSG),
  GATEWAY_BAD_REQUEST("E003", 400, ConstantsMsg.GATEWAY_BAD_REQUEST_MSG),
  SUCCESS_REGISTER_BOOTCAMP_REPORT("E004", 201, ConstantsMsg.SUCCESS_REGISTER_BOOTCAMP_REPORT_MSG);

  private final String code;
  private final int httpStatus;
  private final String message;
}
