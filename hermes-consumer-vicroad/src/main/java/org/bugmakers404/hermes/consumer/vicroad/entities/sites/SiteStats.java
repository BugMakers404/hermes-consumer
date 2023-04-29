package org.bugmakers404.hermes.consumer.vicroad.entities.sites;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteStats {

  @JsonAlias("interval_start")
  private String intervalStart;

  @JsonAlias("probe_count")
  private Integer probeCount;

  @JsonAlias("raw_probe_count")
  private Integer rawProbeCount;

  @JsonAlias("raw_probe_count")
  private Integer estimatedEventCount;

  @JsonAlias("average_duration")
  private Integer averageDuration;

}
