package org.bugmakers404.hermes.consumer.vicroad.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;

public class LinkInfoDeserializer extends JsonDeserializer<LinkInfo> {

  @Override
  public LinkInfo deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    JsonNode linkWithGeoEvent = parser.getCodec().readTree(parser);

    LinkInfo linkInfo = new LinkInfo();
    linkInfo.setLinkId(linkWithGeoEvent.get("id").asInt());
    linkInfo.setTimestamp(
        OffsetDateTime.parse(linkWithGeoEvent.get("latest_stats").get("interval_start").asText(),
            Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    linkInfo.setId(linkInfo.getTimestamp() + "_" + linkInfo.getLinkId());
    linkInfo.setName(linkWithGeoEvent.get("name").asText());
    linkInfo.setOriginId(linkWithGeoEvent.get("origin").get("id").asInt());
    linkInfo.setDestinationId(linkWithGeoEvent.get("destination").get("id").asInt());
    linkInfo.setLength(linkWithGeoEvent.get("length").asInt());
    linkInfo.setMinNumberOfLanes(linkWithGeoEvent.get("min_number_of_lanes").asInt());
    linkInfo.setFreeway(linkWithGeoEvent.get("is_freeway").asBoolean());
    linkInfo.setDirection(linkWithGeoEvent.get("direction").asText());
    linkInfo.setEnabled(linkWithGeoEvent.get("enabled").asBoolean());
    linkInfo.setDraft(linkWithGeoEvent.get("draft").asBoolean());
    linkInfo.setCoordinates(parser.getCodec()
        .readValue(linkWithGeoEvent.get("coordinates").traverse(), new TypeReference<>() {
        }));

    return linkInfo;
  }
}
