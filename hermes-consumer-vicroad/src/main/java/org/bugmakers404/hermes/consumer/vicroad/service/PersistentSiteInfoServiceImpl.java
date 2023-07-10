package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.SiteInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteInfo;
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
  public SiteInfo saveIfChanged(SiteInfo infoEvent) {
    SiteInfo latestSiteInfo = siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(
        infoEvent.getSiteId());

    if (latestSiteInfo == null || !latestSiteInfo.isSame(infoEvent)) {
      return siteInfoDAO.save(infoEvent);
    } else {
      return latestSiteInfo;
    }
  }

  @Override
  public List<SiteInfo> saveAllIfChanged(List<SiteInfo> allInfoEvents) {
    List<SiteInfo> savedSiteInfo = allInfoEvents.stream().filter(siteInfo -> {
      SiteInfo latestSiteInfo = siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(
          siteInfo.getSiteId());
      return Objects.isNull(latestSiteInfo) || !latestSiteInfo.isSame(siteInfo);
    }).collect(Collectors.toList());
    return siteInfoDAO.saveAll(savedSiteInfo);
  }
}
