package org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;

public interface LinkService {

  LinkEvent saveOneLinkEvent(LinkEvent linkEvent);

  LinkEvent findLinkEventByLinkId(Integer id);

  List<LinkEvent> getAllLinkEvents();
}
