package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkGeoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkGeoInfoDAO extends MongoRepository<LinkGeoInfo, String> {

}
