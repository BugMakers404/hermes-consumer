package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkInfoDAO extends MongoRepository<LinkInfo, String> {

}
