package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
//@Document(collection = "bluetooth_raw_data.links_with_geo")
public class LinkGeoInfo implements Serializable {

  @Id
  private String id;

  @NonNull
  @Indexed
  private Integer linkId;

  @NonNull
  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;

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

//  @JsonProperty("origin.id")
//  @JsonAlias("originId")
//  public void setOriginId(Integer originId) {
//    this.origin = originId;
//  }
//
//  @JsonProperty("destination.id")
//  @JsonAlias("destinationId")
//  public void setDestinationId(Integer destinationId) {
//    this.destination = destinationId;
//  }
}
