package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteInfoDAO extends MongoRepository<SiteInfo, String> {

}
