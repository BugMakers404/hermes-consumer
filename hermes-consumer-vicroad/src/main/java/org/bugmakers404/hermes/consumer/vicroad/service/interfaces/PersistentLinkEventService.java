package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;

public interface PersistentLinkEventService {

  LinkEvent saveLinkEvent(LinkEvent linkEvent);

  List<LinkEvent> getAllLinkEvents();

  List<LinkEvent> getAllLinkEventsByLinkId(Integer linkId);

  LinkEvent getLatestLinkEventByLinkId(Integer linkId);

  List<LinkEvent> getAllLinkEventsByTimestamp(OffsetDateTime timestamp);

  LinkEvent getLinkEventByLinkIdAndTimestamp(Integer linkId, OffsetDateTime timestamp);
}
