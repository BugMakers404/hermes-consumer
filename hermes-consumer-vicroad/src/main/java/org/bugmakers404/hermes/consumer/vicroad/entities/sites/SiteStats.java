package org.bugmakers404.hermes.consumer.vicroad.entities.sites;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteStats implements Serializable {

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
