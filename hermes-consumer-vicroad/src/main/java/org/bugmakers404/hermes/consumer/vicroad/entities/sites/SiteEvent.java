package org.bugmakers404.hermes.consumer.vicroad.entities.sites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkStats;

@Slf4j
@Data
@AllArgsConstructor
//@Document(collection = "bluetooth_raw_data.sites")
public class SiteEvent implements Serializable {

  private Integer id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;

  private Boolean enabled;

  private Boolean draft;

  private LinkStats latestStats;

  @JsonProperty("latest_stats")
  public void setLatestStats(LinkStats latestStats) {
    this.latestStats = latestStats;
  }
}
