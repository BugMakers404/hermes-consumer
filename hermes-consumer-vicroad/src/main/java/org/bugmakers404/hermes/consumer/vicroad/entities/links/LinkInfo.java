package org.bugmakers404.hermes.consumer.vicroad.entities.links;

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
import java.util.List;
import java.util.Objects;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vicroad.bluetooth.link.info")
@CompoundIndex(name = "linkId_timestamp_idx", def = "{'linkId': 1, 'timestamp': -1}")
public class LinkInfo implements Serializable {

    @Id
    private String id;

    @Indexed
    private Integer linkId;

    @Indexed
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime timestamp;

    private String name;

    @JsonAlias("origin.id")
    private Integer originId;

    @JsonAlias("destination.id")
    private Integer destinationId;

    private Integer length;

    @JsonAlias("min_number_of_lanes")
    private Integer minNumberOfLanes;

    @JsonAlias("minimum_tt")
    private Integer minTravelTime;

    @JsonAlias("is_freeway")
    private Boolean isFreeway;

    private String direction;

    private List<List<Double>> coordinates;

    public Boolean isSame(LinkInfo other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        return Objects.equals(name, other.name)
                && Objects.equals(originId, other.originId)
                && Objects.equals(destinationId, other.destinationId)
                && Objects.equals(length, other.length)
                && Objects.equals(minNumberOfLanes, other.minNumberOfLanes)
                && Objects.equals(minTravelTime, other.minTravelTime)
                && Objects.equals(isFreeway, other.isFreeway)
                && Objects.equals(direction, other.direction)
                && Objects.equals(coordinates, other.coordinates);
    }

}
