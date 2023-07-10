package org.bugmakers404.hermes.consumer.vicroad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.entity.deserializer.RouteInfoDeserializer;
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
@JsonDeserialize(using = RouteInfoDeserializer.class)
@Document(collection = "vicroad.bluetooth.route.info")
@CompoundIndex(name = "routeId_timestamp_idx", def = "{'routeId': 1, 'timestamp': -1}")
public class RouteInfo {

  @Id
  private String id;

  @Indexed
  private Integer routeId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN_IN_EVENTS)
  private OffsetDateTime timestamp;

  private String name;

  private String primaryRoadName;

  private String startEndDescription;

  private Integer length;

  private Boolean enabled;

  private Boolean draft;

  private List<Integer> links;

  public Boolean isSame(RouteInfo other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    return Objects.equals(name, other.name)
        && Objects.equals(primaryRoadName, other.primaryRoadName)
        && Objects.equals(startEndDescription, other.startEndDescription)
        && Objects.equals(length, other.length)
        && Objects.equals(enabled, other.enabled)
        && Objects.equals(draft, other.draft)
        && Objects.equals(links, other.links);
  }

}
