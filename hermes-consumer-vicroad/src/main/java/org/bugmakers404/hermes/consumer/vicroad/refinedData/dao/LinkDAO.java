package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkDAO extends MongoRepository<LinkEvent, String> {

  List<LinkEvent> findAllByLinkId(Integer linkId);

  List<LinkEvent> findAllByTimestamp(LocalDateTime timestamp);
}
