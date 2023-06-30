package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.bugmakers404.hermes.consumer.vicroad.dao.RouteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteEvent;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteEventService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentRouteEventServiceTest {

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
    RouteEvent routeEvent = new RouteEvent();
    when(routeEventDAO.save(any(RouteEvent.class))).thenReturn(routeEvent);

    RouteEvent result = routeEventService.saveRouteEvent(routeEvent);

    verify(routeEventDAO, times(1)).save(routeEvent);
    assertEquals(routeEvent, result);
  }
}
