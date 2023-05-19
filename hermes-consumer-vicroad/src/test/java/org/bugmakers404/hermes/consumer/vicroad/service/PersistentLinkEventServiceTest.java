package org.bugmakers404.hermes.consumer.vicroad.service;

import org.bugmakers404.hermes.consumer.vicroad.dao.LinkEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class PersistentLinkEventServiceTest {

    @Mock
    private LinkEventDAO linkEventDAO;

    private PersistentLinkEventService linkEventService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
        linkEventService = new PersistentLinkEventServiceImpl(linkEventDAO);
    }

    @Test
    public void saveLinkEventTest() {
        LinkEvent linkEvent = new LinkEvent();

        when(linkEventDAO.save(any(LinkEvent.class))).thenReturn(linkEvent);
        LinkEvent result = linkEventService.saveLinkEvent(linkEvent);

        verify(linkEventDAO, times(1)).save(linkEvent);
        assertEquals(linkEvent, result);
    }
}
