package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkDAO extends MongoRepository<LinkEvent, String> {

  List<LinkEvent> findAllByLinkId(Integer linkId);

  List<LinkEvent> findAllByTimestamp(OffsetDateTime timestamp);

  LinkEvent findByLinkIdAndTimestamp(Integer linkId, OffsetDateTime timestamp);

  List<LinkEvent> findAllLatestByTimestamp();
}
