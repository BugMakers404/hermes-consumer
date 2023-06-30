package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import org.bugmakers404.hermes.consumer.vicroad.entity.sites.SiteInfo;

public interface PersistentSiteInfoService {

  SiteInfo saveSiteInfoIfChanged(SiteInfo siteInfo);

}
