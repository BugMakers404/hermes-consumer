package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;

public interface PersistentLinkEventService {

    LinkEvent saveLinkEvent(LinkEvent linkEvent);

}
