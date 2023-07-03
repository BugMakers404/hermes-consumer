package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.RouteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteEvent;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentRouteEventServiceImpl implements PersistentRouteEventService {

  @NonNull
  private final RouteEventDAO routeEventDAO;

  @Override
  public RouteEvent save(RouteEvent event) {
    return routeEventDAO.save(event);
  }

  @Override
  public List<RouteEvent> saveAll(List<RouteEvent> events) {
    return routeEventDAO.saveAll(events);
  }
}
