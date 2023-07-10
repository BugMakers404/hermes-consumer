package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkStatsService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteStatsService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteStatsService;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteInfoService;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.support.Acknowledgment;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KafkaConsumerServiceImplTest {

  @Mock
  private PersistentLinkStatsService linkEventService;

  @Mock
  private PersistentLinkInfoService linkInfoService;

  @Mock
  private PersistentRouteStatsService routeEventService;

  @Mock
  private PersistentRouteInfoService routeInfoService;

  @Mock
  private PersistentSiteStatsService siteEventService;

  @Mock
  private PersistentSiteInfoService siteInfoService;

  @Mock
  private FailedEventsArchiveService s3Archiver;

  @Mock
  private Acknowledgment acknowledgment;

  private KafkaConsumerServiceImpl kafkaConsumerService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    kafkaConsumerService = new KafkaConsumerServiceImpl(linkEventService, linkInfoService,
        routeEventService, routeInfoService, siteEventService, siteInfoService, s3Archiver);
  }

  @Test
  public void testPersistLinkEvents() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_LINKS, 0, 0, "1997-10-02T00:00:00+10:00_1",
        "{\"id\": 1}");
    kafkaConsumerService.persistLinkEvents(List.of(record), acknowledgment);

    verify(linkEventService, times(1)).saveAll(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistLinkWithGeoEvents() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, 0, 0, "1997-10-02T00:00:00+10:00_1",
        "{\"id\": 1}");
    kafkaConsumerService.persistLinkWithGeoEvents(List.of(record), acknowledgment);
    verify(linkInfoService, times(1)).saveAllIfChanged(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistRouteEvents() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_ROUTES, 0, 0, "1997-10-02T00:00:00+10:00_1", "{\"id\": 1}");
    kafkaConsumerService.persistRouteEvents(List.of(record), acknowledgment);
    verify(routeEventService, times(1)).saveAll(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistRouteWithGeoEvents() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_ROUTES, 0, 0, "1997-10-02T00:00:00+10:00_1", "{\"id\": 1}");
    kafkaConsumerService.persistRouteWithGeoEvents(List.of(record), acknowledgment);
    verify(routeInfoService, times(1)).saveAllIfChanged(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistSiteEvent() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_SITES, 0, 0, "1997-10-02T00:00:00+10:00_1", "{\"id\": 1}");
    kafkaConsumerService.persistSiteEvents(List.of(record), acknowledgment);
    verify(siteEventService, times(1)).saveAll(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistSiteWithGeoEvents() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_SITES, 0, 0, "1997-10-02T00:00:00+10:00_1", "{\"id\": 1}");
    kafkaConsumerService.persistSiteWithGeoEvents(List.of(record), acknowledgment);
    verify(siteInfoService, times(1)).saveAllIfChanged(any());
    verify(acknowledgment, times(1)).acknowledge();
  }
}
