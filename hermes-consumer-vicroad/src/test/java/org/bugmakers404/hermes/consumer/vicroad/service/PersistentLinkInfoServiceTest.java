package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.dao.LinkInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentLinkInfoServiceTest {

  @Mock
  private LinkInfoDAO linkInfoDAO;

  private PersistentLinkInfoService linkInfoService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    linkInfoService = new PersistentLinkInfoServiceImpl(linkInfoDAO);
  }

  @Test
  public void saveNewLinkInfoTest() {
    LinkInfo newLinkInfo = new LinkInfo();
    when(linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(anyInt())).thenReturn(null);
    when(linkInfoDAO.save(any(LinkInfo.class))).thenReturn(newLinkInfo);

    LinkInfo saveResult = linkInfoService.saveIfChanged(newLinkInfo);

    verify(linkInfoDAO, times(1)).save(newLinkInfo);
    assertEquals(newLinkInfo, saveResult);
  }

  @Test
  public void testSaveAllEvents() {
    LinkInfo newLinkInfo1 = new LinkInfo();  // You should set any necessary properties on the events
    LinkInfo newLinkInfo2 = new LinkInfo();
    List<LinkInfo> events = List.of(newLinkInfo1, newLinkInfo2);
    when(linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(newLinkInfo1.getLinkId())).thenReturn(
        null);
    when(linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(newLinkInfo2.getLinkId())).thenReturn(
        null);
    when(linkInfoDAO.saveAll(events)).thenReturn(events);

    List<LinkInfo> saveResult = linkInfoService.saveAllIfChanged(events);

    verify(linkInfoDAO, times(1)).saveAll(events);
    assertEquals(events, saveResult);
  }

  @Test
  public void saveChangedLinkInfoTest() {
    LinkInfo linkInfoInDB = new LinkInfo();
    linkInfoInDB.setName("hello world");
    linkInfoInDB.setOriginId(1);
    linkInfoInDB.setDestinationId(2);
    linkInfoInDB.setLength(3);
    linkInfoInDB.setMinNumberOfLanes(4);
    linkInfoInDB.setMinTravelTime(5);
    linkInfoInDB.setIsFreeway(true);
    linkInfoInDB.setDirection("NB");
    linkInfoInDB.setCoordinates(List.of(List.of(1d, 2d), List.of(3d, 4d)));

    LinkInfo newLinkInfo = new LinkInfo();
    newLinkInfo.setName("goodbye world");
    newLinkInfo.setOriginId(1);
    newLinkInfo.setDestinationId(2);
    newLinkInfo.setLength(3);
    newLinkInfo.setMinNumberOfLanes(4);
    newLinkInfo.setMinTravelTime(5);
    newLinkInfo.setIsFreeway(true);
    newLinkInfo.setDirection("NB");
    newLinkInfo.setCoordinates(List.of(List.of(1d, 2d), List.of(3d, 4d)));

    when(linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(anyInt())).thenReturn(linkInfoInDB);
    when(linkInfoDAO.save(newLinkInfo)).thenReturn(newLinkInfo);
    LinkInfo saveResult = linkInfoService.saveIfChanged(newLinkInfo);

    verify(linkInfoDAO, times(1)).save(newLinkInfo);
    assertNotEquals(linkInfoInDB, saveResult);
    assertEquals(newLinkInfo, saveResult);
  }

  @Test
  public void saveUnchangedLinkInfoTest() {
    LinkInfo linkInfoInDB = new LinkInfo();
    linkInfoInDB.setName("hello world");
    linkInfoInDB.setOriginId(1);
    linkInfoInDB.setDestinationId(2);
    linkInfoInDB.setLength(3);
    linkInfoInDB.setMinNumberOfLanes(4);
    linkInfoInDB.setMinTravelTime(5);
    linkInfoInDB.setIsFreeway(true);
    linkInfoInDB.setDirection("NB");
    linkInfoInDB.setCoordinates(List.of(List.of(1d, 2d), List.of(3d, 4d)));

    LinkInfo sameLinkInfo = new LinkInfo();
    sameLinkInfo.setName("hello world");
    sameLinkInfo.setOriginId(1);
    sameLinkInfo.setDestinationId(2);
    sameLinkInfo.setLength(3);
    sameLinkInfo.setMinNumberOfLanes(4);
    sameLinkInfo.setMinTravelTime(5);
    sameLinkInfo.setIsFreeway(true);
    sameLinkInfo.setDirection("NB");
    sameLinkInfo.setCoordinates(List.of(List.of(1d, 2d), List.of(3d, 4d)));

    when(linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(any())).thenReturn(linkInfoInDB);

    LinkInfo saveResult = linkInfoService.saveIfChanged(sameLinkInfo);

    verify(linkInfoDAO, times(0)).save(sameLinkInfo);
    assertEquals(linkInfoInDB, saveResult);
  }

}
