package org.bugmakers404.hermes.consumer.vicroad.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteEvent;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteEvent;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.utils.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl {

  private final PersistentLinkEventService linkEventService;

  private final PersistentLinkInfoService linkInfoService;

  private final PersistentRouteEventService routeEventService;

  private final PersistentRouteInfoService routeInfoService;

  private final PersistentSiteEventService siteEventService;

  private final PersistentSiteInfoService siteInfoService;

  private final FailedEventsArchiveService s3Archiver;

  private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

  @KafkaListener(topics = {Constants.BLUETOOTH_DATA_TOPIC_LINKS},
      clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_LINKS,
      concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistLinkEvent(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {
    List<LinkEvent> linkEvents = new ArrayList<>();
    Map<String, String> failedRecords = new HashMap<>();

    for (ConsumerRecord<String, String> record : records) {
      String[] timestampAndLinkId = record.key().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndLinkId[0]);
      Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

      try {
        LinkEvent linkEvent = objectMapper.readValue(record.value(), LinkEvent.class);
        linkEvent.setId(null);
        linkEvent.setLinkId(linkId);
        linkEvent.setTimestamp(timestamp);
        linkEvents.add(linkEvent);
      } catch (Exception e) {
        failedRecords.put(record.key(), record.value());
        log.error("{} - Failed to persist the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_LINKS, timestamp, linkId, e.getMessage(), e);
      }
    }

    linkEventService.saveAll(linkEvents);

    // Archive all failed records
    for (Map.Entry<String, String> failedRecord : failedRecords.entrySet()) {
      String[] timestampAndLinkId = failedRecord.getKey().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndLinkId[0]);
      Integer linkId = Integer.parseInt(timestampAndLinkId[1]);
      s3Archiver.archiveFailedLinkEvents(timestamp, linkId, failedRecord.getValue());
    }

    // Acknowledge the batch here
    ack.acknowledge();

  }

  @KafkaListener(topics = {Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO},
      clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO,
      concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistLinkWithGeoEvent(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<LinkInfo> linkInfos = new ArrayList<>();
    Map<String, String> failedRecords = new HashMap<>();

    for (ConsumerRecord<String, String> record : records) {
      String[] timestampAndLinkId = record.key().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndLinkId[0]);
      Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

      try {
        LinkInfo linkInfo = objectMapper.readValue(record.value(), LinkInfo.class);
        linkInfo.setId(null);
        linkInfo.setLinkId(linkId);
        linkInfo.setTimestamp(timestamp);
        linkInfos.add(linkInfo);
      } catch (Exception e) {
        failedRecords.put(record.key(), record.value());
        log.error("{} - Failed to persist the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, timestamp, linkId, e.getMessage(), e);
      }
    }

    linkInfoService.saveAllIfChanged(linkInfos);

    // Archive all failed records
    for (Map.Entry<String, String> failedRecord : failedRecords.entrySet()) {
      String[] timestampAndLinkId = failedRecord.getKey().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndLinkId[0]);
      Integer linkId = Integer.parseInt(timestampAndLinkId[1]);
      s3Archiver.archiveFailedLinkWithGeoEvents(timestamp, linkId, failedRecord.getValue());
    }

    // Acknowledge the batch here
    ack.acknowledge();
  }

  @KafkaListener(topics = {Constants.BLUETOOTH_DATA_TOPIC_ROUTES},
      clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_ROUTES,
      concurrency = Constants.KAFKA_PARTITION_COUNT)
  public void persistRouteEvent(@NonNull ConsumerRecord<String, String> record,
      Acknowledgment ack) {

    String[] timestampAndRouteId = record.key().split("_");
    OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndRouteId[0]);
    Integer routeId = Integer.parseInt(timestampAndRouteId[1]);

    try {

      RouteEvent routeEvent = objectMapper.readValue(record.value(), RouteEvent.class);
      routeEvent.setId(null);
      routeEvent.setRouteId(routeId);
      routeEvent.setTimestamp(timestamp);
      routeEventService.saveRouteEvent(routeEvent);

      RouteInfo routeInfo = objectMapper.readValue(record.value(), RouteInfo.class);
      routeInfo.setId(null);
      routeInfo.setRouteId(routeId);
      routeInfo.setTimestamp(timestamp);
      routeInfoService.saveRouteInfoIfChanged(routeInfo);
      ack.acknowledge();

    } catch (Exception e) {

      log.error("{} - Failed to persist the event with key {}-{}: {}",
          Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId, e.getMessage(), e);
      s3Archiver.archiveFailedRouteEvents(timestamp, routeId, record.value());

    }

  }

  @KafkaListener(topics = {Constants.BLUETOOTH_DATA_TOPIC_SITES},
      clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_SITES,
      concurrency = Constants.KAFKA_PARTITION_COUNT)
  public void persistSiteEvent(@NonNull ConsumerRecord<String, String> record, Acknowledgment ack) {

    String[] timestampAndRouteId = record.key().split("_");
    OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndRouteId[0]);
    Integer siteId = Integer.parseInt(timestampAndRouteId[1]);

    try {

      SiteEvent siteEvent = objectMapper.readValue(record.value(), SiteEvent.class);
      siteEvent.setId(null);
      siteEvent.setSiteId(siteId);
      siteEvent.setTimestamp(timestamp);
      siteEventService.saveSiteEvent(siteEvent);

      SiteInfo siteInfo = objectMapper.readValue(record.value(), SiteInfo.class);
      siteInfo.setId(null);
      siteInfo.setSiteId(siteId);
      siteInfo.setTimestamp(timestamp);
      siteInfoService.saveSiteInfoIfChanged(siteInfo);

      ack.acknowledge();

    } catch (Exception e) {

      log.error("{} - Failed to persist the event with key {}-{}: {}",
          Constants.BLUETOOTH_DATA_TOPIC_SITES, timestamp, siteId, e.getMessage(), e);
      s3Archiver.archiveFailedSiteEvents(timestamp, siteId, record.value());

    }
  }

}
