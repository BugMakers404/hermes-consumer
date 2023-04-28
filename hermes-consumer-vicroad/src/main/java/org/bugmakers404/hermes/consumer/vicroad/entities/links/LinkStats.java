package org.bugmakers404.hermes.consumer.vicroad.entities.links;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkStats {

  @JsonAlias("interval_start")
  private String intervalStart;

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
