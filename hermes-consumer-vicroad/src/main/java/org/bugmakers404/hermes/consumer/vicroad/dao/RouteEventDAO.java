package org.bugmakers404.hermes.consumer.vicroad.dao;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteEventDAO extends MongoRepository<RouteEvent, String> {
  List<RouteEvent> findAllByRouteId(Integer linkId);

  List<RouteEvent> findAllByTimestamp(OffsetDateTime timestamp);

  RouteEvent findByRouteIdAndTimestamp(Integer routeId, OffsetDateTime timestamp);

  RouteEvent findTopByRouteIdOrderByTimestampDesc(Integer routeId);
}
