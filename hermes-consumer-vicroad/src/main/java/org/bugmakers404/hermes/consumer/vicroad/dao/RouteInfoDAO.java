package org.bugmakers404.hermes.consumer.vicroad.dao;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteInfoDAO extends MongoRepository<RouteInfo, String> {

  List<RouteInfo> findAllByRouteId(Integer routeId);

  RouteInfo findTopByRouteIdOrderByTimestampDesc(Integer routeId);
}
