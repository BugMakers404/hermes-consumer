package org.bugmakers404.hermes.consumer.vicroad.dao;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkInfoDAO extends MongoRepository<LinkInfo, String> {

  List<LinkInfo> findAllByLinkId(Integer linkId);

  LinkInfo findTopByLinkIdOrderByTimestampDesc(Integer linkId);

}
