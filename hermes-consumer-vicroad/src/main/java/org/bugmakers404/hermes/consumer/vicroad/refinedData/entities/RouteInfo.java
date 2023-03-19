package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class RouteInfo {

  private Integer id;

  private String name;

  private String primaryRoadName;

  private String startEndDescription;

  private Integer length;

  private List<Integer> links;

  @JsonProperty("primary_road_name")
  public void setPrimaryRoadName(String primaryRoadName) {
    this.primaryRoadName = primaryRoadName;
  }

  @JsonProperty("start_end_description")
  public void setStartEndDescription(String startEndDescription) {
    this.startEndDescription = startEndDescription;
  }

  @JsonSetter("links")
  public void setLinks(JsonNode linksNode) {
    this.links = new ArrayList<>();
    for (JsonNode linkNode : linksNode) {
      this.links.add(linkNode.get("id").asInt());
    }
  }

}
