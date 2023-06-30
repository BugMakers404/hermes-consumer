package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteEvent;

public interface PersistentRouteEventService {

  RouteEvent saveRouteEvent(RouteEvent routeEvent);

}
