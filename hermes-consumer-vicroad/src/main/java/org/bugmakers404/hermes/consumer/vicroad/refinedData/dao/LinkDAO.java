package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkDAO extends MongoRepository<LinkEvent, String> {

}
