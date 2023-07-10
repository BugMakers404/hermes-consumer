package org.bugmakers404.hermes.consumer.vicroad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.entity.deserializer.SiteInfoDeserializer;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = SiteInfoDeserializer.class)
@Document(collection = "vicroad.bluetooth.site.info")
@CompoundIndex(name = "siteId_timestamp_idx", def = "{'siteId': 1, 'timestamp': -1}")
public class SiteInfo {

  @Id
  private String id;

  @Indexed
  private Integer siteId;

  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN_IN_EVENTS)
  private OffsetDateTime timestamp;

  private String name;

  private Boolean enabled;

  private Boolean draft;

  private List<Double> location;

  public Boolean isSame(SiteInfo other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    return Objects.equals(name, other.name)
        && Objects.equals(enabled, other.enabled)
        && Objects.equals(draft, other.draft)
        && Objects.equals(location, other.location);
  }
}
