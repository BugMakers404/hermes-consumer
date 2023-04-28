package org.bugmakers404.hermes.consumer.vicroad.task;

import static org.bugmakers404.hermes.consumer.vicroad.utils.KafkaConstants.BLUETOOTH_DATA_TOPIC_LINKS;
import static org.bugmakers404.hermes.consumer.vicroad.utils.KafkaConstants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO;
import static org.bugmakers404.hermes.consumer.vicroad.utils.KafkaConstants.BLUETOOTH_DATA_TOPIC_ROUTES;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteEvent;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaEventsConsumer {

  private final PersistentLinkEventService persistentLinkEventService;

  private final PersistentLinkInfoService persistentLinkInfoService;

  private final PersistentRouteEventService persistentRouteEventService;

  private final PersistentRouteInfoService persistentRouteInfoService;

  private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

  @KafkaListener(topics = {BLUETOOTH_DATA_TOPIC_LINKS})
  public void persistLinkEvent(ConsumerRecord<String, String> record, Acknowledgment ack)
      throws Exception {
    LinkEvent linkEvent = objectMapper.readValue(record.value(), LinkEvent.class);

    String[] timestampAndLinkId = record.key().split("_");
    String timestamp = timestampAndLinkId[0];
    Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

    linkEvent.setId(record.key());
    linkEvent.setLinkId(linkId);
    linkEvent.setTimestamp(OffsetDateTime.parse(timestamp));

    persistentLinkEventService.saveLinkEvent(linkEvent);
    ack.acknowledge();
  }

  @KafkaListener(topics = {BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO})
  public void persistLinkWithGeoEvent(ConsumerRecord<String, String> record, Acknowledgment ack)
      throws Exception {

    LinkInfo linkInfo = objectMapper.readValue(record.value(), LinkInfo.class);

    String[] timestampAndLinkId = record.key().split("_");
    String timestamp = timestampAndLinkId[0];
    Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

    linkInfo.setId(record.key());
    linkInfo.setLinkId(linkId);
    linkInfo.setTimestamp(OffsetDateTime.parse(timestamp));

    persistentLinkInfoService.saveLinkInfoIfChanged(linkInfo);
    ack.acknowledge();
  }

  @KafkaListener(topics = {BLUETOOTH_DATA_TOPIC_ROUTES})
  public void persistRouteEvent(ConsumerRecord<String, String> record, Acknowledgment ack)
      throws Exception {

    RouteEvent routeEvent = objectMapper.readValue(record.value(), RouteEvent.class);
    RouteInfo routeInfo = objectMapper.readValue(record.value(), RouteInfo.class);

    String[] timestampAndRouteId = record.key().split("_");
    String timestamp = timestampAndRouteId[0];
    Integer routeId = Integer.parseInt(timestampAndRouteId[1]);

    routeEvent.setId(record.key());
    routeInfo.setId(record.key());

    routeEvent.setRouteId(routeId);
    routeInfo.setRouteId(routeId);

    routeEvent.setTimestamp(OffsetDateTime.parse(timestamp));
    routeInfo.setTimestamp(OffsetDateTime.parse(timestamp));

    persistentRouteEventService.saveRouteEvent(routeEvent);
    persistentRouteInfoService.saveRouteInfoIfChanged(routeInfo);
  }

}
