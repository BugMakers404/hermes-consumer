package org.bugmakers404.hermes.consumer.vicroad.configs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {

  @Bean
  public MongoCustomConversions customConversions() {
    return new MongoCustomConversions(Arrays.asList(
        new OffsetDateTimeMongoWriteConverter(),
        new OffsetDateTimeMongoReadConverter()
    ));
  }
}
