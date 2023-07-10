package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.RouteInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteInfo;
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
  public RouteInfo saveIfChanged(RouteInfo infoEvent) {
    RouteInfo latestRouteInfo = routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(
        infoEvent.getRouteId());

    if (latestRouteInfo == null || !latestRouteInfo.isSame(infoEvent)) {
      return routeInfoDAO.save(infoEvent);
    } else {
      return latestRouteInfo;
    }
  }

  @Override
  public List<RouteInfo> saveAllIfChanged(List<RouteInfo> allInfoEvents) {
    List<RouteInfo> savedRouteInfo = allInfoEvents.stream().filter(routeInfo -> {
      RouteInfo latestRouteInfo = routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(
          routeInfo.getRouteId());
      return Objects.isNull(latestRouteInfo) || !latestRouteInfo.isSame(routeInfo);
    }).collect(Collectors.toList());
    return routeInfoDAO.saveAll(savedRouteInfo);
  }
}
