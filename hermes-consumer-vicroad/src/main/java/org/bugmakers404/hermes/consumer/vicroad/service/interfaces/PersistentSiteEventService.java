package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import org.bugmakers404.hermes.consumer.vicroad.entity.sites.SiteEvent;

public interface PersistentSiteEventService {

  SiteEvent saveSiteEvent(SiteEvent siteEvent);

}
