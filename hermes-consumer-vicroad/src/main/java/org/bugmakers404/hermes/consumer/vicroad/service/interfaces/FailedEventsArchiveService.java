package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.time.OffsetDateTime;

public interface FailedEventsArchiveService {

  void archiveFailedLinkEvents(OffsetDateTime timestamp, Integer linkId, String linkEvent);

  void archiveFailedLinkWithGeoEvents(OffsetDateTime timestamp, Integer linkId,
      String linkWithGeoEvent);

  void archiveFailedRouteEvents(OffsetDateTime timestamp, Integer routeId, String routeEvent);

  void archiveFailedSiteEvents(OffsetDateTime timestamp, Integer siteId, String siteEvent);

}
