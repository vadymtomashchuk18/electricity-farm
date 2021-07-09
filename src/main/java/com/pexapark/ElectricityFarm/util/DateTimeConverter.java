package com.pexapark.ElectricityFarm.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class DateTimeConverter {

  private DateTimeConverter() {}

  /* In LocalDateTime time zone is not specified but included*/
  public static LocalDateTime localDateTimeWithTimezoneToUTC(
      LocalDateTime dateTime, String timeZone) {
    ZoneId zone = ZoneId.of(timeZone);
    ZonedDateTime zonedDateTime = dateTime.atZone(zone);

    LocalDateTime dateTimeUTC =
        LocalDateTime.from(zonedDateTime.withZoneSameInstant(ZoneOffset.UTC));
    return dateTimeUTC;
  }
}
