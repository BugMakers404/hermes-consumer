package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
//@Document(collection = "bluetooth_raw_data.routes")
public class RouteEvent implements Serializable {

  private Integer id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;

  private Boolean enabled;

  private Boolean draft;

  private RouteStats latestStats;

  @JsonProperty("latest_stats")
  public void setLatestStats(RouteStats latestStats) {
    this.latestStats = latestStats;
  }
}

