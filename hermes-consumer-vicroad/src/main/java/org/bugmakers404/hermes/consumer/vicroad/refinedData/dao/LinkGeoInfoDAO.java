package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import java.util.List;
import java.util.Optional;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkGeoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkGeoInfoDAO extends MongoRepository<LinkGeoInfo, String> {

  List<LinkGeoInfo> findAllByLinkId(Integer linkId);

  Optional<LinkGeoInfo> findTopByLinkIdOrderByTimestampDesc(Integer linkId);

}
