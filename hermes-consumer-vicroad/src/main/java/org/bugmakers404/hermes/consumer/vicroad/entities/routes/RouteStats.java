package org.bugmakers404.hermes.consumer.vicroad.entities.routes;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteStats {

  @JsonAlias("interval_start")
  private String intervalStart;

  @JsonAlias("travel_time")
  private Integer travelTime;

  private Integer delay;

  private Integer speed;

  @JsonAlias("excess_delay")
  private Integer excessDelay;

  @JsonAlias("data_status")
  private String dataStatus;

  private Integer length;
}
