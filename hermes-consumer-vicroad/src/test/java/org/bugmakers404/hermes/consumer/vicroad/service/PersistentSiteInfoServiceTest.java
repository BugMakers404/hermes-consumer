package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.dao.SiteInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.sites.SiteInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentSiteInfoService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersistentSiteInfoServiceTest {

  @Mock
  private SiteInfoDAO siteInfoDAO;

  private PersistentSiteInfoService siteInfoService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    siteInfoService = new PersistentSiteInfoServiceImpl(siteInfoDAO);
  }

  @Test
  public void saveNewSiteInfoTest() {
    SiteInfo newSiteInfo = new SiteInfo();
    when(siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(anyInt())).thenReturn(null);
    when(siteInfoDAO.save(any(SiteInfo.class))).thenReturn(newSiteInfo);

    SiteInfo saveResult = siteInfoService.saveIfChanged(newSiteInfo);

    verify(siteInfoDAO, times(1)).save(newSiteInfo);
    assertEquals(newSiteInfo, saveResult);
  }

  @Test
  public void saveChangedSiteInfoTest() {
    SiteInfo siteInfoInDB = new SiteInfo();
    siteInfoInDB.setName("hello world");
    siteInfoInDB.setLocation(List.of(1d, 2d));

    SiteInfo newSiteInfo = new SiteInfo();
    newSiteInfo.setName("goodbye world");
    newSiteInfo.setLocation(List.of(1d, 2d));

    when(siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(any())).thenReturn(siteInfoInDB);
    when(siteInfoDAO.save(newSiteInfo)).thenReturn(newSiteInfo);
    SiteInfo saveResult = siteInfoService.saveIfChanged(newSiteInfo);

    verify(siteInfoDAO, times(1)).save(newSiteInfo);
    assertNotEquals(siteInfoInDB, saveResult);
    assertEquals(newSiteInfo, saveResult);
  }

  @Test
  public void saveUnchangedSiteInfoTest() {
    SiteInfo siteInfoInDB = new SiteInfo();
    siteInfoInDB.setName("hello world");
    siteInfoInDB.setLocation(List.of(1d, 2d));

    SiteInfo sameSiteInfo = new SiteInfo();
    sameSiteInfo.setName("hello world");
    sameSiteInfo.setLocation(List.of(1d, 2d));

    when(siteInfoDAO.findTopBySiteIdOrderByTimestampDesc(any())).thenReturn(siteInfoInDB);
    SiteInfo saveResult = siteInfoService.saveIfChanged(sameSiteInfo);

    verify(siteInfoDAO, times(0)).save(sameSiteInfo);
    assertEquals(siteInfoInDB, saveResult);
  }
}
