package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteEvent;

public interface PersistentRouteEventService {
  RouteEvent saveLinkEvent(RouteEvent routeEvent);

  List<RouteEvent> getAllRouteEvents();

  List<RouteEvent> getAllLinkEventsByRouteId(Integer routeId);

  RouteEvent getLatestLinkEventByRouteId(Integer routeId);

  List<RouteEvent> getAllRouteEventsByTimestamp(OffsetDateTime timestamp);

  RouteEvent getRouteEventByLinkIdAndTimestamp(Integer routeId, OffsetDateTime timestamp);
}
