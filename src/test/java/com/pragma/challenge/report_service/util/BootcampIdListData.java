package com.pragma.challenge.report_service.util;

import com.pragma.challenge.report_service.domain.model.BootcampIdList;
import java.util.List;

public class BootcampIdListData {
  private BootcampIdListData() throws InstantiationException {
    throw new InstantiationException("Data class cannot be instantiated");
  }

  public static BootcampIdList getBootcampIdList() {
    return new BootcampIdList(List.of(1L));
  }
}
