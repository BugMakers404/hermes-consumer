package org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;

public interface PersistentLinkService {

  LinkEvent saveLinkEvent(LinkEvent linkEvent);

  List<LinkEvent> getAllLinkEvents();

  List<LinkEvent> getAllLinkEventsByLinkId(Integer linkId);

  List<LinkEvent> getAllLinkEventsByTimestamp(OffsetDateTime timestamp);

  LinkEvent getLinkEventByLinkIdAndTimestamp(Integer linkId, OffsetDateTime timestamp);
}
