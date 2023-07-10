package org.bugmakers404.hermes.consumer.vicroad.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;

public class SiteStatsDeserializer extends JsonDeserializer<SiteStats> {

  @Override
  public SiteStats deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    JsonNode siteEvent = parser.getCodec().readTree(parser);
    JsonNode latestStats = siteEvent.get("latest_stats");

    SiteStats siteStats = new SiteStats();
    siteStats.setSiteId(siteEvent.get("id").asInt());
    siteStats.setTimestamp(OffsetDateTime.parse(latestStats.get("interval_start").asText(),
        Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    siteStats.setId(siteStats.getTimestamp() + "_" + siteStats.getSiteId());
    siteStats.setProbeCount(latestStats.get("probe_count").asInt());
    siteStats.setRawProbeCount(latestStats.get("raw_probe_count").asInt());
    siteStats.setEstimatedEventCount(latestStats.get("estimated_event_count").asInt());
    siteStats.setAverageDuration(latestStats.get("average_duration").asInt());

    return siteStats;
  }
}
