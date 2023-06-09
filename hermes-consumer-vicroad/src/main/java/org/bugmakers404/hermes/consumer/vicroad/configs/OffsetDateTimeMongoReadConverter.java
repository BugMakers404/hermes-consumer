package org.bugmakers404.hermes.consumer.vicroad.configs;

import java.time.OffsetDateTime;

import org.springframework.core.convert.converter.Converter;

public class OffsetDateTimeMongoReadConverter implements Converter<String, OffsetDateTime> {

    @Override
    public OffsetDateTime convert(String source) {
        return OffsetDateTime.parse(source);
    }
}
