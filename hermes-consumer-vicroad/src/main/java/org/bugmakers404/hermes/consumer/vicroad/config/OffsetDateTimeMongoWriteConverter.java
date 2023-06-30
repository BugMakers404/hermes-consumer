package org.bugmakers404.hermes.consumer.vicroad.config;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class OffsetDateTimeMongoWriteConverter implements Converter<OffsetDateTime, String> {

  @Override
  public String convert(OffsetDateTime source) {
    return source.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }
}
