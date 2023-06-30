package org.bugmakers404.hermes.consumer.vicroad.entity.links;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkStats implements Serializable {

  @JsonAlias("travel_time")
  private Integer travelTime;

  private Integer delay;

  private Integer speed;

  @JsonAlias("excess_delay")
  private Integer excessDelay;

  private Integer congestion;

  private Integer score;

  @JsonAlias("flow_restriction_score")
  private Integer flowRestrictionScore;

  @JsonAlias("average_density")
  private Integer averageDensity;

  private Integer density;

  @JsonAlias("enough_data")
  private Boolean enoughData;

  private Boolean ignored;

  private Boolean closed;
}
