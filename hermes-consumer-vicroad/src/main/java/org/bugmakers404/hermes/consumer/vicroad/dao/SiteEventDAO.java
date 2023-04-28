package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteEventDAO extends MongoRepository<SiteEvent, String> {

}
