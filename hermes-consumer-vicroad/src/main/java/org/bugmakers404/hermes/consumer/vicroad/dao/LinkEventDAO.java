package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entity.LinkStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkEventDAO extends MongoRepository<LinkStats, String> {

}
