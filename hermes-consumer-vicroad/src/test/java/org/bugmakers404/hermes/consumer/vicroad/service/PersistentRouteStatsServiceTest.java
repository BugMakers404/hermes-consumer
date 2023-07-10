package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.bugmakers404.hermes.consumer.vicroad.dao.RouteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.RouteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteEventService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentRouteStatsServiceTest {

  @Mock
  private RouteEventDAO routeEventDAO;

  private PersistentRouteEventService routeEventService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    routeEventService = new PersistentRouteEventServiceImpl(routeEventDAO);
  }

  @Test
  public void saveRouteEventTest() {
    RouteStats routeStats = new RouteStats();
    when(routeEventDAO.save(any(RouteStats.class))).thenReturn(routeStats);

    RouteStats result = routeEventService.save(routeStats);

    verify(routeEventDAO, times(1)).save(routeStats);
    assertEquals(routeStats, result);
  }
}
