package org.bugmakers404.hermes.consumer.vicroad.entities.routes;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vicroad.bluetooth.route.info")
public class RouteInfo {

  @Id
  private String id;

  @NonNull
  @Indexed
  private Integer routeId;

  @NonNull
  @Indexed
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private OffsetDateTime timestamp;

  private String name;

  @JsonAlias("primary_road_name")
  private String primaryRoadName;

  @JsonAlias("start_end_description")
  private String startEndDescription;

  private Integer length;

  private List<Integer> links;

  @JsonSetter("links")
  public void setLinks(JsonNode linksNode) {
    this.links = new ArrayList<>();

    for (JsonNode linkNode : linksNode) {
      if (linkNode instanceof IntNode) {
        this.links.add(linkNode.asInt());
      } else {
        this.links.add(linkNode.get("id").asInt());
      }
    }
  }

  public Boolean isSame(RouteInfo other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    return Objects.equals(routeId, other.routeId)
        && Objects.equals(name, other.name)
        && Objects.equals(primaryRoadName, other.primaryRoadName)
        && Objects.equals(startEndDescription, other.startEndDescription)
        && Objects.equals(length, other.length)
        && Objects.equals(links, other.links);
  }

}
