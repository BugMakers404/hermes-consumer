package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.bugmakers404.hermes.consumer.vicroad.dao.LinkEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkStatsService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentLinkStatsServiceTest {

  @Mock
  private LinkEventDAO linkEventDAO;

  private PersistentLinkStatsService linkEventService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    linkEventService = new PersistentLinkStatsServiceImpl(linkEventDAO);
  }

  @Test
  public void saveLinkEventTest() {
    LinkStats linkStats = new LinkStats();
    when(linkEventDAO.save(any(LinkStats.class))).thenReturn(linkStats);
    LinkStats result = linkEventService.save(linkStats);

    verify(linkEventDAO, times(1)).save(linkStats);
    assertEquals(linkStats, result);
  }
}
