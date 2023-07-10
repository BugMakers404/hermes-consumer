package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.LinkEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkEventServiceImpl implements PersistentLinkEventService {

  @NonNull
  private final LinkEventDAO linkEventDAO;

  @Override
  public LinkStats save(LinkStats event) {
    return linkEventDAO.save(event);
  }

  @Override
  public List<LinkStats> saveAll(List<LinkStats> events) {
    return linkEventDAO.saveAll(events);
  }

}
