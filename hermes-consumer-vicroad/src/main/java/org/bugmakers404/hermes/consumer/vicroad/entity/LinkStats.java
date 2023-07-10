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
import org.bugmakers404.hermes.consumer.vicroad.entity.deserializer.LinkStatsDeserializer;
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
@JsonDeserialize(using = LinkStatsDeserializer.class)
@Document(collection = "vicroad.bluetooth.link.stats")
@CompoundIndex(name = "linkId_timestamp_idx", def = "{'linkId': 1, 'timestamp': -1}")
public class LinkStats implements Serializable {

  @Id
  private String id;

  @Indexed
  private Integer linkId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN_IN_EVENTS)
  private OffsetDateTime timestamp;

  private Integer travelTime;

  private Integer delay;

  private Integer speed;

  private Integer excessDelay;

  private Integer congestion;

  private Integer score;

  private Integer flowRestrictionScore;

  private Integer averageDensity;

  private Integer density;

  private Boolean enoughData;

  private Boolean ignored;

  private Boolean closed;
}
