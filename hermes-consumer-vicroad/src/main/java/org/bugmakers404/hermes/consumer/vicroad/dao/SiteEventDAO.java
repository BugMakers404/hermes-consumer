package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entity.SiteStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteEventDAO extends MongoRepository<SiteStats, String> {

}
