package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.LinkStatsDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkStatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkStatsServiceImpl implements PersistentLinkStatsService {

  @NonNull
  private final LinkStatsDAO linkStatsDAO;

  @Override
  public LinkStats save(LinkStats event) {
    return linkStatsDAO.save(event);
  }

  @Override
  public List<LinkStats> saveAll(List<LinkStats> events) {
    return linkStatsDAO.saveAll(events);
  }

}
