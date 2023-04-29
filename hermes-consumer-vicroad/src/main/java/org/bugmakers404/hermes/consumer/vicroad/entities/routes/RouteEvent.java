package org.bugmakers404.hermes.consumer.vicroad.entities.routes;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vicroad.bluetooth.route.events")
public class RouteEvent implements Serializable {

  @Id
  private String id;

  @Indexed
  private Integer routeId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private OffsetDateTime timestamp;

  private Boolean enabled;

  private Boolean draft;

  @JsonAlias("latest_stats")
  private RouteStats latestStats;
}
