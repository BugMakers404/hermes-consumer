package org.bugmakers404.hermes.consumer.vicroad.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteStats;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;

public class RouteStatsDeserializer extends JsonDeserializer<RouteStats> {

  @Override
  public RouteStats deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    JsonNode routeEvent = parser.getCodec().readTree(parser);
    JsonNode latestStats = routeEvent.get("latest_stats");

    RouteStats routeStats = new RouteStats();
    routeStats.setRouteId(routeEvent.get("id").asInt());
    routeStats.setTimestamp(OffsetDateTime.parse(latestStats.get("interval_start").asText(),
        Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    routeStats.setId(routeStats.getTimestamp() + "_" + routeStats.getRouteId());
    routeStats.setTravelTime(latestStats.get("travel_time").asInt());
    routeStats.setDelay(latestStats.get("delay").asInt());
    routeStats.setSpeed(latestStats.get("speed").asInt());
    routeStats.setExcessDelay(latestStats.get("excess_delay").asInt());
    routeStats.setDataStatus(latestStats.get("data_status").asText());

    return routeStats;
  }
}
