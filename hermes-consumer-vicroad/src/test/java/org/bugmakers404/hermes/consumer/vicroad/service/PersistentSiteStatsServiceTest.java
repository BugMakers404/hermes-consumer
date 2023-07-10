package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.bugmakers404.hermes.consumer.vicroad.dao.SiteStatsDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteStatsService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentSiteStatsServiceTest {

  @Mock
  private SiteStatsDAO siteStatsDAO;

  private PersistentSiteStatsService siteEventService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    siteEventService = new PersistentSiteStatsServiceImpl(siteStatsDAO);
  }

  @Test
  public void saveSiteEventTest() {
    SiteStats siteStats = new SiteStats();
    when(siteStatsDAO.save(any(SiteStats.class))).thenReturn(siteStats);

    SiteStats result = siteEventService.save(siteStats);

    verify(siteStatsDAO, times(1)).save(siteStats);
    assertEquals(siteStats, result);
  }
}
