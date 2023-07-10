package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.SiteStatsDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteStatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentSiteStatsServiceImpl implements PersistentSiteStatsService {

  @NonNull
  private final SiteStatsDAO siteStatsDAO;


  @Override
  public SiteStats save(SiteStats event) {
    return siteStatsDAO.save(event);
  }

  @Override
  public List<SiteStats> saveAll(List<SiteStats> events) {
    return siteStatsDAO.saveAll(events);
  }
}
