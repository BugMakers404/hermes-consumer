package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.SiteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.sites.SiteEvent;
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
  public SiteEvent save(SiteEvent event) {
    return siteEventDAO.save(event);
  }

  @Override
  public List<SiteEvent> saveAll(List<SiteEvent> events) {
    return siteEventDAO.saveAll(events);
  }
}
