package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkStats {

  private String intervalStart;

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

  @JsonProperty("interval_start")
  public void setIntervalStartTime(String intervalStartTime) {
    this.intervalStart = intervalStartTime;
  }

  @JsonProperty("travel_time")
  public void setTravelTime(Integer travelTime) {
    this.travelTime = travelTime;
  }

  @JsonProperty("flow_restriction_score")
  public void setFlowRestrictionScore(Integer flowRestrictionScore) {
    this.flowRestrictionScore = flowRestrictionScore;
  }

  @JsonProperty("average_density")
  public void setAverageDensity(Integer averageDensity) {
    this.averageDensity = averageDensity;
  }

  @JsonProperty("enough_data")
  public void setEnoughData(Boolean enoughData) {
    this.enoughData = enoughData;
  }
}
