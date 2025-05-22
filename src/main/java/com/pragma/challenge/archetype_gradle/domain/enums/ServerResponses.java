package com.pragma.challenge.archetype_gradle.domain.enums;

import com.pragma.challenge.archetype_gradle.domain.constants.ConstantsMsg;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServerResponses {
  BAD_REQUEST("E000", 400, ConstantsMsg.BAD_REQUEST_MSG),
  SERVER_ERROR("E001", 500, ConstantsMsg.SERVER_ERROR_MSG),
  GATEWAY_ERROR("E002", 500, ConstantsMsg.GATEWAY_ERROR_MSG),
  GATEWAY_BAD_REQUEST("E003", 400, ConstantsMsg.GATEWAY_BAD_REQUEST_MSG);

  private final String code;
  private final int httpStatus;
  private final String message;
}
