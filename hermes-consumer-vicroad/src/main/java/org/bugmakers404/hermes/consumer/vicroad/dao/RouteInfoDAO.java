package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entity.RouteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteInfoDAO extends MongoRepository<RouteInfo, String> {

  RouteInfo findTopByRouteIdOrderByTimestampDesc(Integer routeId);

}
