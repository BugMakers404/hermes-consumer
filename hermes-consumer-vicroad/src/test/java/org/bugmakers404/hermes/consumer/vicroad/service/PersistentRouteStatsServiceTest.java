package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.bugmakers404.hermes.consumer.vicroad.dao.RouteStatsDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteStatsService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentRouteStatsServiceTest {

  @Mock
  private RouteStatsDAO routeStatsDAO;

  private PersistentRouteStatsService routeEventService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    routeEventService = new PersistentRouteStatsServiceImpl(routeStatsDAO);
  }

  @Test
  public void saveRouteEventTest() {
    RouteStats routeStats = new RouteStats();
    when(routeStatsDAO.save(any(RouteStats.class))).thenReturn(routeStats);

    RouteStats result = routeEventService.save(routeStats);

    verify(routeStatsDAO, times(1)).save(routeStats);
    assertEquals(routeStats, result);
  }
}
