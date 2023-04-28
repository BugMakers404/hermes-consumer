package org.bugmakers404.hermes.consumer.vicroad.service;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.SiteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteEvent;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentSiteEventServiceImpl implements PersistentSiteEventService {

  @NonNull
  private final SiteEventDAO siteEventDAO;

  @Override
  public SiteEvent saveSiteEvent(SiteEvent siteEvent) {
    return siteEventDAO.save(siteEvent);
  }

  @Override
  public List<SiteEvent> getAllSiteEvents() {
    return siteEventDAO.findAll();
  }

  @Override
  public List<SiteEvent> getAllSiteEventsBySiteId(Integer siteId) {
    return siteEventDAO.findAllBySiteId(siteId);
  }

  @Override
  public SiteEvent getLatestSiteEventBySiteId(Integer siteId) {
    return siteEventDAO.findTopBySiteIdOrderByTimestampDesc(siteId);
  }

  @Override
  public List<SiteEvent> getAllSiteEventsByTimestamp(OffsetDateTime timestamp) {
    return siteEventDAO.findAllByTimestamp(timestamp);
  }

  @Override
  public SiteEvent getSiteEventBySiteIdAndTimestamp(Integer siteId, OffsetDateTime timestamp) {
    return siteEventDAO.findBySiteIdAndTimestamp(siteId, timestamp);
  }
}
