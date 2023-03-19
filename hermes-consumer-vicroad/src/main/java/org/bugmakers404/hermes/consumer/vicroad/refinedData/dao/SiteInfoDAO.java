package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.SiteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteInfoDAO extends MongoRepository<SiteInfo, String> {

}
