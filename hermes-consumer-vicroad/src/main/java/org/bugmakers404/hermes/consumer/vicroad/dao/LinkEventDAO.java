package org.bugmakers404.hermes.consumer.vicroad.dao;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkEventDAO extends MongoRepository<LinkEvent, String> {

  List<LinkEvent> findAllByLinkId(Integer linkId);

  List<LinkEvent> findAllByTimestamp(OffsetDateTime timestamp);

  LinkEvent findByLinkIdAndTimestamp(Integer linkId, OffsetDateTime timestamp);

  LinkEvent findTopByLinkIdOrderByTimestampDesc(Integer linkId);
}
