package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkInfoDAO extends MongoRepository<LinkInfo, String> {

  LinkInfo findTopByLinkIdOrderByTimestampDesc(Integer linkId);

}
