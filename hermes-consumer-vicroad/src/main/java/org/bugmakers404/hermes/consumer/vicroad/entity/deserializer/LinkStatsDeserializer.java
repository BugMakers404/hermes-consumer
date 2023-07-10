package org.bugmakers404.hermes.consumer.vicroad.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;

public class LinkStatsDeserializer extends JsonDeserializer<LinkStats> {

  @Override
  public LinkStats deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    JsonNode linkEvent = parser.getCodec().readTree(parser);
    JsonNode latestStats = linkEvent.get("latest_stats");

    LinkStats linkStats = new LinkStats();
    linkStats.setLinkId(linkEvent.get("id").asInt());
    linkStats.setTimestamp(OffsetDateTime.parse(latestStats.get("interval_start").asText(),
        Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    linkStats.setId(linkStats.getTimestamp() + "_" + linkStats.getLinkId());
    linkStats.setTravelTime(latestStats.get("travel_time").asInt());
    linkStats.setDelay(latestStats.get("delay").asInt());
    linkStats.setSpeed(latestStats.get("speed").asInt());
    linkStats.setExcessDelay(latestStats.get("excess_delay").asInt());
    linkStats.setCongestion(latestStats.get("congestion").asInt());
    linkStats.setScore(latestStats.get("score").asInt());
    linkStats.setFlowRestrictionScore(latestStats.get("flow_restriction_score").asInt());
    linkStats.setAverageDensity(latestStats.get("average_density").asInt());
    linkStats.setDensity(latestStats.get("density").asInt());
    linkStats.setEnoughData(latestStats.get("enough_data").asBoolean());
    linkStats.setIgnored(latestStats.get("ignored").asBoolean());
    linkStats.setClosed(latestStats.get("closed").asBoolean());

    return linkStats;
  }
}
