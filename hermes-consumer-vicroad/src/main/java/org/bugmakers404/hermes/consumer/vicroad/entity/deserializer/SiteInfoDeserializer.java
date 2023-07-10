package org.bugmakers404.hermes.consumer.vicroad.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;

public class SiteInfoDeserializer extends JsonDeserializer<SiteInfo> {

  @Override
  public SiteInfo deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    JsonNode siteEvent = parser.getCodec().readTree(parser);

    SiteInfo siteInfo = new SiteInfo();
    siteInfo.setSiteId(siteEvent.get("id").asInt());
    siteInfo.setTimestamp(
        OffsetDateTime.parse(siteEvent.get("latest_stats").get("interval_start").asText(),
            Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    siteInfo.setId(siteInfo.getTimestamp() + "_" + siteInfo.getSiteId());
    siteInfo.setName(siteEvent.get("name").asText());
    siteInfo.setEnabled(siteEvent.get("enabled").asBoolean());
    siteInfo.setDraft(siteEvent.get("draft").asBoolean());
    siteInfo.setLocation(
        parser.getCodec().readValue(siteEvent.get("location").traverse(), new TypeReference<>() {
        }));

    return siteInfo;
  }
}
