package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteEvent;

public interface PersistentSiteEventService {

  SiteEvent saveSiteEvent(SiteEvent siteEvent);

  List<SiteEvent> getAllSiteEvents();

  List<SiteEvent> getAllSiteEventsBySiteId(Integer siteId);

  SiteEvent getLatestSiteEventBySiteId(Integer siteId);

  List<SiteEvent> getAllSiteEventsByTimestamp(OffsetDateTime timestamp);

  SiteEvent getSiteEventBySiteIdAndTimestamp(Integer siteId, OffsetDateTime timestamp);

}
