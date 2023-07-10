package org.bugmakers404.hermes.consumer.vicroad.entitiy;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteStats;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

public class DeserializerTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void linkInfoDeserializerTest() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("events/linkWithGeoEvent.json");
    LinkInfo convertedLinkInfo = objectMapper.readValue(classPathResource.getInputStream(),
        LinkInfo.class);

    assertEquals(convertedLinkInfo.getLinkId(), 3);
    assertEquals(convertedLinkInfo.getTimestamp(),
        OffsetDateTime.parse("2023-03-03T23:48:30+11:00", Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    assertEquals(convertedLinkInfo.getName(), "Bulleen Rd, Eastern Fwy to Manningham Rd");
    assertEquals(convertedLinkInfo.getOriginId(), 2827);
    assertEquals(convertedLinkInfo.getDestinationId(), 686);
    assertEquals(convertedLinkInfo.getLength(), 2000);
    assertEquals(convertedLinkInfo.getMinNumberOfLanes(), 1);
    assertFalse(convertedLinkInfo.getFreeway());
    assertTrue(convertedLinkInfo.getEnabled());
    assertFalse(convertedLinkInfo.getDraft());
    assertEquals(convertedLinkInfo.getCoordinates().size(), 21);
  }

  @Test
  public void linkStatsDeserializerTest() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("events/linkEvent.json");
    LinkStats convertedLinkStats = objectMapper.readValue(classPathResource.getInputStream(),
        LinkStats.class);
    assertEquals(convertedLinkStats.getLinkId(), 3);
    assertEquals(convertedLinkStats.getTimestamp(),
        OffsetDateTime.parse("2023-03-03T11:37:00+11:00", Constants.DATE_TIME_FORMATTER_IN_EVENTS));

    assertEquals(convertedLinkStats.getDelay(), 47);
    assertEquals(convertedLinkStats.getSpeed(), 37);
    assertEquals(convertedLinkStats.getExcessDelay(), 18);
    assertEquals(convertedLinkStats.getCongestion(), 1);
    assertEquals(convertedLinkStats.getScore(), 0);
    assertEquals(convertedLinkStats.getFlowRestrictionScore(), 0);
    assertEquals(convertedLinkStats.getAverageDensity(), 76);
    assertEquals(convertedLinkStats.getDensity(), 89);
    assertTrue(convertedLinkStats.getEnoughData());
    assertFalse(convertedLinkStats.getIgnored());
    assertFalse(convertedLinkStats.getClosed());
  }

  @Test
  public void routeInfoDeserializerTest() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("events/routeEvent.json");
    RouteInfo convertedRouteInfo = objectMapper.readValue(classPathResource.getInputStream(),
        RouteInfo.class);

    assertEquals(convertedRouteInfo.getRouteId(), 2);
    assertEquals(convertedRouteInfo.getTimestamp(),
        OffsetDateTime.parse("2023-03-03T23:49:00+11:00", Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    assertEquals(convertedRouteInfo.getName(), "Greensborough Hwy (SB), M80 to Eastern Fwy");
    assertEquals(convertedRouteInfo.getPrimaryRoadName(),
        "Greensborough Hwy - Rosanna Rd - Bulleen Rd (SB)");
    assertEquals(convertedRouteInfo.getStartEndDescription(), "Western Ring Rd to Eastern Fwy");
    assertTrue(convertedRouteInfo.getEnabled());
    assertFalse(convertedRouteInfo.getDraft());
    assertEquals(convertedRouteInfo.getLength(), 11110);
    assertEquals(convertedRouteInfo.getLinks().size(), 10);
  }

  @Test
  public void routeStatsDeserializerTest() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("events/routeEvent.json");
    RouteStats convertedRouteStats = objectMapper.readValue(classPathResource.getInputStream(),
        RouteStats.class);

    assertEquals(convertedRouteStats.getRouteId(), 2);
    assertEquals(convertedRouteStats.getTimestamp(),
        OffsetDateTime.parse("2023-03-03T23:49:00+11:00", Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    assertEquals(convertedRouteStats.getTravelTime(), 938);
    assertEquals(convertedRouteStats.getDelay(), 77);
    assertEquals(convertedRouteStats.getSpeed(), 42);
    assertEquals(convertedRouteStats.getExcessDelay(), -1);
    assertEquals(convertedRouteStats.getDataStatus(), "insufficient_live");
  }

  @Test
  public void SiteInfoDeserializerTest() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("events/siteEvent.json");
    SiteInfo convertedSiteInfo = objectMapper.readValue(classPathResource.getInputStream(),
        SiteInfo.class);

    assertEquals(convertedSiteInfo.getSiteId(), 13);
    assertEquals(convertedSiteInfo.getTimestamp(),
        OffsetDateTime.parse("2023-03-03T23:40:00+11:00", Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    assertEquals(convertedSiteInfo.getName(), "SAGE - 2815. HUME FWY/COOPER");
    assertTrue(convertedSiteInfo.getEnabled());
    assertFalse(convertedSiteInfo.getDraft());
    assertEquals(convertedSiteInfo.getLocation(), List.of(145.22583, -37.9576073));
  }

  @Test
  public void SiteStatsDeserializerTest() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("events/siteEvent.json");
    SiteStats convertedSiteStats = objectMapper.readValue(classPathResource.getInputStream(),
        SiteStats.class);

    assertEquals(convertedSiteStats.getSiteId(), 13);
    assertEquals(convertedSiteStats.getTimestamp(),
        OffsetDateTime.parse("2023-03-03T23:40:00+11:00", Constants.DATE_TIME_FORMATTER_IN_EVENTS));
    assertEquals(convertedSiteStats.getProbeCount(), 0);
    assertEquals(convertedSiteStats.getRawProbeCount(), 0);
    assertEquals(convertedSiteStats.getEstimatedEventCount(), 0);
    assertEquals(convertedSiteStats.getAverageDuration(), 0);
  }

}
