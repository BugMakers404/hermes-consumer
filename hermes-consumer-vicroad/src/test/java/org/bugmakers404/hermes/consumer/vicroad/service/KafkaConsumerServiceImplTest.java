package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.support.Acknowledgment;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KafkaConsumerServiceImplTest {

  @Mock
  private PersistentLinkEventService linkEventService;

  @Mock
  private PersistentLinkInfoService linkInfoService;

  @Mock
  private PersistentRouteEventService routeEventService;

  @Mock
  private PersistentRouteInfoService routeInfoService;

  @Mock
  private PersistentSiteEventService siteEventService;

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
  public void testPersistLinkEvent() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_LINKS, 0, 0, "1997-10-02T00:00:00+10:00_1",
        "{\"id\": 1}");
    kafkaConsumerService.persistLinkEvent(List.of(record), acknowledgment);

    verify(linkEventService, times(1)).saveAll(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistLinkWithGeoEvent() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, 0, 0, "1997-10-02T00:00:00+10:00_1",
        "{\"id\": 1}");
    kafkaConsumerService.persistLinkWithGeoEvent(List.of(record), acknowledgment);
    verify(linkInfoService, times(1)).saveAllIfChanged(any());
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistRouteEvent() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_ROUTES, 0, 0, "1997-10-02T00:00:00+10:00_1", "{\"id\": 1}");
    kafkaConsumerService.persistRouteEvent(record, acknowledgment);
    verify(routeEventService, times(1)).saveRouteEvent(any(RouteEvent.class));
    verify(routeInfoService, times(1)).saveRouteInfoIfChanged(any(RouteInfo.class));
    verify(acknowledgment, times(1)).acknowledge();
  }

  @Test
  public void testPersistSiteEvent() {
    ConsumerRecord<String, String> record = new ConsumerRecord<>(
        Constants.BLUETOOTH_DATA_TOPIC_SITES, 0, 0, "1997-10-02T00:00:00+10:00_1", "{\"id\": 1}");
    kafkaConsumerService.persistSiteEvent(record, acknowledgment);
    verify(siteEventService, times(1)).saveSiteEvent(any(SiteEvent.class));
    verify(siteInfoService, times(1)).saveSiteInfoIfChanged(any(SiteInfo.class));
    verify(acknowledgment, times(1)).acknowledge();
  }
}
