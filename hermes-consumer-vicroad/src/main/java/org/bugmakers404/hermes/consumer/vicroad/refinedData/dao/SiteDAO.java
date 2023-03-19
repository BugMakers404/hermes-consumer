package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.SiteEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteDAO extends MongoRepository<SiteEvent, String> {

}
