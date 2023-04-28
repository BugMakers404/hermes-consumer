package org.bugmakers404.hermes.consumer.vicroad.entities.links;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vicroad.bluetooth.link.info")
public class LinkInfo implements Serializable {

  @Id
  private String id;

  @NonNull
  @Indexed
  private Integer linkId;

  @NonNull
  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private OffsetDateTime timestamp;

  private String name;

  @JsonAlias("origin.id")
  private Integer originId;

  @JsonAlias("destination.id")
  private Integer destinationId;

  private Integer length;

  @JsonAlias("min_number_of_lanes")
  private Integer minNumberOfLanes;

  @JsonAlias("minimum_tt")
  private Integer minTravelTime;

  @JsonAlias("is_freeway")
  private Boolean isFreeway;

  private String direction;

  private List<List<Double>> coordinates;

  public Boolean isSame(LinkInfo other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }

    return Objects.equals(linkId, other.linkId) && Objects.equals(name, other.name)
        && Objects.equals(originId, other.originId) && Objects.equals(destinationId,
        other.destinationId) && Objects.equals(length, other.length) && Objects.equals(
        minNumberOfLanes, other.minNumberOfLanes) && Objects.equals(minTravelTime,
        other.minTravelTime) && Objects.equals(isFreeway, other.isFreeway) && Objects.equals(
        direction, other.direction) && Objects.equals(coordinates, other.coordinates);
  }

}
