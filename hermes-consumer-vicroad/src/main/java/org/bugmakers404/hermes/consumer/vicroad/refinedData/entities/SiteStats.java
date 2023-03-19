package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class SiteStats {

  private String intervalStart;

  private Integer probeCount;

  private Integer rawProbeCount;

  private Integer estimatedEventCount;

  private Integer averageDuration;


  @JsonProperty("interval_start")
  public void setIntervalStartTime(String intervalStartTime) {
    this.intervalStart = intervalStartTime;
  }

  @JsonProperty("probe_count")
  public void setProbeCount(Integer probeCount) {
    this.probeCount = probeCount;
  }

  @JsonProperty("raw_probe_count")
  public void setRawProbeCount(Integer rawProbeCount) {
    this.rawProbeCount = rawProbeCount;
  }

  @JsonProperty("estimated_event_count")
  public void setEstimatedEventCount(Integer estimatedEventCount) {
    this.estimatedEventCount = estimatedEventCount;
  }

  @JsonProperty("average_duration")
  public void setAverageDuration(Integer averageDuration) {
    this.averageDuration = averageDuration;
  }
}
