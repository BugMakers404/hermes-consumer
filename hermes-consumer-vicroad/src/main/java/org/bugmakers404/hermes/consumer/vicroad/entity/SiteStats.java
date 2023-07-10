package org.bugmakers404.hermes.consumer.vicroad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.entity.deserializer.SiteStatsDeserializer;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = SiteStatsDeserializer.class)
@Document(collection = "vicroad.bluetooth.site.stats")
@CompoundIndex(name = "siteId_timestamp_idx", def = "{'siteId': 1, 'timestamp': -1}")
public class SiteStats implements Serializable {

  @Id
  private String id;

  @Indexed
  private Integer siteId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN_IN_EVENTS)
  private OffsetDateTime timestamp;

  private Integer probeCount;

  private Integer rawProbeCount;

  private Integer estimatedEventCount;

  private Integer averageDuration;
}
