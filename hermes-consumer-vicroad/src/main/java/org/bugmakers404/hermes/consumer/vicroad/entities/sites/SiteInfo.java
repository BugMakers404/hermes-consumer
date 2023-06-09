package org.bugmakers404.hermes.consumer.vicroad.entities.sites;

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

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vicroad.bluetooth.site.info")
@CompoundIndex(name = "siteId_timestamp_idx", def = "{'siteId': 1, 'timestamp': -1}")
public class SiteInfo {

    @Id
    private String id;

    @Indexed
    private Integer siteId;

    @Indexed
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime timestamp;

    private String name;

    private List<Double> location;

    public Boolean isSame(SiteInfo other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        return Objects.equals(name, other.name)
                && Objects.equals(location, other.location);
    }
}
