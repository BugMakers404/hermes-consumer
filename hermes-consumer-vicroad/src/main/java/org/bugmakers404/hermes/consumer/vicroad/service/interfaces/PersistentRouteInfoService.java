package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteInfo;

public interface PersistentRouteInfoService {

  RouteInfo saveRouteInfoIfChanged(RouteInfo routeInfo);

}
