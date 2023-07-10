package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.bugmakers404.hermes.consumer.vicroad.dao.SiteEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteEventService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentSiteStatsServiceTest {

  @Mock
  private SiteEventDAO siteEventDAO;

  private PersistentSiteEventService siteEventService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    siteEventService = new PersistentSiteEventServiceImpl(siteEventDAO);
  }

  @Test
  public void saveSiteEventTest() {
    SiteStats siteStats = new SiteStats();
    when(siteEventDAO.save(any(SiteStats.class))).thenReturn(siteStats);

    SiteStats result = siteEventService.save(siteStats);

    verify(siteEventDAO, times(1)).save(siteStats);
    assertEquals(siteStats, result);
  }
}
