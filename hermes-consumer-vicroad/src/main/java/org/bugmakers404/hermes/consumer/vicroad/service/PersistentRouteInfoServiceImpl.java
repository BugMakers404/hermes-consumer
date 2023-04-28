package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.RouteInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.entities.routes.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentRouteInfoServiceImpl implements PersistentRouteInfoService {

  @NonNull
  private final RouteInfoDAO routeInfoDAO;

  @Override
  public RouteInfo saveRouteInfoIfChanged(RouteInfo routeInfo) {
    RouteInfo latestRouteInfo = routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(
        routeInfo.getRouteId());

    if (latestRouteInfo == null || !latestRouteInfo.isSame(routeInfo)) {
      return routeInfoDAO.save(routeInfo);
    } else {
      return latestRouteInfo;
    }
  }

  @Override
  public RouteInfo getLatestRouteInfoByRouteId(Integer routeId) {
    return routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(routeId);
  }

  @Override
  public List<LinkInfo> getAllRouteInfo() {
    return null;
  }
}
