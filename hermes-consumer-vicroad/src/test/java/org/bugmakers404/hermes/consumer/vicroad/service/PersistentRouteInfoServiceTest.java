package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.dao.RouteInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentRouteInfoService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentRouteInfoServiceTest {

  @Mock
  private RouteInfoDAO routeInfoDAO;

  private PersistentRouteInfoService routeInfoService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    routeInfoService = new PersistentRouteInfoServiceImpl(routeInfoDAO);
  }

  @Test
  public void saveNewRouteInfoTest() {
    RouteInfo newRouteInfo = new RouteInfo();
    when(routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(anyInt())).thenReturn(null);
    when(routeInfoDAO.save(any(RouteInfo.class))).thenReturn(newRouteInfo);

    RouteInfo saveResult = routeInfoService.saveRouteInfoIfChanged(newRouteInfo);

    verify(routeInfoDAO, times(1)).save(newRouteInfo);
    assertEquals(newRouteInfo, saveResult);
  }

  @Test
  public void saveChangedRouteInfoTest() {
    RouteInfo routeInfoInDB = new RouteInfo();
    routeInfoInDB.setName("hello world");
    routeInfoInDB.setPrimaryRoadName("hello world");
    routeInfoInDB.setStartEndDescription("hello world");
    routeInfoInDB.setLength(1);
    routeInfoInDB.setLinks(List.of(1, 2, 3));

    RouteInfo newRouteInfo = new RouteInfo();
    newRouteInfo.setName("goodbye world");
    newRouteInfo.setPrimaryRoadName("goodbye world");
    newRouteInfo.setStartEndDescription("goodbye world");
    newRouteInfo.setLength(1);
    newRouteInfo.setLinks(List.of(1, 2, 3));

    when(routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(any())).thenReturn(routeInfoInDB);
    when(routeInfoDAO.save(newRouteInfo)).thenReturn(newRouteInfo);
    RouteInfo saveResult = routeInfoService.saveRouteInfoIfChanged(newRouteInfo);

    verify(routeInfoDAO, times(1)).save(newRouteInfo);
    assertNotEquals(routeInfoInDB, saveResult);
    assertEquals(newRouteInfo, saveResult);

  }

  @Test
  public void saveUnchangedRouteInfoTest() {
    RouteInfo routeInfoInDB = new RouteInfo();
    routeInfoInDB.setName("hello world");
    routeInfoInDB.setPrimaryRoadName("hello world");
    routeInfoInDB.setStartEndDescription("hello world");
    routeInfoInDB.setLength(1);
    routeInfoInDB.setLinks(List.of(1, 2, 3));

    RouteInfo sameRouteInfo = new RouteInfo();
    sameRouteInfo.setName("hello world");
    sameRouteInfo.setPrimaryRoadName("hello world");
    sameRouteInfo.setStartEndDescription("hello world");
    sameRouteInfo.setLength(1);
    sameRouteInfo.setLinks(List.of(1, 2, 3));

    when(routeInfoDAO.findTopByRouteIdOrderByTimestampDesc(any())).thenReturn(routeInfoInDB);
    RouteInfo saveResult = routeInfoService.saveRouteInfoIfChanged(sameRouteInfo);

    verify(routeInfoDAO, times(0)).save(sameRouteInfo);
    assertEquals(routeInfoInDB, saveResult);
  }
}
