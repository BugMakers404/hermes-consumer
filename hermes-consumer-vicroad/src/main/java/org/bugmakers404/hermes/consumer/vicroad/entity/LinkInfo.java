package org.bugmakers404.hermes.consumer.vicroad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.entity.deserializer.LinkInfoDeserializer;
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
@JsonDeserialize(using = LinkInfoDeserializer.class)
@Document(collection = "vicroad.bluetooth.link.info")
@CompoundIndex(name = "linkId_timestamp_idx", def = "{'linkId': 1, 'timestamp': -1}")
public class LinkInfo implements Serializable {

  @Id
  private String id;

  @Indexed
  private Integer linkId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN_IN_EVENTS)
  private OffsetDateTime timestamp;

  private String name;

  private Integer originId;

  private Integer destinationId;

  private Integer length;

  private Integer minNumberOfLanes;

  private Boolean freeway;

  private String direction;

  private Boolean enabled;

  private Boolean draft;

  private List<List<Double>> coordinates;

  public Boolean isSame(LinkInfo other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    return Objects.equals(name, other.name)
        && Objects.equals(originId, other.originId)
        && Objects.equals(destinationId, other.destinationId)
        && Objects.equals(length, other.length)
        && Objects.equals(minNumberOfLanes, other.minNumberOfLanes)
        && Objects.equals(freeway, other.freeway)
        && Objects.equals(direction, other.direction)
        && Objects.equals(enabled, other.enabled)
        && Objects.equals(draft, other.draft)
        && Objects.equals(coordinates, other.coordinates);
  }

}
