package org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;

public interface LinkService {

  LinkEvent saveOneLinkEvent(LinkEvent linkEvent);

  List<LinkEvent> getAllLinkEvents();

  List<LinkEvent> getAllLinkEventsByLinkId(Integer linkId);

  List<LinkEvent> getAllLinkEventsByTimestamp(LocalDateTime timestamp);

  LinkEvent getALinkEventByLinkIdAndTimestamp(Integer linkId, LocalDateTime timestamp);
}
