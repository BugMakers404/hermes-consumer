package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class RouteStats {

  private String intervalStart;

  private Integer travelTime;

  private Integer delay;

  private Integer speed;

  private Integer excessDelay;

  private String dataStatus;

  @JsonProperty("interval_start")
  public void setIntervalStartTime(String intervalStart) {
    this.intervalStart = intervalStart;
  }

  @JsonProperty("travel_time")
  public void setTravelTime(Integer travelTime) {
    this.travelTime = travelTime;
  }

  @JsonProperty("excess_delay")
  public void setExcessDelay(Integer excessDelay) {
    this.excessDelay = excessDelay;
  }

  @JsonProperty("data_status")
  public void setDataStatus(String dataStatus) {
    this.dataStatus = dataStatus;
  }

}
