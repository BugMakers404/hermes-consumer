package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.SiteInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentSiteInfoServiceImpl implements PersistentSiteInfoService {

  @NonNull
  private final SiteInfoDAO siteInfoDAO;

  @Override
  public SiteInfo saveSiteInfoIfChanged(SiteInfo siteInfo) {
    SiteInfo latestSiteInfo = siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(
        siteInfo.getSiteId());

    if (latestSiteInfo == null || !latestSiteInfo.isSame(siteInfo)) {
      return siteInfoDAO.save(siteInfo);
    } else {
      return latestSiteInfo;
    }
  }

  @Override
  public SiteInfo getLatestSiteInfoBySiteId(Integer siteId) {
    return siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(siteId);
  }

  @Override
  public List<SiteInfo> getAllSiteInfo() {
    return siteInfoDAO.findAll();
  }
}