package org.bugmakers404.hermes.consumer.vicroad.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;

public class RouteInfoDeserializer extends JsonDeserializer<RouteInfo> {

  @Override
  public RouteInfo deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    JsonNode routeEvent = parser.getCodec().readTree(parser);

    RouteInfo routeInfo = new RouteInfo();
    routeInfo.setRouteId(routeEvent.get("id").asInt());
    routeInfo.setTimestamp(
        OffsetDateTime.parse(routeEvent.get("latest_stats").get("interval_start").asText(),
            Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    routeInfo.setId(routeInfo.getTimestamp() + "_" + routeInfo.getRouteId());
    routeInfo.setName(routeEvent.get("name").asText());
    routeInfo.setPrimaryRoadName(routeEvent.get("primary_road_name").asText());
    routeInfo.setStartEndDescription(routeEvent.get("start_end_description").asText());
    routeInfo.setEnabled(routeEvent.get("enabled").asBoolean());
    routeInfo.setDraft(routeEvent.get("draft").asBoolean());
    routeInfo.setLength(routeEvent.get("length").asInt());
    routeInfo.setLinks(StreamSupport.stream(routeEvent.get("links").spliterator(), false)
        .map(link -> link.get("id").asInt()).collect(Collectors.toList()));

    return routeInfo;

  }
}
