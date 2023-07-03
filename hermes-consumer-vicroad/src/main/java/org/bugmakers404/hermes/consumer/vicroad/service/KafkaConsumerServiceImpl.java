package org.bugmakers404.hermes.consumer.vicroad.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.entity.links.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.entity.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteEvent;
import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.sites.SiteEvent;
import org.bugmakers404.hermes.consumer.vicroad.entity.sites.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteEventService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
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

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_LINKS}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_LINKS, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistLinkEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<LinkEvent> linkEvents = new ArrayList<>();

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
        log.error("{} - Failed to deserialize the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_LINKS, timestamp, linkId, e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS, timestamp, linkId,
            record.value());
      }
    }

    linkEventService.saveAll(linkEvents);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistLinkWithGeoEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<LinkInfo> linkInfos = new ArrayList<>(records.size());

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
        log.error("{} - Failed to deserialize the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, timestamp, linkId, e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, timestamp,
            linkId, record.value());
      }
    }

    linkInfoService.saveAllIfChanged(linkInfos);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_ROUTES}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_ROUTES, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistRouteEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<RouteEvent> routeEvents = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      String[] timestampAndRouteId = record.key().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndRouteId[0]);
      Integer routeId = Integer.parseInt(timestampAndRouteId[1]);

      try {
        RouteEvent routeEvent = objectMapper.readValue(record.value(), RouteEvent.class);
        routeEvent.setId(null);
        routeEvent.setRouteId(routeId);
        routeEvent.setTimestamp(timestamp);
        routeEvents.add(routeEvent);
      } catch (Exception e) {
        log.error("{} - Failed to deserialize the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId, e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId,
            record.value());
      }
    }

    routeEventService.saveAll(routeEvents);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistRouteWithGeoEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<RouteInfo> routeInfos = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      String[] timestampAndRouteId = record.key().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndRouteId[0]);
      Integer routeId = Integer.parseInt(timestampAndRouteId[1]);

      try {
        RouteInfo routeInfo = objectMapper.readValue(record.value(), RouteInfo.class);
        routeInfo.setId(null);
        routeInfo.setRouteId(routeId);
        routeInfo.setTimestamp(timestamp);
        routeInfos.add(routeInfo);
      } catch (Exception e) {
        log.error("{} - Failed to deserialize the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId, e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO, timestamp,
            routeId, record.value());
      }
    }

    routeInfoService.saveAllIfChanged(routeInfos);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_SITES}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_SITES, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistSiteEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<SiteEvent> siteEvents = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      String[] timestampAndRouteId = record.key().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndRouteId[0]);
      Integer siteId = Integer.parseInt(timestampAndRouteId[1]);

      try {
        SiteEvent siteEvent = objectMapper.readValue(record.value(), SiteEvent.class);
        siteEvent.setId(null);
        siteEvent.setSiteId(siteId);
        siteEvent.setTimestamp(timestamp);
        siteEvents.add(siteEvent);
      } catch (Exception e) {
        log.error("{} - Failed to persist the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_SITES, timestamp, siteId, e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_SITES, timestamp, siteId,
            record.value());
      }
    }

    siteEventService.saveAll(siteEvents);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistSiteWithGeoEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<SiteInfo> siteInfos = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      String[] timestampAndRouteId = record.key().split("_");
      OffsetDateTime timestamp = OffsetDateTime.parse(timestampAndRouteId[0]);
      Integer siteId = Integer.parseInt(timestampAndRouteId[1]);

      try {
        SiteInfo siteInfo = objectMapper.readValue(record.value(), SiteInfo.class);
        siteInfo.setId(null);
        siteInfo.setSiteId(siteId);
        siteInfo.setTimestamp(timestamp);
        siteInfos.add(siteInfo);
      } catch (Exception e) {
        log.error("{} - Failed to persist the event with key {}-{}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO, timestamp, siteId, e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO, timestamp,
            siteId, record.value());
      }
    }

    siteInfoService.saveAllIfChanged(siteInfos);
    ack.acknowledge();
  }

}
