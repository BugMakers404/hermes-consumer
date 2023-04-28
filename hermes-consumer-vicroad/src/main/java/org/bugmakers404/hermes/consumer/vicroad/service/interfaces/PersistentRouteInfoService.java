package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteInfo;

public interface PersistentRouteInfoService {
  RouteInfo saveRouteInfoIfChanged(RouteInfo routeInfo);

  RouteInfo getLatestRouteInfoByRouteId(Integer routeId);

  List<LinkInfo> getAllRouteInfo();
}
