package org.bugmakers404.hermes.consumer.vicroad.service;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.RouteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteEvent;
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
  public RouteEvent saveLinkEvent(RouteEvent routeEvent) {
    return routeEventDAO.save(routeEvent);
  }

  @Override
  public List<RouteEvent> getAllRouteEvents() {
    return routeEventDAO.findAll();
  }

  @Override
  public List<RouteEvent> getAllLinkEventsByRouteId(Integer routeId) {
    return routeEventDAO.findAllByRouteId(routeId);
  }

  @Override
  public RouteEvent getLatestLinkEventByRouteId(Integer routeId) {
    return routeEventDAO.findTopByRouteIdOrderByTimestampDesc(routeId);
  }

  @Override
  public List<RouteEvent> getAllRouteEventsByTimestamp(OffsetDateTime timestamp) {
    return routeEventDAO.findAllByTimestamp(timestamp);
  }

  @Override
  public RouteEvent getRouteEventByLinkIdAndTimestamp(Integer routeId, OffsetDateTime timestamp) {
    return routeEventDAO.findByRouteIdAndTimestamp(routeId, timestamp);
  }
}
