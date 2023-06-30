package org.bugmakers404.hermes.consumer.vicroad.entity.sites;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vicroad.bluetooth.site.events")
@CompoundIndex(name = "siteId_timestamp_idx", def = "{'siteId': 1, 'timestamp': -1}")
public class SiteEvent implements Serializable {

  @Id
  private String id;

  @Indexed
  private Integer siteId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private OffsetDateTime timestamp;

  private Boolean enabled;

  private Boolean draft;

  @JsonAlias("latest_stats")
  private SiteStats latestStats;
}
