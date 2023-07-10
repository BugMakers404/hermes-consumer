package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.RouteStatsDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteStatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentRouteStatsServiceImpl implements PersistentRouteStatsService {

  @NonNull
  private final RouteStatsDAO routeStatsDAO;

  @Override
  public RouteStats save(RouteStats event) {
    return routeStatsDAO.save(event);
  }

  @Override
  public List<RouteStats> saveAll(List<RouteStats> events) {
    return routeStatsDAO.saveAll(events);
  }
}
