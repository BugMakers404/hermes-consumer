package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteInfo;

public interface PersistentSiteInfoService {

  SiteInfo saveSiteInfoIfChanged(SiteInfo siteInfo);

  SiteInfo getLatestSiteInfoBySiteId(Integer siteId);

  List<SiteInfo> getAllSiteInfo();

}
