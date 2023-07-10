package org.bugmakers404.hermes.consumer.vicroad.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteStats;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkStatsService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteStatsService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteStatsService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl {

  private final PersistentLinkStatsService linkEventService;

  private final PersistentLinkInfoService linkInfoService;

  private final PersistentRouteStatsService routeEventService;

  private final PersistentRouteInfoService routeInfoService;

  private final PersistentSiteStatsService siteEventService;

  private final PersistentSiteInfoService siteInfoService;

  private final FailedEventsArchiveService s3Archiver;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_LINKS}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_LINKS, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistLinkEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<LinkStats> linkStatsBatch = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      try {
        LinkStats linkStats = objectMapper.readValue(record.value(), LinkStats.class);
        linkStatsBatch.add(linkStats);
      } catch (Exception e) {
        log.error("{} - Failed to deserialize the event with key {}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_LINKS, record.key(), e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS, record.key(),
            record.value());
      }
    }

    linkEventService.saveAll(linkStatsBatch);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistLinkWithGeoEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<LinkInfo> linkInfoBatch = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      try {
        LinkInfo linkInfo = objectMapper.readValue(record.value(), LinkInfo.class);
        linkInfoBatch.add(linkInfo);
      } catch (Exception e) {
        log.error("{} - Failed to deserialize the event with key {}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, record.key(), e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, record.key(),
            record.value());
      }
    }

    linkInfoService.saveAllIfChanged(linkInfoBatch);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_ROUTES}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_ROUTES, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistRouteEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<RouteStats> routeStatsBatch = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      try {
        RouteStats routeStats = objectMapper.readValue(record.value(), RouteStats.class);
        routeStatsBatch.add(routeStats);
      } catch (Exception e) {
        log.error("{} - Failed to deserialize the event with key {}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_ROUTES, record.key(), e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_ROUTES, record.key(),
            record.value());
      }
    }

    routeEventService.saveAll(routeStatsBatch);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistRouteWithGeoEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<RouteInfo> routeInfoBatch = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      try {
        RouteInfo routeInfo = objectMapper.readValue(record.value(), RouteInfo.class);
        routeInfoBatch.add(routeInfo);
      } catch (Exception e) {
        log.error("{} - Failed to deserialize the event with key {}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_ROUTES, record.key(), e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO, record.key(),
            record.value());
      }
    }

    routeInfoService.saveAllIfChanged(routeInfoBatch);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_SITES}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_SITES, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistSiteEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<SiteStats> siteStatsBatch = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {
      try {
        SiteStats siteStats = objectMapper.readValue(record.value(), SiteStats.class);
        siteStatsBatch.add(siteStats);
      } catch (Exception e) {
        log.error("{} - Failed to persist the event with key {}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_SITES, record.key(), e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_SITES, record.key(),
            record.value());
      }
    }

    siteEventService.saveAll(siteStatsBatch);
    ack.acknowledge();
  }

  @KafkaListener(topics = {
      Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO}, clientIdPrefix = Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO, concurrency = Constants.KAFKA_PARTITION_COUNT, batch = "true")
  public void persistSiteWithGeoEvents(@NonNull List<ConsumerRecord<String, String>> records,
      Acknowledgment ack) {

    List<SiteInfo> siteInfoBatch = new ArrayList<>(records.size());

    for (ConsumerRecord<String, String> record : records) {

      try {
        SiteInfo siteInfo = objectMapper.readValue(record.value(), SiteInfo.class);
        siteInfoBatch.add(siteInfo);
      } catch (Exception e) {
        log.error("{} - Failed to persist the event with key {}: {}",
            Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO, record.key(), e.getMessage(), e);
        s3Archiver.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO, record.key(),
            record.value());
      }
    }

    siteInfoService.saveAllIfChanged(siteInfoBatch);
    ack.acknowledge();
  }

}
